package net.bi4vmr.study.base;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    // 音频管理器
    private AudioManager manager;
    // 音频焦点请求（临时）
    private AudioFocusRequest temporaryRequest;
    // 音频焦点请求（永久）
    private AudioFocusRequest permanentRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        Button btnRequestTemporary = findViewById(R.id.btnRequestTemporary);
        Button btnAbandonTemporary = findViewById(R.id.btnAbandonTemporary);
        Button btnRequestPermanent = findViewById(R.id.btnRequestPermanent);
        Button btnAbandonPermanent = findViewById(R.id.btnAbandonPermanent);

        // 通过Context获取音频管理器
        manager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);

        // 请求焦点的音频类型，此参数影响系统是否允许当前音源获取音频焦点，各音频类型的优先级由Framework决定。
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        // 按钮：请求临时音频焦点
        btnRequestTemporary.setOnClickListener(v -> {
            // 构建请求实例，请求获得临时音频焦点。
            temporaryRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                    // 设置音频属性，是可选参数，默认值为Usage为USAGE_MEDIA的实例。
                    .setAudioAttributes(attributes)
                    // 设置音频焦点改变监听器
                    .setOnAudioFocusChangeListener(new AudioFocusListener())
                    .build();

            // 使用音频管理器请求音频焦点
            int result = manager.requestAudioFocus(temporaryRequest);
            // 根据返回值执行进一步操作
            switch (result) {
                /*
                 * 焦点请求成功
                 *
                 * 此时可以播放媒体。
                 */
                case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                    Log.i("myapp", "临时音频焦点请求成功");
                    break;
                /*
                 * 焦点请求失败（延迟）
                 *
                 * 此时不应当播放媒体。该数值表示当前系统不能给当前程序授予焦点，但是稍后可以授予，这种情况
                 * 常见于TTS正在播报时有程序申请焦点。
                 *
                 * 如果AudioFocusRequest请求包含"setAcceptsDelayedFocusGain(false)"配置项，则系统
                 * 只会返回AUDIOFOCUS_REQUEST_FAILED，不会返回该数值。
                 */
                case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                    Log.i("myapp", "临时音频焦点请求失败（延迟）");
                    break;
                /*
                 * 焦点请求失败
                 *
                 * 此时不应当播放媒体。“正在通话”等情况会导致音频焦点请求失败。
                 */
                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                    Log.i("myapp", "临时音频焦点请求失败");
                    break;
            }
        });

        // 按钮：放弃临时音频焦点
        btnAbandonTemporary.setOnClickListener(v -> {
            if (temporaryRequest == null) {
                return;
            }

            manager.abandonAudioFocusRequest(temporaryRequest);
            Log.i("myapp", "已释放临时音频焦点");
        });

        // 按钮：请求持久音频焦点
        btnRequestPermanent.setOnClickListener(v -> {
            // 构建请求实例，请求获得持久音频焦点。
            permanentRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(attributes)
                    .setOnAudioFocusChangeListener(new AudioFocusListener())
                    .build();

            int result = manager.requestAudioFocus(permanentRequest);
            switch (result) {
                case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                    Log.i("myapp", "持久音频焦点请求成功");
                    break;
                case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                    Log.i("myapp", "持久音频焦点请求失败（延迟）");
                    break;
                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                    Log.i("myapp", "持久音频焦点请求失败");
                    break;
            }
        });

        // 按钮：放弃持久音频焦点
        btnAbandonPermanent.setOnClickListener(v -> {
            if (permanentRequest == null) {
                return;
            }

            manager.abandonAudioFocusRequest(permanentRequest);
            Log.i("myapp", "已释放持久音频焦点");
        });
    }

    /* “音频焦点改变”事件监听器 */
    class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {

        /**
         * Name        : onAudioFocusChange()
         * <p>
         * Description : 音频焦点改变事件
         *
         * @param focusChange 当前程序的焦点状态
         */
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                /*
                 * 其它程序获得临时焦点并释放后，当前程序获得焦点。
                 *
                 * 此时可以继续媒体播放动作。
                 *
                 * 注意：此处仅为焦点请求成功后的焦点改变事件回调，当前程序首次请求焦点成功时不会触发本事件。
                 */
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.i("myapp", "重新获得持久音频焦点");
                    break;
                /*
                 * 持久失去音频焦点，其它程序成功请求持久焦点后，当前程序进入该状态。
                 *
                 * 由于其它程序可能长时间播放，此时应当记录播放进度，释放资源，直到用户对本程序发出继续播放的指令。
                 */
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.i("myapp", "持久失去音频焦点");
                    /*
                     * 释放音频焦点
                     *
                     * Android 9.0以下的系统中，当前程序失去持久音频焦点后，一旦其它程序释放持久焦点，本程序会再次收到
                     * "AUDIOFOCUS_GAIN"事件，从而继续播放。
                     * 为了防止对用户造成困扰，持久失去焦点后此处应当主动释放音频焦点，从此不再接收任何焦点改变事件。
                     */
                    if (permanentRequest != null) {
                        manager.abandonAudioFocusRequest(permanentRequest);
                    }
                    break;
                /*
                 * 暂时失去音频焦点，其它程序成功请求临时焦点后，当前程序进入该状态。
                 *
                 * 由于其它程序播放时间较短，此时应当暂停播放，以便收到恢复消息时快速恢复播放。
                 */
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.i("myapp", "暂时失去音频焦点");
                    break;
                /*
                 * 暂时失去音频焦点，其它程序成功请求"Duck"方式的临时焦点后，当前程序进入该状态。
                 *
                 * 系统是否降低音量视具体实现而定，对于AOSP而言：
                 * Android 8.0及更高版本的系统，会自动降低本程序的媒体音量，开发者不需要作任何操作。
                 * Android 8.0以下版本的系统，需要自行实现降低音量的功能。
                 */
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.i("myapp", "暂时失去音频焦点，且本程序应当降低音量播放。");
                    break;
            }
        }
    }
}
