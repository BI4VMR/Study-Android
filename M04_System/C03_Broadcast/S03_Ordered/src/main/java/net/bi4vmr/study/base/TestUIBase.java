package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
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

    private BroadcastReceiver receiver1;
    private BroadcastReceiver receiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnSend.setOnClickListener(v -> testSendBroadcast());

        // 注册第一个广播接收器
        IntentFilter filter1 = new IntentFilter("net.bi4vmr.MY_BROADCAST_MESSAGE");
        receiver1 = new MyReceiver1();
        registerReceiver(receiver1, filter1);

        // 注册第二个广播接收器
        IntentFilter filter2 = new IntentFilter("net.bi4vmr.MY_BROADCAST_MESSAGE");
        // 设置优先级
        // filter2.setPriority(100);
        receiver2 = new MyReceiver2();
        registerReceiver(receiver2, filter2);
    }

    // 发送广播
    private void testSendBroadcast() {
        binding.tvLog.append("\n--- 发送广播 ---\n");
        Log.i(TAG, "--- 发送广播 ---");

        Intent intent = new Intent("net.bi4vmr.MY_BROADCAST_MESSAGE");
        sendOrderedBroadcast(intent, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver1);
        unregisterReceiver(receiver2);
    }
}
