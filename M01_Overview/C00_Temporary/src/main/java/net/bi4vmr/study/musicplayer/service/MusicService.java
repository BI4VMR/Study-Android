package net.bi4vmr.study.media_demo_musicplayer.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.MediaPlayer;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.util.MusicUtil;
import net.bi4vmr.study.musicplayer.manager.MediaNotificationManager;

import java.io.IOException;
import java.util.List;

/**
 * Name          : MusicService
 * Author        : 詹屹罡
 * Email         : bi4vmr@outlook.com.cn
 * Date          : 2022-04-18 11:23
 * Description   : 音乐播放服务
 */
public class MusicService extends MediaBrowserService {

    private static final String TAG_APP = MusicApp.TAG;
    private static final String TAG_CLS = MusicService.class.getSimpleName();

    // 全局环境
    private Context mContext;

    private AudioManager manager;
    private AudioFocusRequest focusRequest;
    private MediaSession mediaSession;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG_APP, TAG_CLS + ":onCreate()");

        mContext = getApplicationContext();
        // 获取音频管理器
        manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        // 音频属性，影响系统是否允许当前应用获取音频焦点。
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        // 构建请求实例，请求持久获得音频焦点。
        focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                // 设置音频属性，是可选参数，默认值为Usage为USAGE_MEDIA的实例。
                .setAudioAttributes(attributes)
                // 设置音频焦点改变监听器
                .setOnAudioFocusChangeListener(new MyAudioFocusChangeListener())
                .build();

        // 创建媒体会话实例
        mediaSession = new MediaSession(this, TAG_CLS);
        // 设置初始回放状态为无
        PlaybackState initState = new PlaybackState.Builder()
                .setState(PlaybackState.STATE_NONE, 0, 1.0f)
                .build();
        mediaSession.setPlaybackState(initState);
        // 设置MediaController的回调方法，处理媒体控制指令。
        mediaSession.setCallback(new MyMediaControllerCallback());
        // 激活会话，使得会话控制器能够接收指令。
        mediaSession.setActive(true);
        /*
         * 获取会话令牌，并将令牌绑定到此服务。
         *
         * 设置令牌后，客户端的ConnectionCallback将被调用。
         */
        MediaSession.Token token = mediaSession.getSessionToken();
        setSessionToken(token);

        // 创建媒体播放器实例
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            /**
             * MediaPlayer准备就绪的回调
             *
             * @param mp MediaPlayer实例
             */
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG_APP, TAG_CLS + ":onPrepared()");
                if (checkAudioFocus()) {
                    // 开始放音
                    mp.start();
                } else {
                    Toast.makeText(mContext, "音频焦点被其它程序占用", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            /**
             * MediaPlayer播放完成的回调
             *
             * @param mp MediaPlayer实例
             */
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG_APP, TAG_CLS + ":onCompletion()");
                // 重置MediaPlayer状态
                mp.reset();
                // 释放音频焦点
                manager.abandonAudioFocusRequest(focusRequest);
                // 清空播放状态
                PlaybackState playbackState = new PlaybackState.Builder()
                        .setState(PlaybackState.STATE_NONE, 0, 1.0f)
                        .build();
                mediaSession.setPlaybackState(playbackState);
            }
        });
    }

    /**
     * Name        : onStartCommand()
     * Description : 接受启动服务的指令，转为前台服务，防止界面在后台时服务被系统终止。
     *
     * @param intent  启动本服务的Intent
     * @param flags   TODO 服务标志位
     * @param startId TODO 服务ID
     * @return 返回"START_NOT_STICKY"，防止服务异常后自启，突然播放音乐。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG_APP, TAG_CLS + ":onStartCommand()");

        // 获取通知管理器实例
        MediaNotificationManager mnManager = MediaNotificationManager.getInstance(mContext);
        // 获取通知实例
        Notification notification = mnManager.createNotification();
        // 启动前台服务，并绑定通知。
        startForeground(100, notification);
        return START_NOT_STICKY;
    }

    /**
     * Name        : onGetRoot()
     * Description : 本服务连接成功时执行该回调方法。
     *
     * @param clientPackageName
     * @param clientUid
     * @param rootHints
     * @return 返回不为Null时表示连接成功
     */
    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        Log.d(TAG_APP, TAG_CLS + ":onGetRoot()");
        return new BrowserRoot(TAG_CLS, null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowser.MediaItem>> result) {
        Log.d(TAG_APP, TAG_CLS + ":onLoadChildren()");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG_APP, TAG_CLS + ":onDestroy()");

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mediaSession != null) {
            mediaSession.release();
            mediaSession = null;
        }
        super.onDestroy();
    }

    // 检查音频焦点
    private boolean checkAudioFocus() {
        if (1 < 2) {
            return true;
        }
        // 使用音频管理器请求音频焦点
        int result = manager.requestAudioFocus(focusRequest);
        // 根据返回值执行进一步操作
        switch (result) {
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                Log.d(TAG_APP, TAG_CLS + ":音频焦点请求成功");
                return true;
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                Log.w(TAG_APP, TAG_CLS + ":音频焦点请求成功（延迟）");
                return false;
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                // “正在通话”等情况会导致音频焦点请求失败
                Log.w(TAG_APP, TAG_CLS + ":音频焦点请求失败");
                return false;
            default:
                Log.e(TAG_APP, TAG_CLS + ":音频焦点状态未知");
                return false;
        }
    }

    // 播放回调，处理控制器的指令。
    class MyMediaControllerCallback extends MediaSession.Callback {
        @Override
        public void onPlay() {
            Log.d(TAG_APP, TAG_CLS + ":onPlay()");

            // 如果获取音频焦点失败，退出当前方法。
            if (!checkAudioFocus()) {
                return;
            }

            // 继续MediaPlayer的播放动作
            mediaPlayer.start();
            // 设置MediaSession的状态为暂停
            PlaybackState playbackState = new PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PLAYING, 0, 1.0f)
                    .build();
            mediaSession.setPlaybackState(playbackState);
        }

        /**
         * 控制器端调用"playFromUri()"方法时触发此回调
         *
         * @param uri    "playFromUri()"传入的参数
         * @param extras "playFromUri()"传入的参数
         */
        @Override
        public void onPlayFromUri(Uri uri, Bundle extras) {
            Log.d(TAG_APP, TAG_CLS + ":onPlayFromUri()");

            // 重置MediaPlayer状态
            mediaPlayer.reset();
            // 设置新的数据源，并准备播放。
            try {
                mediaPlayer.setDataSource(MusicService.this, uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.prepareAsync();

            // 设置MediaSession的播放状态
            PlaybackState playbackState = new PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PLAYING, 0, 1.0f)
                    .setActions(PlaybackState.ACTION_PLAY)
                    .build();
            mediaSession.setPlaybackState(playbackState);

            if (extras == null) {
                return;
            }
            String title = extras.getString("Title");
            String artist = extras.getString("Artist");
            Bitmap cover = MusicUtil.getMusicCover(uri.toString(), mContext);
            // 设置MediaSession的媒体属性
            MediaMetadata metadata = new MediaMetadata.Builder()
                    .putString(MediaMetadata.METADATA_KEY_TITLE, title)
                    .putString(MediaMetadata.METADATA_KEY_ARTIST, artist)
                    .putBitmap(MediaMetadata.METADATA_KEY_ART, cover)
                    .build();
            mediaSession.setMetadata(metadata);
        }

        @Override
        public void onPause() {
            Log.d(TAG_APP, TAG_CLS + ":onPause()");

            // 暂停MediaPlayer
            mediaPlayer.pause();
            // 设置MediaSession的状态为暂停
            PlaybackState playbackState = new PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PAUSED, 0, 1.0f)
                    .build();
            mediaSession.setPlaybackState(playbackState);
        }
    }

    // “音频焦点改变”事件监听器
    class MyAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {

        /**
         * Name        : onAudioFocusChange()
         * Description : 音频焦点发生变化时触发此方法。
         *
         * @param focusChange 表示当前程序的焦点状态
         */
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.d(TAG_APP, TAG_CLS + ":重新获取持久音频焦点");
                    mediaSession.getController().getTransportControls().play();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                    Log.d(TAG_APP, TAG_CLS + ":重新获取临时音频焦点");
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                    Log.d(TAG_APP, TAG_CLS + ":重新获取临时音频焦点，且其它音频流的音量降低。");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.d(TAG_APP, TAG_CLS + ":持久失去音频焦点");
                    // 停止放音操作
                    mediaSession.getController().getTransportControls().pause();
                    /*
                     * 释放音频焦点
                     *
                     * Android 9.0以下系统中失去持久音频焦点后，一旦其它程序释放持久焦点，本程序会再次收到
                     * "AUDIOFOCUS_GAIN"事件，继续播放。
                     * 为了防止对用户造成困扰，持久失去焦点后此处自主释放音频焦点，从此不再接收任何焦点改变事件。
                     */
                    manager.abandonAudioFocusRequest(focusRequest);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.d(TAG_APP, TAG_CLS + ":暂时失去音频焦点");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.d(TAG_APP, TAG_CLS + ":暂时失去音频焦点，且本程序应当降低音量播放。");
                    break;
                default:
                    Log.w(TAG_APP, TAG_CLS + ":收到焦点改变事件，但未处理。");
                    break;
            }
        }
    }
}
