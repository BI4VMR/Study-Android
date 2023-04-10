package net.bi4vmr.study.bind;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBindUI extends AppCompatActivity {

    private final static String TAG = "myapp";
    private final static String SERVICE_PKG = "net.bi4vmr.study.media.common.mediasession.server";
    private final static String SERVICE_NAME = "net.bi4vmr.study.base.MusicService";

    private MediaBrowser mediaBrowser;
    private MediaController mediaController;

    private TextView tvInfo;
    private TextView tvState;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_bind);

        tvInfo = findViewById(R.id.tvInfo);
        tvState = findViewById(R.id.tvState);
        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnNext = findViewById(R.id.btnNext);

        // 创建MediaBrowser实例，连接服务端。
        mediaBrowser = new MediaBrowser(this,
                new ComponentName(SERVICE_PKG, SERVICE_NAME),
                new MyConnectionCallback(),
                null);

        // 播放按钮
        btnPlay.setOnClickListener(v -> {
            int stateIndex = mediaController.getPlaybackState().getState();
            // 如果当前是播放状态，则发出暂停指令；反之亦然。
            if (stateIndex == PlaybackState.STATE_PLAYING) {
                mediaController.getTransportControls().pause();
            } else if (stateIndex == PlaybackState.STATE_PAUSED) {
                mediaController.getTransportControls().play();
            }
        });

        // 下一曲按钮
        btnNext.setOnClickListener(v -> mediaController.getTransportControls().skipToNext());

        tvInfo.setText("服务未连接。");
        tvState.setText("服务未连接。");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 连接MediaSession服务
        mediaBrowser.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 界面不可见时，断开连接。
        if (mediaBrowser != null) {
            mediaBrowser.disconnect();
        }
    }

    // 将Session中的媒体信息更新至UI
    @SuppressLint("SetTextI18n")
    private void updateMediaInfoUI(MediaMetadata metadata) {
        // 如果媒体信息为空则清空当前显示。
        if (metadata == null) {
            tvInfo.setText(null);
            return;
        }

        // 取出“标题”属性
        String title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
        // 取出“专辑”属性
        String album = metadata.getString(MediaMetadata.METADATA_KEY_ALBUM);
        // 更新界面
        tvInfo.setText("标题：" + title + "\n专辑：" + album);
        Log.i(TAG, "标题：" + title + "\n专辑：" + album);
    }

    // 将Session中的播放状态更新至UI
    @SuppressLint("SetTextI18n")
    private void updatePlayStateUI(PlaybackState state) {
        // 如果播放状态为空则清空当前显示。
        if (state == null) {
            tvState.setText(null);
            return;
        }

        // 更新界面
        tvState.setText("播放状态: " + state.getState());
        Log.i(TAG, "播放状态: " + state.getState());
    }

    // MediaSession服务连接回调
    class MyConnectionCallback extends MediaBrowser.ConnectionCallback {

        @Override
        public void onConnected() {
            Log.i(TAG, "MediaBrowserCallback-OnConnected.");
            // 获取配对令牌
            MediaSession.Token token = mediaBrowser.getSessionToken();
            // 通过令牌创建媒体控制器
            mediaController = new MediaController(getApplicationContext(), token);

            // 服务连接完毕后，主动获取媒体信息与播放状态，同步UI显示。
            updateMediaInfoUI(mediaController.getMetadata());
            updatePlayStateUI(mediaController.getPlaybackState());
            // 注册媒体控制器回调，处理后续媒体信息与播放状态变更事件。
            mediaController.registerCallback(new MyMediaControllerCallback());
        }

        @Override
        public void onConnectionFailed() {
            Log.i(TAG, "MediaBrowserCallback-OnConnectionFailed.");
            tvInfo.setText("服务连接失败！");
        }
    }

    // 媒体控制器的回调接口实现类
    class MyMediaControllerCallback extends MediaController.Callback {

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
