package net.bi4vmr.study.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    private LocalBroadcastManager manager;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> testRegister());
        binding.btnUnregister.setOnClickListener(v -> testUnregister());
        binding.btnSend.setOnClickListener(v -> testSendBroadcast());

        // 获取LocalBroadcastManager实例
        manager = LocalBroadcastManager.getInstance(this);
    }

    // 注册广播监听器
    private void testRegister() {
        binding.tvLog.append("\n--- 注册广播监听器 ---\n");
        Log.i(TAG, "--- 注册广播监听器 ---");

        // 注册广播接收器
        IntentFilter filter = new IntentFilter("net.bi4vmr.MY_BROADCAST");
        receiver = new MyReceiver();
        manager.registerReceiver(receiver, filter);
    }

    // 注销广播监听器
    private void testUnregister() {
        binding.tvLog.append("\n--- 注销广播监听器 ---\n");
        Log.i(TAG, "--- 注销广播监听器 ---");

        // 注销广播接收器
        manager.unregisterReceiver(receiver);
    }

    // 发送广播
    private void testSendBroadcast() {
        binding.tvLog.append("\n--- 发送广播 ---\n");
        Log.i(TAG, "--- 发送广播 ---");

        Intent intent = new Intent("net.bi4vmr.MY_BROADCAST");
        manager.sendBroadcast(intent);
    }

    /*
     * 自定义广播接收器
     */
    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            binding.tvLog.append("MyReceiver接收到广播。\n");
            Log.i(TAG, "MyReceiver接收到广播。");
        }
    }
}
