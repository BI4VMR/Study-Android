package net.bi4vmr.study.privacy.appops;

import static android.hardware.SensorPrivacyManager.Sensors.CAMERA;
import static android.hardware.SensorPrivacyManager.Sensors.MICROPHONE;
import static android.media.AudioManager.ACTION_MICROPHONE_MUTE_CHANGED;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;

import com.android.systemui.broadcast.BroadcastDispatcher;

import net.bi4vmr.study.privacy.AppProtoEnums;
import net.bi4vmr.study.privacy.IndividualSensorPrivacyController;
import net.bi4vmr.study.privacy.UserHandleExt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * 控制器，用于跟踪已请求访问给定应用程序操作的应用程序
 * <p>
 * 可以通过回调订阅。此外，它还将信息传递给
 * 要向用户显示的 NotificationPresenter。
 */
@RequiresApi(api = Build.VERSION_CODES.S)
public class AppOpsControllerImpl extends BroadcastReceiver implements AppOpsController,
        AppOpsManager.OnOpActiveChangedListener,
        AppOpsManager.OnOpNotedInternalListener,
        IndividualSensorPrivacyController.Callback {

    // This is the minimum time that we will keep AppOps that are noted on record. If multiple
    // occurrences of the same (op, package, uid) happen in a shorter interval, they will not be
    // notified to listeners.
    private static final long NOTED_OP_TIME_DELAY_MS = 5000;
    private static final String TAG = "AppOpsControllerImpl";
    private static final boolean DEBUG = false;

    private final AppOpsManager mAppOps;
    private final AudioManager mAudioManager;
    private final BroadcastDispatcher mDispatcher;
    private final IndividualSensorPrivacyController mSensorPrivacyController;

    private H mBGHandler;
    private final Executor mBgExecutor;
    private final List<AppOpsController.Callback> mCallbacks = new ArrayList<>();
    private final SparseArray<Set<Callback>> mCallbacksByCode = new SparseArray<>();
    private boolean mMicMuted;
    private boolean mCameraDisabled;

    private final List<AppOpItem> mActiveItems = new ArrayList<>();
    private final List<AppOpItem> mNotedItems = new ArrayList<>();
    private final SparseArray<ArrayList<AudioRecordingConfiguration>> mRecordingsByUid =
            new SparseArray<>();

    protected static final int[] OPS_MIC = new int[]{
            AppProtoEnums.APP_OP_RECORD_AUDIO,
            AppProtoEnums.APP_OP_PHONE_CALL_MICROPHONE,
            AppProtoEnums.APP_OP_RECEIVE_AMBIENT_TRIGGER_AUDIO,
            AppProtoEnums.APP_OP_RECEIVE_SANDBOX_TRIGGER_AUDIO,
            AppProtoEnums.APP_OP_RECEIVE_EXPLICIT_USER_INTERACTION_AUDIO
    };

    protected static final int[] OPS_CAMERA = new int[]{
            AppProtoEnums.APP_OP_CAMERA,
            AppProtoEnums.APP_OP_PHONE_CALL_CAMERA
    };

    protected static final int[] OPS_LOC = new int[]{
            AppProtoEnums.APP_OP_COARSE_LOCATION,
            AppProtoEnums.APP_OP_FINE_LOCATION,
            AppProtoEnums.APP_OP_MONITOR_HIGH_POWER_LOCATION
    };

    protected static final int[] OPS_OTHERS = new int[]{
            AppProtoEnums.APP_OP_SYSTEM_ALERT_WINDOW
    };

    protected static final int[] OPS = concatOps(OPS_MIC, OPS_CAMERA, OPS_LOC, OPS_OTHERS);

    /**
     * @param opArrays the given op arrays.
     * @return the concatenations of the given op arrays. Null arrays are treated as empty.
     */
    private static int[] concatOps(@Nullable int[]... opArrays) {
        if (opArrays == null) {
            return new int[0];
        }
        int totalLength = 0;
        for (int[] opArray : opArrays) {
            if (opArray == null || opArray.length == 0) {
                continue;
            }
            totalLength += opArray.length;
        }
        final int[] concatOps = new int[totalLength];
        int index = 0;
        for (int[] opArray : opArrays) {
            if (opArray == null || opArray.length == 0) continue;
            System.arraycopy(opArray, 0, concatOps, index, opArray.length);
            index += opArray.length;
        }
        return concatOps;
    }

    public AppOpsControllerImpl(
            Context context,
            Looper bgLooper,
            Executor bgExecutor,
            AudioManager audioManager,
            IndividualSensorPrivacyController sensorPrivacyController,
            BroadcastDispatcher dispatcher
    ) {
        mDispatcher = dispatcher;
        mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        mBGHandler = new H(bgLooper);
        mBgExecutor = bgExecutor;
        for (int op : OPS) {
            mCallbacksByCode.put(op, new ArraySet<>());
        }
        mAudioManager = audioManager;
        mSensorPrivacyController = sensorPrivacyController;
        mMicMuted = audioManager.isMicrophoneMute()
                || mSensorPrivacyController.isSensorBlocked(MICROPHONE);
        mCameraDisabled = mSensorPrivacyController.isSensorBlocked(CAMERA);
        mContext = context;
    }

    protected void setBGHandler(H handler) {
        mBGHandler = handler;
    }

    protected void setListening(boolean listening) {
        mListening = listening;
        // Move IPCs to the background.
        mBgExecutor.execute(() -> {
            if (listening) {
                // System UI could be restarted while ops are active, so fetch the currently active
                // ops once System UI starts listening again -- see b/294104969.
                fetchCurrentActiveOps();

                mAppOps.startWatchingActive(OPS, this);
                mAppOps.startWatchingNoted(OPS, this);
                mAudioManager.registerAudioRecordingCallback(mAudioRecordingCallback, mBGHandler);
                mSensorPrivacyController.addCallback(this);

                mMicMuted = mAudioManager.isMicrophoneMute()
                        || mSensorPrivacyController.isSensorBlocked(MICROPHONE);
                mCameraDisabled = mSensorPrivacyController.isSensorBlocked(CAMERA);

                mBGHandler.post(() -> mAudioRecordingCallback.onRecordingConfigChanged(
                        mAudioManager.getActiveRecordingConfigurations()));
                mDispatcher.registerReceiverWithHandler(this,
                        new IntentFilter(ACTION_MICROPHONE_MUTE_CHANGED), mBGHandler);
            } else {
                mAppOps.stopWatchingActive(this);
                mAppOps.stopWatchingNoted(this);
                mAudioManager.unregisterAudioRecordingCallback(mAudioRecordingCallback);
                mSensorPrivacyController.removeCallback(this);

                mBGHandler.removeCallbacksAndMessages(null); // null removes all
                mDispatcher.unregisterReceiver(this);
                synchronized (mActiveItems) {
                    mActiveItems.clear();
                    mRecordingsByUid.clear();
                }
                synchronized (mNotedItems) {
                    mNotedItems.clear();
                }
            }
        });
    }

    private void fetchCurrentActiveOps() {
        List<AppOpsManager.PackageOps> packageOps = mAppOps.getPackagesForOps(OPS);
        for (AppOpsManager.PackageOps op : packageOps) {
            for (AppOpsManager.OpEntry entry : op.getOps()) {
                for (Map.Entry<String, AppOpsManager.AttributedOpEntry> attributedOpEntry :
                        entry.getAttributedOpEntries().entrySet()) {
                    if (attributedOpEntry.getValue().isRunning()) {
                        onOpActiveChanged(
                                entry.getOpStr(),
                                op.getUid(),
                                op.getPackageName(),
                                /* attributionTag= */
                                attributedOpEntry.getKey(),
                                /* active= */
                                true,
                                // AppOpsManager doesn't have a way to fetch attribution flags or
                                // chain ID given an op entry, so default them to none.
                                // AppOpsManager.ATTRIBUTION_FLAGS_NONE <-> 0
                                0,
                                // AppOpsManager.ATTRIBUTION_CHAIN_ID_NONE <-> -1
                                -1);
                    }
                }
            }
        }
    }

    /**
     * Adds a callback that will get notifified when an AppOp of the type the controller tracks
     * changes
     *
     * @param callback Callback to report changes
     * @param opsCodes App Ops the callback is interested in checking
     * @see #removeCallback(int[], Callback)
     */
    @Override
    public void addCallback(int[] opsCodes, AppOpsController.Callback callback) {
        boolean added = false;
        final int numCodes = opsCodes.length;
        for (int i = 0; i < numCodes; i++) {
            if (mCallbacksByCode.contains(opsCodes[i])) {
                mCallbacksByCode.get(opsCodes[i]).add(callback);
                added = true;
            } else {
                if (DEBUG) Log.wtf(TAG, "APP_OP " + opsCodes[i] + " not supported");
            }
        }
        if (added) mCallbacks.add(callback);
        if (!mCallbacks.isEmpty()) setListening(true);
    }

    /**
     * Removes a callback from those notified when an AppOp of the type the controller tracks
     * changes
     *
     * @param callback Callback to stop reporting changes
     * @param opsCodes App Ops the callback was interested in checking
     * @see #addCallback(int[], Callback)
     */
    @Override
    public void removeCallback(int[] opsCodes, AppOpsController.Callback callback) {
        final int numCodes = opsCodes.length;
        for (int i = 0; i < numCodes; i++) {
            if (mCallbacksByCode.contains(opsCodes[i])) {
                mCallbacksByCode.get(opsCodes[i]).remove(callback);
            }
        }
        mCallbacks.remove(callback);
        if (mCallbacks.isEmpty()) setListening(false);
    }

    // 在列表中查找项目编号，仅在传递的列表被锁定时调用。
    private AppOpItem getAppOpItemLocked(List<AppOpItem> appOpList, int code, int uid,
                                         String packageName) {
        final int itemsQ = appOpList.size();
        for (int i = 0; i < itemsQ; i++) {
            AppOpItem item = appOpList.get(i);
            if (item.getCode() == code && item.getUid() == uid
                    && item.getPackageName().equals(packageName)) {
                return item;
            }
        }
        return null;
    }

    private boolean updateActives(int code, int uid, String packageName, boolean active) {
        synchronized (mActiveItems) {
            AppOpItem item = getAppOpItemLocked(mActiveItems, code, uid, packageName);
            if (item == null && active) {
                item = new AppOpItem(code, uid, packageName, SystemClock.elapsedRealtime());
                if (isOpMicrophone(code)) {
                    item.setDisabled(isAnyRecordingPausedLocked(uid));
                } else if (isOpCamera(code)) {
                    item.setDisabled(mCameraDisabled);
                }
                mActiveItems.add(item);
                if (DEBUG) Log.w(TAG, "Added item: " + item.toString());
                return !item.isDisabled();
            } else if (item != null && !active) {
                mActiveItems.remove(item);
                if (DEBUG) Log.w(TAG, "Removed item: " + item.toString());
                return true;
            }
            return false;
        }
    }

    private void removeNoted(int code, int uid, String packageName) {
        AppOpItem item;
        synchronized (mNotedItems) {
            item = getAppOpItemLocked(mNotedItems, code, uid, packageName);
            if (item == null) return;
            mNotedItems.remove(item);
            if (DEBUG) Log.w(TAG, "Removed item: " + item.toString());
        }
        boolean active;
        // Check if the item is also active
        synchronized (mActiveItems) {
            active = getAppOpItemLocked(mActiveItems, code, uid, packageName) != null;
        }
        if (!active) {
            notifySuscribersWorker(code, uid, packageName, false);
        }
    }

    private boolean addNoted(int code, int uid, String packageName) {
        AppOpItem item;
        boolean createdNew = false;
        synchronized (mNotedItems) {
            item = getAppOpItemLocked(mNotedItems, code, uid, packageName);
            if (item == null) {
                item = new AppOpItem(code, uid, packageName, SystemClock.elapsedRealtime());
                mNotedItems.add(item);
                if (DEBUG) Log.w(TAG, "Added item: " + item);
                createdNew = true;
            }
        }
        // We should keep this so we make sure it cannot time out.
        mBGHandler.removeCallbacksAndMessages(item);
        mBGHandler.scheduleRemoval(item, NOTED_OP_TIME_DELAY_MS);
        return createdNew;
    }

    // 判断是否为包含"android"字样的系统包
    private boolean isUserVisible(String packageName) {
        // return PermissionManager.shouldShowPackageForIndicatorCached(mContext, packageName);
        return true;
    }

    @WorkerThread
    public List<AppOpItem> getActiveAppOps() {
        return getActiveAppOps(false);
    }

    /**
     * Returns a copy of the list containing all the active AppOps that the controller tracks.
     * <p>
     * Call from a worker thread as it may perform long operations.
     *
     * @return List of active AppOps information
     */
    @WorkerThread
    public List<AppOpItem> getActiveAppOps(boolean showPaused) {
        // UserHandleExt.USER_ALL <-> -1
        return getActiveAppOpsForUser(-1, showPaused);
    }

    /**
     * Returns a copy of the list containing all the active AppOps that the controller tracks, for
     * a given user id.
     * <p>
     * Call from a worker thread as it may perform long operations.
     *
     * @param userId User id to track, can be UserHandleExt.USER_ALL
     * @return List of active AppOps information for that user id
     */
    @WorkerThread
    public List<AppOpItem> getActiveAppOpsForUser(int userId, boolean showPaused) {
        List<AppOpItem> list = new ArrayList<>();
        synchronized (mActiveItems) {
            final int numActiveItems = mActiveItems.size();
            for (int i = 0; i < numActiveItems; i++) {
                AppOpItem item = mActiveItems.get(i);
                // "-1" <-> UserHandleExt.USER_ALL
                if ((userId == -1
                        || UserHandleExt.getUserId(item.getUid()) == userId)
                        && isUserVisible(item.getPackageName())
                        && (showPaused || !item.isDisabled())) {
                    list.add(item);
                }
            }
        }
        synchronized (mNotedItems) {
            final int numNotedItems = mNotedItems.size();
            for (int i = 0; i < numNotedItems; i++) {
                AppOpItem item = mNotedItems.get(i);
                // "-1" <-> UserHandleExt.USER_ALL
                if ((userId == -1
                        || UserHandleExt.getUserId(item.getUid()) == userId)
                        && isUserVisible(item.getPackageName())) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    private void notifySuscribers(int code, int uid, String packageName, boolean active) {
        mBGHandler.post(() -> notifySuscribersWorker(code, uid, packageName, active));
    }

    /**
     * Required to override, delegate to other. Should not be called.
     */
    public void onOpActiveChanged(@NonNull String op, int uid, @NonNull String packageName, boolean active) {
        onOpActiveChanged(op, uid, packageName, null, active,
                AppOpsManager.ATTRIBUTION_FLAGS_NONE, AppOpsManager.ATTRIBUTION_CHAIN_ID_NONE);
    }

    // Get active app ops, and check if their attributions are trusted
    @Override
    public void onOpActiveChanged(String op, int uid, String packageName, String attributionTag,
                                  boolean active, int attributionFlags, int attributionChainId) {
        int code = AppOpsManager.strOpToOp(op);
        if (DEBUG) {
            Log.w(TAG, String.format("onActiveChanged(%d,%d,%s,%s,%d,%d)", code, uid, packageName,
                    active, attributionChainId, attributionFlags));
        }
        if (active && attributionChainId != AppOpsManager.ATTRIBUTION_CHAIN_ID_NONE
                && attributionFlags != AppOpsManager.ATTRIBUTION_FLAGS_NONE
                && (attributionFlags & AppOpsManager.ATTRIBUTION_FLAG_ACCESSOR) == 0
                && (attributionFlags & AppOpsManager.ATTRIBUTION_FLAG_TRUSTED) == 0) {
            // if this attribution chain isn't trusted, and this isn't the accessor, do not show it.
            return;
        }
        boolean activeChanged = updateActives(code, uid, packageName, active);
        if (!activeChanged) return; // early return
        // Check if the item is also noted, in that case, there's no update.
        boolean alsoNoted;
        synchronized (mNotedItems) {
            alsoNoted = getAppOpItemLocked(mNotedItems, code, uid, packageName) != null;
        }
        // If active is true, we only send the update if the op is not actively noted (already true)
        // If active is false, we only send the update if the op is not actively noted (prevent
        // early removal)
        if (!alsoNoted) {
            notifySuscribers(code, uid, packageName, active);
        }
    }

    @Override
    public void onOpNoted(int code, int uid, String packageName,
                          String attributionTag, @AppOpsManager.OpFlags int flags,
                          @AppOpsManager.Mode int result) {
        if (DEBUG) {
            Log.w(TAG, "Noted op: " + code + " with result "
                    + AppOpsManager.MODE_NAMES[result] + " for package " + packageName);
        }
        if (result != AppOpsManager.MODE_ALLOWED) return;
        boolean notedAdded = addNoted(code, uid, packageName);
        if (!notedAdded) return; // early return
        boolean alsoActive;
        synchronized (mActiveItems) {
            alsoActive = getAppOpItemLocked(mActiveItems, code, uid, packageName) != null;
        }
        if (!alsoActive) {
            notifySuscribers(code, uid, packageName, true);
        }
    }

    private void notifySuscribersWorker(int code, int uid, String packageName, boolean active) {
        if (mCallbacksByCode.contains(code) && isUserVisible(packageName)) {
            if (DEBUG) Log.d(TAG, "Notifying of change in package " + packageName);
            for (Callback cb : mCallbacksByCode.get(code)) {
                cb.onActiveStateChanged(code, uid, packageName, active);
            }
        }
    }

    private boolean isAnyRecordingPausedLocked(int uid) {
        if (mMicMuted) {
            return true;
        }
        List<AudioRecordingConfiguration> configs = mRecordingsByUid.get(uid);
        if (configs == null) return false;
        int configsNum = configs.size();
        for (int i = 0; i < configsNum; i++) {
            AudioRecordingConfiguration config = configs.get(i);
            if (config.isClientSilenced()) return true;
        }
        return false;
    }

    private void updateSensorDisabledStatus() {
        synchronized (mActiveItems) {
            int size = mActiveItems.size();
            for (int i = 0; i < size; i++) {
                AppOpItem item = mActiveItems.get(i);

                boolean paused = false;
                if (isOpMicrophone(item.getCode())) {
                    paused = isAnyRecordingPausedLocked(item.getUid());
                } else if (isOpCamera(item.getCode())) {
                    paused = mCameraDisabled;
                }

                if (item.isDisabled() != paused) {
                    item.setDisabled(paused);
                    notifySuscribers(
                            item.getCode(),
                            item.getUid(),
                            item.getPackageName(),
                            !item.isDisabled()
                    );
                }
            }
        }
    }

    private AudioManager.AudioRecordingCallback mAudioRecordingCallback =
            new AudioManager.AudioRecordingCallback() {
                @Override
                public void onRecordingConfigChanged(List<AudioRecordingConfiguration> configs) {
                    synchronized (mActiveItems) {
                        mRecordingsByUid.clear();
                        final int recordingsCount = configs.size();
                        for (int i = 0; i < recordingsCount; i++) {
                            AudioRecordingConfiguration recording = configs.get(i);

                            ArrayList<AudioRecordingConfiguration> recordings = mRecordingsByUid.get(
                                    recording.getClientUid());
                            if (recordings == null) {
                                recordings = new ArrayList<>();
                                mRecordingsByUid.put(recording.getClientUid(), recordings);
                            }
                            recordings.add(recording);
                        }
                    }
                    updateSensorDisabledStatus();
                }
            };

    @Override
    public void onReceive(Context context, Intent intent) {
        mMicMuted = mAudioManager.isMicrophoneMute()
                || mSensorPrivacyController.isSensorBlocked(MICROPHONE);
        updateSensorDisabledStatus();
    }

    @Override
    public void onSensorBlockedChanged(int sensor, boolean blocked) {
        mBGHandler.post(() -> {
            if (sensor == CAMERA) {
                mCameraDisabled = blocked;
            } else if (sensor == MICROPHONE) {
                mMicMuted = mAudioManager.isMicrophoneMute() || blocked;
            }
            updateSensorDisabledStatus();
        });
    }

    @Override
    public boolean isMicMuted() {
        return mMicMuted;
    }

    // 判断参数传入的操作目标是否为摄像头
    private boolean isOpCamera(int op) {
        for (int i : OPS_CAMERA) {
            if (op == i) return true;
        }
        return false;
    }

    // 判断参数传入的操作目标是否为麦克风
    private boolean isOpMicrophone(int op) {
        for (int i : OPS_MIC) {
            if (op == i) return true;
        }
        return false;
    }

    protected class H extends Handler {
        H(Looper looper) {
            super(looper);
        }

        public void scheduleRemoval(AppOpItem item, long timeToRemoval) {
            removeCallbacksAndMessages(item);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    removeNoted(item.getCode(), item.getUid(), item.getPackageName());
                }
            }, item, timeToRemoval);
        }
    }
}
