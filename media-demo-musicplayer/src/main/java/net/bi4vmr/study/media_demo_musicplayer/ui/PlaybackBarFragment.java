package net.bi4vmr.study.media_demo_musicplayer.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.media_demo_musicplayer.R;
import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.databinding.PlaybackBarFragmentBinding;
import net.bi4vmr.study.media_demo_musicplayer.viewmodel.PlaybackVM;


public class PlaybackBarFragment extends Fragment {

    private static final String TAG_APP = MusicApp.TAG;
    private static final String TAG = "PlaybackBarFragment";

    // 全局环境
    private Context mContext;

    private PlaybackBarFragmentBinding binding;

    // 回放ViewModel
    private PlaybackVM playbackVM;

    private MediaControllerCompat mediaController;

    public PlaybackBarFragment() {
        // 保持空方法体
    }

    public static PlaybackBarFragment newInstance() {
        return new PlaybackBarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取全局环境
        mContext = getContext();
        // 传入Activity获取实例，保持数据与Activity一致。
        playbackVM = new ViewModelProvider(requireActivity()).get(PlaybackVM.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = PlaybackBarFragmentBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObserver();
    }

    @SuppressLint("SwitchIntDef")
    private void initView() {
        // 播放按钮点击事件
        binding.ibPlay.setOnClickListener(v -> {
            // 如果MediaController为Null，退出当前方法。
            if (mediaController == null) {
                Log.e(TAG_APP, TAG + ":MediaController为空");
                return;
            }

            PlaybackStateCompat playbackState = mediaController.getPlaybackState();
            if (playbackState == null) {
                Log.e(TAG_APP, TAG + ":PlaybackState为空");
                return;
            }

            switch (playbackState.getState()) {
                case PlaybackStateCompat.STATE_NONE:
                    Toast.makeText(mContext, "没有待播放的音乐", Toast.LENGTH_SHORT).show();
                    break;
                case PlaybackStateCompat.STATE_PLAYING:
                    mediaController.getTransportControls().pause();
                    break;
                case PlaybackStateCompat.STATE_PAUSED:
                    mediaController.getTransportControls().play();
                    break;
            }
        });
    }

    @SuppressLint("SwitchIntDef")
    private void initObserver() {
        // 观察回放状态LiveData
        playbackVM.getPlaybackState().observe(getViewLifecycleOwner(), state -> {
            // 若值为Null，退出当前方法。
            if (state == null) {
                Log.w(TAG_APP, TAG + ":回放状态已变更，但值为空。");
                return;
            }

            // 回放状态不为空时，更新UI。
            switch (state.getState()) {
                case PlaybackStateCompat.STATE_NONE:
                    Log.d(TAG_APP, TAG + ":回放状态：NONE");
                    binding.ibPlay.setImageResource(R.drawable.ic_playback_play);
                    break;
                case PlaybackStateCompat.STATE_PLAYING:
                    Log.d(TAG_APP, TAG + ":回放状态：PLAYING");
                    // 播放时，将图标设为暂停。
                    binding.ibPlay.setImageResource(R.drawable.ic_playback_pause);
                    break;
                case PlaybackStateCompat.STATE_PAUSED:
                    Log.d(TAG_APP, TAG + ":回放状态：PAUSED");
                    // 暂停时，将图标设为播放。
                    binding.ibPlay.setImageResource(R.drawable.ic_playback_play);
                    break;
                default:
                    Log.w(TAG_APP, TAG + ":回放状态已变更，但未被处理。");
                    break;
            }
        });

        // 观察媒体元数据LiveData
        playbackVM.getMediaMetadata().observe(getViewLifecycleOwner(), metadata -> {
            // 若值为Null，退出当前方法。
            if (metadata == null) {
                Log.w(TAG_APP, TAG + ":媒体元数据已变更，但值为空。");
                binding.tvTitle.setText("无曲目");
                binding.tvArtist.setText("-");
                binding.ivCover.setImageResource(R.drawable.ic_playback_unknown_cover);
                return;
            }

            // 媒体元数据不为空时，更新UI。
            String title = metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
            String artist = metadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
            Bitmap cover = metadata.getBitmap(MediaMetadataCompat.METADATA_KEY_ART);
            binding.tvTitle.setText(title);
            binding.tvArtist.setText(artist);
            binding.ivCover.setImageBitmap(cover);
        });
    }

    public void setMediaController(MediaControllerCompat mediaController) {
        this.mediaController = mediaController;
    }
}