package net.bi4vmr.study.musicplayer.ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.PlayerActivityBinding;
import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.manager.MusicDataManager;
import net.bi4vmr.study.media_demo_musicplayer.model.MusicVO;
import net.bi4vmr.study.media_demo_musicplayer.service.MusicService;
import net.bi4vmr.study.musicplayer.adapter.MusicListAdapter;
import net.bi4vmr.study.musicplayer.viewmodel.PlaybackVM;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private final static String TAG_APP = MusicApp.TAG;
    private static final String TAG = "MainActivity";

    private PlayerActivityBinding binding;
    private MusicListAdapter adapter;
    private PlaybackBarFragment playbackBarFragment;

    // 回放ViewModel
    private PlaybackVM playbackVM;

    private MusicDataManager musicManager;
    private MediaBrowser mediaBrowser;
    private MediaController mediaController;

    @SuppressLint("SwitchIntDef")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG_APP, TAG + ":onCreate()");

        binding = PlayerActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 初始化音乐列表控件
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvMusic.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rvMusic.addItemDecoration(itemDecoration);
        adapter = new MusicListAdapter(new ArrayList<>(), this);
        binding.rvMusic.setAdapter(adapter);
        adapter.setOnItemClickListener(new MusicListAdapter.OnItemClickListener() {
            // 列表项的点击事件
            @Override
            public void onItemClick(int position, MusicVO vo) {
                Log.d(TAG_APP, TAG + ":点击表项：" + vo.toString());
                //
                Uri itemURI = Uri.parse(vo.getUri());
                // 获取额外数据
                Bundle bundle = new Bundle();
                bundle.putString("Title", vo.getTitle());
                bundle.putString("Artist", vo.getArtist());
                mediaController.getTransportControls().playFromUri(itemURI, bundle);
            }

            // 列表项的长按事件
            @Override
            public void onItemLongClick(int position, MusicVO vo) {
                Log.d(TAG_APP, TAG + ":长按表项：" + vo.toString());
            }
        });

        // 初始化回放控制栏
        playbackBarFragment = PlaybackBarFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fmPlaybackBar, playbackBarFragment);
        transaction.commit();

        // 开启服务
        Intent intent = new Intent(this, MusicService.class);
        startForegroundService(intent);

        // 获取回放ViewModel
        playbackVM = new ViewModelProvider(this).get(PlaybackVM.class);

        // 获取音乐管理器实例
        musicManager = MusicDataManager.getInstance(this);

        // 创建MediaBrowser
        mediaBrowser = new MediaBrowser(this,
                new ComponentName(this, MusicService.class),
                new MyMediaBrowserConnectionCallback(),
                null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_APP, TAG + ":onResume()");

        // 连接音乐播放服务
        mediaBrowser.connect();

        // 获取本地音乐数据，并加载至列表。
        List<MusicVO> datas = musicManager.getAudioData();
        if (datas != null) {
            adapter.reloadItem(datas);
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG_APP, TAG + ":onStop()");

        if (mediaBrowser != null) {
            mediaBrowser.disconnect();
        }

        super.onStop();
    }

    /**
     * 音频服务连接回调
     */
    class MyMediaBrowserConnectionCallback extends MediaBrowser.ConnectionCallback {

        @Override
        public void onConnected() {
            Log.d(TAG_APP, TAG + ":onConnected()");

            // 获取令牌
            MediaSession.Token token = mediaBrowser.getSessionToken();
            // 通过令牌创建媒体控制器
            mediaController = new MediaController(getApplicationContext(), token);
            // 注册媒体控制器回调，处理播放状态变更消息。
            mediaController.registerCallback(new MyMediaControllerCallback());
            // 将回放状态与媒体元数据传递给ViewModel
            playbackVM.setPlaybackState(mediaController.getPlaybackState());
            playbackVM.setMediaMetadata(mediaController.getMetadata());
            // 将控制器实例传递给回放状态栏
            playbackBarFragment.setMediaController(mediaController);
        }

        @Override
        public void onConnectionSuspended() {
            Log.d(TAG_APP, TAG + ":onConnectionSuspended()");
        }

        @Override
        public void onConnectionFailed() {
            Log.d(TAG_APP, TAG + ":onConnectionFailed()");
        }
    }

    /**
     * 媒体控制器的回调类
     */
    class MyMediaControllerCallback extends MediaController.Callback {

        /**
         * 播放状态改变回调
         *
         * @param state 播放状态
         */
        @SuppressLint("SwitchIntDef")
        @Override
        public void onPlaybackStateChanged(@Nullable PlaybackState state) {
            Log.d(TAG_APP, TAG + ":onPlaybackStateChanged()");
            // 更新回放状态LiveData
            playbackVM.setPlaybackState(state);
        }

        /**
         * 媒体元数据改变回调
         *
         * @param metadata 媒体元数据
         */
        @Override
        public void onMetadataChanged(@Nullable MediaMetadata metadata) {
            Log.d(TAG_APP, TAG + ":onMetadataChanged()");
            // 更新媒体元数据LiveData
            playbackVM.setMediaMetadata(metadata);
        }
    }
}