package net.bi4vmr.study.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    // 声明常量表示不同的消息
    private final int MSG_TEST_01 = 0x01;
    private final int MSG_TEST_02 = 0x02;

    // 创建Handler对象，使用主线程的事件循环。
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        // 回调方法：当有新消息需要处理时被触发
        @Override
        public void handleMessage(@NonNull Message msg) {
            int code = msg.what;
            switch (code) {
                case MSG_TEST_01:
                    Log.d(TAG, "HandleMessage 1.");
                    binding.tvLog.append("\nHandleMessage 1.");
                    // 在此处编写收到1号消息后需要执行的逻辑...
                    break;
                case MSG_TEST_02:
                    Log.d(TAG, "HandleMessage 2. Arg1:[" + msg.arg1 + "] Arg2:[" + msg.arg2 + "]");
                    binding.tvLog.append("\nHandleMessage 2. Arg1:[" + msg.arg1 + "] Arg2:[" + msg.arg2 + "]");
                    // 在此处编写收到2号消息后需要执行的逻辑...
            }
        }
    };

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSendMsg1.setOnClickListener(v -> testSendMessage1());
        binding.btnSendMsg2.setOnClickListener(v -> testSendMessage2());
        binding.btnSendMsgDelay.setOnClickListener(v -> testSendMessageDelay());
        binding.btnCancelMsg.setOnClickListener(v -> testCancelMessage());
    }

    // 向队列中发送消息1
    private void testSendMessage1() {
        Log.i(TAG, "--- 向队列中发送消息1 ---");
        binding.tvLog.append("\n--- 向队列中发送消息1 ---\n");

        // 发送消息
        mHandler.sendEmptyMessage(MSG_TEST_01);
    }

    // 向队列中发送消息2
    private void testSendMessage2() {
        Log.i(TAG, "--- 向队列中发送消息2 ---");
        binding.tvLog.append("\n--- 向队列中发送消息2 ---\n");

        // 从系统申请一个Message对象
        Message msg = Message.obtain();
        msg.what = MSG_TEST_01;
        // 设置参数
        msg.arg1 = 114514;
        msg.arg2 = 1919810;

        // 其他形式的参数
        // msg.obj = "Hello World!";
        // msg.setData(new Bundle());

        // 发送消息
        mHandler.sendMessage(msg);
    }

    // 向队列中发送消息（延时执行）
    private void testSendMessageDelay() {
        Log.i(TAG, "--- 向队列中发送消息（延时执行） ---");
        binding.tvLog.append("\n--- 向队列中发送消息（延时执行） ---\n");

        // 向队列发送消息，延时4秒后执行。
        mHandler.sendEmptyMessageDelayed(MSG_TEST_01, 4000L);
        mHandler.sendEmptyMessageDelayed(MSG_TEST_02, 4000L);

        // 向队列发送消息，延时8秒后执行。
        Message msg = Message.obtain();
        msg.what = MSG_TEST_01;
        mHandler.sendMessageDelayed(msg, 8000L);
    }

    // 移除队列中尚未执行的消息1
    private void testCancelMessage() {
        Log.i(TAG, "--- 移除队列中尚未执行的消息1 ---");
        binding.tvLog.append("\n--- 移除队列中尚未执行的消息1 ---\n");

        // 移除所有编号为1的消息
        mHandler.removeMessages(MSG_TEST_01);
    }
}
