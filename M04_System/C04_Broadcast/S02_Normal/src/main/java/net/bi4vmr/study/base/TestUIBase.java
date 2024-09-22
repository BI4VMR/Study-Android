package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

@SuppressLint("UnspecifiedRegisterReceiverFlag")
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> testRegister());
        binding.btnUnregister.setOnClickListener(v -> testUnregister());
        binding.btnSend.setOnClickListener(v -> testSendBroadcast());
    }

    // 注册广播监听器
    private void testRegister() {
        binding.tvLog.append("\n--- 注册广播监听器 ---\n");
        Log.i(TAG, "--- 注册广播监听器 ---");

        // 创建过滤器，填写要接收的广播类型。
        IntentFilter filter = new IntentFilter();
        filter.addAction("net.bi4vmr.MY_BROADCAST");
        // 创建接收器实例，并进行动态注册。
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
    }

    // 注销广播监听器
    private void testUnregister() {
        binding.tvLog.append("\n--- 注销广播监听器 ---\n");
        Log.i(TAG, "--- 注销广播监听器 ---");

        // 注销广播接收器
        unregisterReceiver(receiver);
    }

    // 发送广播
    private void testSendBroadcast() {
        binding.tvLog.append("\n--- 发送广播 ---\n");
        Log.i(TAG, "--- 发送广播 ---");

        // 创建Intent实例，设置Action，并携带额外的数据。
        Intent intent = new Intent();
        intent.setAction("net.bi4vmr.MY_BROADCAST");
        intent.putExtra("message", "Broadcast Test.");
        // 发送广播
        sendBroadcast(intent);
    }

    /*
     * 自定义广播接收器实现类
     */
    private class MyReceiver extends BroadcastReceiver {

        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String msg = intent.getStringExtra("message");
            // 接收到广播时，需要执行的操作。
            binding.tvLog.append("MyReceiver-OnReceive. Action:" + action + "，携带数据：" + msg + "\n");
            Log.i(TAG, "MyReceiver-OnReceive. Action:" + action + "，携带数据：" + msg);
        }
    }
}
