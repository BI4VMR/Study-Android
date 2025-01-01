package net.bi4vmr.study.permission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

public class AospPermissionMgr implements AospPermissionChangeListener.ChangeCallback {

    private static final String CLASS_NAME_INNER_LISTENER = "android.content.pm.PackageManager$OnPermissionsChangedListener";
    private static final String METHOD_NAME_ADD_LISTENER = "addOnPermissionsChangeListener";
    private static final String METHOD_NAME_REMOVE_LISTENER = "removeOnPermissionsChangeListener";
    private static final String METHOD_NAME_REVOKE_PERMISSION = "revokeRuntimePermission";
    private static final String METHOD_NAME_GRANT_PERMISSION = "grantRuntimePermission";
    private static final String METHOD_NAME_UPDATE_FLAGS = "updatePermissionFlags";
    private static final String METHOD_NAME_SET_UID_MODE = "setUidMode";
    private static volatile AospPermissionMgr mInstance;
    private final Context mContext;
    private final PackageManager mPackageManager;
    private Object mPermissionListener;
    private PermissionChangeListener mListener;
    private PermissionChangeListener2 mListener2;
    private final Class<?> mPackageMgrClazz;

    private static final String PKG_AMAP = "com.autonavi.amapauto";
    private static final String PKG_WEATHER = "com.icoolme.car.weather";

    private static final String PERMISSION_LOCATION_FINE = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_LOCATION_COARSE = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;

    private boolean amap_location, amap_record, weather_location;


    private AospPermissionMgr(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mPackageMgrClazz = mPackageManager.getClass();
        initPermissionGrantCache();
    }

    public static AospPermissionMgr getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AospPermissionMgr.class) {
                if (mInstance == null) {
                    mInstance = new AospPermissionMgr(context);
                }
            }
        }
        return mInstance;
    }

    @SuppressLint("PrivateApi")
    public void startListenPermissionChange(PermissionChangeListener listener) {
        this.mListener = listener;
        if (mPermissionListener != null) {
            Log.d("TestApp", "startListenPermissionChange: The inner listener added already");
            return;
        }
        try {
            Class<?> clazz = Class.forName(CLASS_NAME_INNER_LISTENER);
            Method method = this.mPackageMgrClazz.getDeclaredMethod(METHOD_NAME_ADD_LISTENER, clazz);
            this.mPermissionListener = AospPermissionChangeListener.newInstance(new Class[]{clazz}, this);
            method.invoke(mPackageManager, this.mPermissionListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startListenPermissionChange2(PermissionChangeListener2 listener) {
        this.mListener2 = listener;
        if (mPermissionListener != null) {
            Log.d("TestApp", "startListenPermissionChange: The inner listener added already");
            return;
        }
        try {
            Class<?> clazz = Class.forName(CLASS_NAME_INNER_LISTENER);
            Method method = this.mPackageMgrClazz.getDeclaredMethod(METHOD_NAME_ADD_LISTENER, clazz);
            this.mPermissionListener = AospPermissionChangeListener.newInstance(new Class[]{clazz}, this);
            method.invoke(mPackageManager, this.mPermissionListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("PrivateApi")
    public void stopListenPermissionChange() {
        Log.d("TestApp", "stopListenPermissionChange: ");
        if (mPermissionListener == null) {
            return;
        }
        try {
            Class<?> clazz = Class.forName(CLASS_NAME_INNER_LISTENER);
            this.mPackageMgrClazz.getDeclaredMethod(METHOD_NAME_REMOVE_LISTENER, clazz)
                    .invoke(mPackageManager, this.mPermissionListener);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mPermissionListener = null;
            mListener = null;
        }
    }

    @Override
    public void onRuntimePermissionChange(int uid) {
        Log.d("TestApp", "onRuntimePermissionChange: " + uid);
        if (mPackageManager == null) return;
        String[] pkgs = mPackageManager.getPackagesForUid(uid);
        if (pkgs == null) {
            return;
        }
        for (String item : pkgs) {
            handlePermissionChanged(item);
        }
    }

    public boolean isAmapLocationGranted() {
        return isPermissionGranted(PKG_AMAP, PERMISSION_LOCATION_FINE)
                && isPermissionGranted(PKG_AMAP, PERMISSION_LOCATION_COARSE);
    }

    public void setAmapLocationGranted(Boolean isGrant) {
        setRuntimePermission(PKG_AMAP, PERMISSION_LOCATION_FINE, isGrant);
        setRuntimePermission(PKG_AMAP, PERMISSION_LOCATION_COARSE, isGrant);
    }

    public boolean isAmapRecordGranted() {
        return isPermissionGranted(PKG_AMAP, PERMISSION_RECORD_AUDIO);
    }

    public void setAmapRecordGranted(Boolean isGrant) {
        setRuntimePermission(PKG_AMAP, PERMISSION_RECORD_AUDIO, isGrant);
    }

    public boolean isWeatherLocationGranted() {
        return isPermissionGranted(PKG_WEATHER, PERMISSION_LOCATION_COARSE)
                && isPermissionGranted(PKG_WEATHER, PERMISSION_LOCATION_FINE);
    }

    public void setWeatherLocationGranted(Boolean isGrant) {
        setRuntimePermission(PKG_WEATHER, PERMISSION_LOCATION_COARSE, isGrant);
        setRuntimePermission(PKG_WEATHER, PERMISSION_LOCATION_FINE, isGrant);
    }

    private void initPermissionGrantCache() {
        amap_location = isPermissionGranted(PKG_AMAP, PERMISSION_LOCATION_FINE);
        amap_record = isPermissionGranted(PKG_AMAP, PERMISSION_RECORD_AUDIO);
        weather_location = isPermissionGranted(PKG_WEATHER, PERMISSION_LOCATION_COARSE)
                && isPermissionGranted(PKG_WEATHER, PERMISSION_LOCATION_FINE);
    }

    private void setRuntimePermission(String packageName, String permission, boolean isGrant) {
        int uid = getUidFromPackageName(packageName);
        Log.d("TestApp", "setRuntimePermission: permission = " + permission + ", packageName = "
                + packageName + ", uid = " + uid + ", isGrant = " + isGrant);
        int appOpsMode = isGrant ? AppOpsManager.MODE_ALLOWED : AppOpsManager.MODE_IGNORED;
        setAndroidPermissionState(permission, uid, appOpsMode);
        try {
            UserHandle userHandle = UserHandle.getUserHandleForUid(uid);
            String methodName = isGrant ? METHOD_NAME_GRANT_PERMISSION : METHOD_NAME_REVOKE_PERMISSION;
            Method method = mPackageMgrClazz.getDeclaredMethod(methodName, String.class, String.class, UserHandle.class);
            method.setAccessible(true);
            method.invoke(mPackageManager, packageName, permission, userHandle);

            Method methodFlags = mPackageMgrClazz.getDeclaredMethod(METHOD_NAME_UPDATE_FLAGS, String.class, String.class, int.class, int.class, UserHandle.class);
            if (isGrant) {
                // 使用AOSP接口启用权限后，需要清除FLAG_PERMISSION_USER_SET(1 << 0)，否则重启后坐舱管家读取状态为“允许”，但系统实际的状态为“禁止”。
                methodFlags.invoke(mPackageManager, permission, packageName, 0x1, 0, userHandle);
            } else {
                // 使用AOSP接口禁用权限后，需要将FLAG_PERMISSION_USER_SET(1 << 0)置位，否则重启后系统将会按照预授权状态还原权限。
                methodFlags.invoke(mPackageManager, permission, packageName, 0x1, 1, userHandle);
                // FLAG_PERMISSION_USER_FIXED(1 << 1)与FLAG_PERMISSION_USER_SET(1 << 0)互斥，因此需要取消该标志位。
                methodFlags.invoke(mPackageManager, permission, packageName, 0x2, 0, userHandle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePermissionChanged(String packageName) {
        if (mListener2 != null) {
            mListener2.onPermissionChanged(packageName);
        }

        if (!packageName.equals(PKG_AMAP) && !packageName.equals(PKG_WEATHER)) {
            return;
        }
        if (mListener == null) {
            return;
        }
        if (packageName.equals(PKG_AMAP)) {
            if (amap_location != isAmapLocationGranted()) {
                amap_location = !amap_location;
                mListener.onAmapLocationPermissionChanged(amap_location);
            }
            if (amap_record != isAmapRecordGranted()) {
                amap_record = !amap_record;
                mListener.onAmapRecordPermissionChanged(amap_record);
            }
        }
        if (packageName.equals(PKG_WEATHER)) {
            if (weather_location != isWeatherLocationGranted()) {
                weather_location = !weather_location;
                mListener.onWeatherLocationPermissionChanged(weather_location);
            }
        }
    }

    private boolean isPermissionGranted(String packageName, String permission) {
        PackageInfo packageInfo = getPackageInfoFromPackageName(packageName);
        Log.d("TestApp", "isPermissionGranted: " + packageInfo);
        boolean bool = false;
        if (packageInfo == null || TextUtils.isEmpty(permission)) {
            Log.e("TestApp", "isPermissionGranted packageInfo is null!");
            return false;
        }
        String[] allPermission = packageInfo.requestedPermissions;
        if (allPermission == null) {
            Log.e("TestApp", "isPermissionGrant: allPermission is null!");
            return false;
        }
        int requestedPermissionCount = packageInfo.requestedPermissionsFlags.length;
        Log.d("TestApp", "isPermissionGranted: requestedPermissionCount = " + requestedPermissionCount);
        for (int i = 0; i < allPermission.length; i++) {
            String item = allPermission[i];
            Log.d("TestApp", "isPermissionGranted: compare -> " + item);
            if (permission.equals(item) && i < requestedPermissionCount) {
                if ((packageInfo.requestedPermissionsFlags[i] & 0x2) != 0) {
                    bool = true;
                }
                return bool;
            }
        }
        return false;
    }

    private void setAndroidPermissionState(String permission, int uid, int mode) {
        String str = AppOpsManager.permissionToOp(permission);
        Log.d("TestApp", "setAndroidPermissionState: str = " + str);
        if (str == null) return;
        AppOpsManager appOpsManager = this.mContext.getSystemService(AppOpsManager.class);
        try {
            appOpsManager.getClass()
                    .getMethod(METHOD_NAME_SET_UID_MODE, String.class, int.class, int.class)
                    .invoke(appOpsManager, str, uid, mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getUidFromPackageName(String packageName) {
        try {
            int uid = mPackageManager.getPackageUid(packageName, 0);
            Log.d("TestApp", "getUidFromPackageName: package -> " + packageName + ", uid -> " + uid);
            return uid;
        } catch (PackageManager.NameNotFoundException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    private PackageInfo getPackageInfoFromPackageName(String packageName) {
        try {
            return mPackageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        } catch (Exception e) {
            return null;
        }
    }

    public interface PermissionChangeListener {
        void onAmapLocationPermissionChanged(boolean isGranted);

        void onAmapRecordPermissionChanged(boolean isGranted);

        void onWeatherLocationPermissionChanged(boolean isGranted);
    }

    public interface PermissionChangeListener2 {
        void onPermissionChanged(String pkg);
    }
}
