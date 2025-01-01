package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private final static String SERVICE_PKG = "net.bi4vmr.study.media.common.mediasession";
    private final static String SERVICE_NAME = "net.bi4vmr.study.base.MusicService";

    private TestuiBaseBinding binding;

    private MediaBrowser mediaBrowser;
    private MediaController mediaController;

    private boolean serviceReady = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnConnect.setOnClickListener(v -> testConnect());
        binding.btnDisconnect.setOnClickListener(v -> testDisconnect());
        binding.btnPlay.setOnClickListener(v -> testPlay());
        binding.btnPrev.setOnClickListener(v -> testPrev());
        binding.btnNext.setOnClickListener(v -> testNext());
    }

    // 连接服务
    private void testConnect() {
        binding.tvLog.append("\n--- 连接服务 ---\n");
        Log.i(TAG, "--- 连接服务 ---");

        if (!serviceReady) {
            // 创建MediaBrowser实例，连接服务端。
            mediaBrowser = new MediaBrowser(this,
                    new ComponentName(SERVICE_PKG, SERVICE_NAME),
                    new MyConnectionCallback(),
                    null);
            // 连接MediaSession服务
            mediaBrowser.connect();
        }
    }

    // 断开服务
    private void testDisconnect() {
        binding.tvLog.append("\n--- 断开服务 ---\n");
        Log.i(TAG, "--- 断开服务 ---");

        if (serviceReady) {
            mediaBrowser.disconnect();
            serviceReady = false;
        }
    }

    // 播放/暂停
    private void testPlay() {
        binding.tvLog.append("\n--- 播放/暂停 ---\n");
        Log.i(TAG, "--- 播放/暂停 ---");

        PlaybackState state = mediaController.getPlaybackState();
        if (state != null) {
            int stateIndex = state.getState();
            // 如果当前是播放状态，则发出暂停指令；反之亦然。
            if (stateIndex == PlaybackState.STATE_PLAYING) {
                mediaController.getTransportControls().pause();
            } else if (stateIndex == PlaybackState.STATE_PAUSED) {
                mediaController.getTransportControls().play();
            }
        }
    }

    // 上一曲
    private void testPrev() {
        binding.tvLog.append("\n--- 上一曲 ---\n");
        Log.i(TAG, "--- 上一曲 ---");

        mediaController.getTransportControls().skipToPrevious();
    }

    // 下一曲
    private void testNext() {
        binding.tvLog.append("\n--- 下一曲 ---\n");
        Log.i(TAG, "--- 下一曲 ---");

        mediaController.getTransportControls().skipToNext();
    }

    // 将Session中的媒体信息更新至UI
    @SuppressLint("SetTextI18n")
    private void updateMediaInfoUI(MediaMetadata metadata) {
        if (metadata == null) {
            return;
        }

        // 取出“标题”属性
        String title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
        // 取出“专辑”属性
        String album = metadata.getString(MediaMetadata.METADATA_KEY_ALBUM);
        // 更新界面
        binding.tvLog.append("标题：" + title + "\n专辑：" + album + "\n");
        Log.i(TAG, "标题：" + title + "\n专辑：" + album);
    }

    // 将Session中的播放状态更新至UI
    @SuppressLint("SetTextI18n")
    private void updatePlayStateUI(PlaybackState state) {
        if (state == null) {
            return;
        }

        // 更新界面
        binding.tvLog.append("播放状态: " + state.getState() + "\n");
        Log.i(TAG, "播放状态: " + state.getState());
    }

    /*
     * MediaSession服务连接回调
     */
    private class MyConnectionCallback extends MediaBrowser.ConnectionCallback {

        @Override
        public void onConnected() {
            Log.i(TAG, "MyConnectionCallback-OnConnected.");
            binding.tvLog.append("服务连接成功！\n");
            Log.i(TAG, "服务连接成功！");

            // 获取配对令牌
            MediaSession.Token token = mediaBrowser.getSessionToken();
            // 通过令牌创建媒体控制器
            mediaController = new MediaController(getApplicationContext(), token);

            // 服务连接完毕后，主动获取媒体信息与播放状态，同步UI显示。
            updateMediaInfoUI(mediaController.getMetadata());
            updatePlayStateUI(mediaController.getPlaybackState());

            // 注册媒体控制器回调，处理后续媒体信息与播放状态变更事件。
            mediaController.registerCallback(new MyMediaControllerCallback());

            serviceReady = true;
        }

        @Override
        public void onConnectionFailed() {
            Log.i(TAG, "MyConnectionCallback-OnConnectionFailed.");
            binding.tvLog.append("服务连接失败！\n");
            Log.i(TAG, "服务连接失败！");

            serviceReady = false;
        }
    }

    /*
     * 媒体控制器的回调接口实现类
     */
    private class MyMediaControllerCallback extends MediaController.Callback {

        // 媒体元数据改变回调
        @Override
        public void onMetadataChanged(@Nullable MediaMetadata metadata) {
            // 媒体元数据发生变更（标题，艺术家等），将其更新到界面上。
            updateMediaInfoUI(metadata);
        }

        // 回放状态改变回调
        @Override
        public void onPlaybackStateChanged(@Nullable PlaybackState state) {
            // 播放状态发生变更，将其更新到界面上。
            updatePlayStateUI(state);
        }
    }
}
