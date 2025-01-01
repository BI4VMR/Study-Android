package net.bi4vmr.study.base;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    // 音频管理器
    private AudioManager manager;
    // 请求焦点的音频类型
    private AudioAttributes attributes;
    // 音频焦点请求（临时）
    private AudioFocusRequest temporaryRequest;
    // 音频焦点请求（持久）
    private AudioFocusRequest permanentRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 通过Context获取音频管理器
        manager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
        // 设置需要请求焦点的音频类型，此参数影响系统是否允许当前音源获取音频焦点，各音频类型的优先级由Framework决定。
        attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        binding.btnTemporaryRequest.setOnClickListener(v -> testTemporaryRequest());
        binding.btnTemporaryAbandon.setOnClickListener(v -> testTemporaryAbandon());
        binding.btnPersistenceRequest.setOnClickListener(v -> testPersistenceRequest());
        binding.btnPersistenceAbandon.setOnClickListener(v -> testPersistenceAbandon());
    }

    // 请求临时焦点
    private void testTemporaryRequest() {
        binding.tvLog.append("\n--- 请求临时音频焦点 ---\n");
        Log.i(TAG, "--- 请求临时音频焦点 ---");

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
             * 音频焦点请求成功
             *
             * 此时我们的程序可以开始播放媒体。
             */
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                binding.tvLog.append("临时音频焦点请求成功\n");
                Log.i(TAG, "临时音频焦点请求成功");
                break;
            /*
             * 音频焦点请求失败
             *
             * 此时我们的程序不应当播放媒体。
             *
             * 这种情况常见于“正在通话”等高优先级程序正在放音。
             */
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                binding.tvLog.append("临时音频焦点请求失败\n");
                Log.i(TAG, "临时音频焦点请求失败");
                break;
            /*
             * 音频焦点请求失败（暂时）
             *
             * 该数值表示当前时刻系统不能给当前程序授予焦点，但是稍后可以授予。此时我们的程序不应当播放媒体，可以
             * 放弃本次放音操作，或者稍后重新尝试请求焦点。
             *
             * 这种情况常见于TTS正在播报时，我们的程序恰好申请了音频焦点。
             *
             * 如果AudioFocusRequest请求包含"setAcceptsDelayedFocusGain(false)"配置项，则系统只会返回AU
             * DIOFOCUS_REQUEST_FAILED，不会返回该数值。
             */
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                binding.tvLog.append("临时音频焦点请求失败（延迟）\n");
                Log.i(TAG, "临时音频焦点请求失败（延迟）");
                break;
        }
    }

    // 放弃临时音频焦点
    private void testTemporaryAbandon() {
        binding.tvLog.append("\n--- 放弃临时音频焦点 ---\n");
        Log.i(TAG, "--- 放弃临时音频焦点 ---");

        if (temporaryRequest == null) {
            binding.tvLog.append("还没有请求临时音频焦点！\n");
            Log.i(TAG, "还没有请求临时音频焦点！");
            return;
        }

        manager.abandonAudioFocusRequest(temporaryRequest);
        binding.tvLog.append("已释放临时音频焦点\n");
        Log.i(TAG, "已释放临时音频焦点");
    }

    // 请求持久音频焦点
    private void testPersistenceRequest() {
        binding.tvLog.append("\n--- 请求持久音频焦点 ---\n");
        Log.i(TAG, "--- 请求持久音频焦点 ---");

        permanentRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(attributes)
                .setOnAudioFocusChangeListener(new AudioFocusListener())
                .build();

        int result = manager.requestAudioFocus(permanentRequest);
        switch (result) {
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                binding.tvLog.append("持久音频焦点请求成功\n");
                Log.i(TAG, "持久音频焦点请求成功");
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                binding.tvLog.append("持久音频焦点请求失败（延迟）\n");
                Log.i(TAG, "持久音频焦点请求失败（延迟）");
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                binding.tvLog.append("持久音频焦点请求失败\n");
                Log.i(TAG, "持久音频焦点请求失败");
                break;
        }
    }

    // 放弃持久音频焦点
    private void testPersistenceAbandon() {
        binding.tvLog.append("\n--- 放弃持久音频焦点 ---\n");
        Log.i(TAG, "--- 放弃持久音频焦点 ---");

        if (permanentRequest == null) {
            binding.tvLog.append("还没有请求持久音频焦点！\n");
            Log.i(TAG, "还没有请求持久音频焦点！");
            return;
        }

        manager.abandonAudioFocusRequest(permanentRequest);
        binding.tvLog.append("已释放持久音频焦点\n");
        Log.i(TAG, "已释放持久音频焦点");
    }

    /* “音频焦点改变”事件监听器 */
    private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {

        /**
         * Name        : onAudioFocusChange()
         * <p>
         * Description : 音频焦点改变事件。
         *
         * @param focusChange 当前程序的焦点状态
         */
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                /*
                 * 当前程序重新获得音频焦点
                 *
                 * 当前程序首先请求到持久音频焦点，随后其它程序又请求临时焦点，使用完毕后将其释放，当前程序就会收
                 * 到该事件，此处应当恢复先前的媒体播放。
                 *
                 * 注意：此处仅为焦点请求成功后的焦点改变事件回调，当前程序首次请求焦点成功时不会触发本事件。
                 */
                case AudioManager.AUDIOFOCUS_GAIN:
                    binding.tvLog.append("重新获得持久音频焦点\n");
                    Log.i(TAG, "重新获得持久音频焦点");
                    break;
                /*
                 * 当前程序持久失去音频焦点
                 *
                 * 其它程序成功请求持久焦点后，当前程序就会收到该事件。由于其它程序可能长时间播放媒体，此时当前程
                 * 序应当暂停播放并记录进度，直到用户通过UI点击等方式对本程序发出“继续播放”指令时，再恢复播放。
                 */
                case AudioManager.AUDIOFOCUS_LOSS:
                    binding.tvLog.append("持久失去音频焦点\n");
                    Log.i(TAG, "持久失去音频焦点");
                    /*
                     * 释放音频焦点
                     *
                     * Android 9.0以下的系统中，当前程序失去持久音频焦点后，一旦其它程序释放持久焦点，本程序会再次收到
                     * "AUDIOFOCUS_GAIN"事件，从而继续播放。
                     * 为了防止对用户造成困扰，持久失去焦点后此处应当主动释放音频焦点，从此不再接收任何焦点改变事件。
                     */
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                        if (permanentRequest != null) {
                            manager.abandonAudioFocusRequest(permanentRequest);
                        }
                    }
                    break;
                /*
                 * 当前程序暂时失去音频焦点
                 *
                 * 其它程序成功请求临时焦点后，当前程序就会收到该事件。由于其它程序的意图为短时间播放媒体，此时当
                 * 前程序应当先暂停播放，后续收到"AUDIOFOCUS_GAIN"事件时自行恢复播放。
                 */
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    binding.tvLog.append("暂时失去音频焦点\n");
                    Log.i(TAG, "暂时失去音频焦点");
                    break;
                /*
                 * 当前程序暂时失去音频焦点（允许降低音量继续播放）
                 *
                 * 其它程序成功请求允许降低音量的临时音频焦点后，当前程序就会收到该事件。
                 *
                 * 系统是否支持自动降低音量需要视具体实现而定，对于AOSP而言：
                 * Android 8.0及更高版本的系统，能够自动降低本程序的媒体音量，开发者不需要额外操作。
                 * Android 8.0以下版本的系统，需要开发者自行实现降低音量的功能。
                 */
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    binding.tvLog.append("暂时失去音频焦点，且本程序应当降低音量播放。\n");
                    Log.i(TAG, "暂时失去音频焦点，且本程序应当降低音量播放。");
                    break;
            }
        }
    }
}
