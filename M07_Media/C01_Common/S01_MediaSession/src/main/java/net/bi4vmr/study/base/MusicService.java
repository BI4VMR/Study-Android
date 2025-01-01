package net.bi4vmr.study.base;

import android.content.Intent;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.IBinder;
import android.service.media.MediaBrowserService;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Name        : MusicService
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-03-10 17:09
 * <p>
 * Description : 音乐服务。
 */
public class MusicService extends MediaBrowserService {

    private static final String TAG = "TestApp-" + MusicService.class.getSimpleName();

    private MediaSession mediaSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "OnCreate.");

        /*
         * Name        : MediaSession构造方法
         * <p>
         * Description : 创建MediaSession实例，第二参数为媒体Tag，用于标识服务的身份。
         *
         * @param context 上下文环境。
         * @param tag     媒体Tag，用于标识服务的身份。
         */
        mediaSession = new MediaSession(this, "MusicService");
        // 注册MediaController的回调方法，接收媒体控制指令。
        mediaSession.setCallback(new MyControllerCallback());
        // 激活会话，表明当前服务已经就绪，可以接受控制。
        mediaSession.setActive(true);

        /*
         * 获取会话令牌，并将令牌绑定到此服务。
         *
         * 设置令牌后，客户端的ConnectionCallback将被调用，发送令牌给客户端。
         */
        MediaSession.Token token = mediaSession.getSessionToken();
        setSessionToken(token);

        // 设置初始的媒体信息与播放状态
        MediaMetadata metadata = new MediaMetadata.Builder()
                .putString(MediaMetadata.METADATA_KEY_TITLE, "歌曲标题...")
                .putString(MediaMetadata.METADATA_KEY_ALBUM, "专辑信息...")
                .build();
        mediaSession.setMetadata(metadata);
        PlaybackState state = new PlaybackState.Builder()
                .setState(PlaybackState.STATE_PLAYING, 1000L, 1.0F)
                .build();
        mediaSession.setPlaybackState(state);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind.");
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy.");
        // 服务销毁时释放资源
        if (mediaSession != null) {
            mediaSession.release();
        }
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        // 暂不使用
        return new BrowserRoot("MEDIA_ROOT", null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowser.MediaItem>> result) {
        // 暂不使用
    }

    /*
     * MediaController的回调类，处理客户端发送的指令。
     */
    private class MyControllerCallback extends MediaSession.Callback {

        // 播放指令
        @Override
        public void onPlay() {
            Log.i(TAG, "OnPlay.");
            // 设置播放状态为暂停
            PlaybackState state = new PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PLAYING, 2000L, 1.0F)
                    .build();
            mediaSession.setPlaybackState(state);
            // 向实际控制播放的组件发出指令，此处省略相关代码。
        }

        // 暂停指令
        @Override
        public void onPause() {
            Log.i(TAG, "OnPause.");
            // 设置播放状态为暂停
            PlaybackState state = new PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PAUSED, 2000L, 1.0F)
                    .build();
            mediaSession.setPlaybackState(state);
            // 向实际控制播放的组件发出指令，此处省略相关代码。
        }

        @Override
        public void onSkipToPrevious() {
            Log.i(TAG, "OnPrevious.");
            // 向实际控制播放的组件发出指令，此处省略相关代码。
        }

        @Override
        public void onSkipToNext() {
            Log.i(TAG, "OnNext.");
            // 向实际控制播放的组件发出指令，此处省略相关代码。
        }
    }
}
