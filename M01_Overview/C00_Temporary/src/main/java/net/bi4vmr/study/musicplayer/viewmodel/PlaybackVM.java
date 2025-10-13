package net.bi4vmr.study.musicplayer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.PlaybackState;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Name          : PlaybackVM
 * Author        : BI4VMR
 * Date          : 2022-04-19 11:40
 * Description   : 音频回放ViewModel
 */
public class PlaybackVM extends AndroidViewModel {

    private final Context mContext;

    public PlaybackVM(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    // 回放状态LiveData
    private final MutableLiveData<PlaybackState> playbackState = new MutableLiveData<>();
    private final LiveData<PlaybackState> observePlaybackState = playbackState;

    // 媒体元数据LiveData
    private final MutableLiveData<MediaMetadata> mediaMetadata = new MutableLiveData<>();
    private final LiveData<MediaMetadata> observeMediaMetadata = mediaMetadata;

    /**
     * Name        设置回放状态
     * Author      BI4VMR
     * Date        2022-4-19 22:56
     * Description 设置回放状态（需要同步调用）。
     *
     * @param state 新的回放状态
     */
    public void setPlaybackState(PlaybackState state) {
        playbackState.setValue(state);
    }

    /**
     * Name        设置媒体元数据
     * Author      BI4VMR
     * Date        2022-4-19 22:56
     * Description  设置媒体元数据（需要同步调用）。
     *
     * @param metadata 新的媒体元数据
     */
    public void setMediaMetadata(MediaMetadata metadata) {
        mediaMetadata.setValue(metadata);
    }

    public LiveData<PlaybackState> getPlaybackState() {
        return observePlaybackState;
    }

    public LiveData<MediaMetadata> getMediaMetadata() {
        return observeMediaMetadata;
    }
}
