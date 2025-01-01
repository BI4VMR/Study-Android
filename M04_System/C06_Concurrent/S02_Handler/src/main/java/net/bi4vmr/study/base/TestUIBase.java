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

    // 子线程的Handler
    private Handler mSubHandler = null;

    private TestuiBaseBinding binding;

    {
        // 在子线程中初始化Handler
        new Thread(() -> {
            // 创建本线程的Looper对象
            Looper.prepare();
            // 使用当前线程的Looper创建Handler对象
            Looper looper = Looper.myLooper();
            if (looper != null) {
                mSubHandler = new Handler(looper);
            }
            // 开始事件循环
            Looper.loop();
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSendMsg1.setOnClickListener(v -> testSendMessage1());
        binding.btnSendMsg2.setOnClickListener(v -> testSendMessage2());
        binding.btnSendMsgDelay.setOnClickListener(v -> testSendMessageDelay());
        binding.btnPostCallback.setOnClickListener(v -> testPostCallback());
        binding.btnCancelMsg.setOnClickListener(v -> testCancelMessage());
        binding.btnWrap.setOnClickListener(v -> testWrap());
        binding.btnUseSubThreadHandler.setOnClickListener(v -> testUseSubThreadHandler());
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

        // 向队列发送消息1，延时4秒后执行。
        mHandler.sendEmptyMessageDelayed(MSG_TEST_01, 4000L);

        // 向队列发送消息2，延时8秒后执行。
        Message msg = Message.obtain();
        msg.what = MSG_TEST_02;
        msg.arg1 = -100;
        mHandler.sendMessageDelayed(msg, 8000L);
    }

    // 向队列中提交回调方法
    private void testPostCallback() {
        Log.i(TAG, "--- 向队列中提交回调方法 ---");
        binding.tvLog.append("\n--- 向队列中提交回调方法 ---\n");

        // 向队列提交回调方法，立刻执行。
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "HandleCallback A");
            }
        });

        // 向队列提交回调方法，延时4秒后执行。
        mHandler.postDelayed(() -> {
            Log.i(TAG, "HandleCallback B");
        }, 4000L);
    }

    // 移除队列中尚未执行的消息1
    private void testCancelMessage() {
        Log.i(TAG, "--- 移除队列中尚未执行的消息1 ---");
        binding.tvLog.append("\n--- 移除队列中尚未执行的消息1 ---\n");

        // 移除所有编号为1的消息
        mHandler.removeMessages(MSG_TEST_01);
    }

    // 更新UI的快捷方法
    private void testWrap() {
        Log.i(TAG, "--- 更新UI的快捷方法 ---");
        binding.tvLog.append("\n--- 更新UI的快捷方法 ---\n");

        // 向View的事件队列中提交回调方法。
        binding.tvLog.post(new Runnable() {

            @Override
            public void run() {
                binding.tvLog.append("\nCall View.post()");
            }
        });

        // 向View的事件队列中提交回调方法，延时执行。
        binding.tvLog.postDelayed(new Runnable() {

            @Override
            public void run() {
                binding.tvLog.append("\nCall View.postDelayed()");
            }
        }, 5000L);

        // 向Activity的事件队列中提交回调方法。
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                binding.tvLog.append("\nCall Activity.runOnUiThread()");
            }
        });
    }

    // 使用子线程的Handler
    private void testUseSubThreadHandler() {
        Log.i(TAG, "--- 使用子线程的Handler ---");
        binding.tvLog.append("\n--- 使用子线程的Handler ---\n");

        // 向队列提交回调方法，立刻执行。
        mSubHandler.post(() -> Log.i(TAG, "HandleCallback A"));

        // 向队列提交回调方法，延时4秒后执行。
        mSubHandler.postDelayed(() -> Log.i(TAG, "HandleCallback B"), 4000L);
    }
}
