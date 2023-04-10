package net.bi4vmr.study.base;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    BroadcastReceiver receiver1;
    BroadcastReceiver receiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        Button btSend = findViewById(R.id.btSend);

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

        // 发送按钮
        btSend.setOnClickListener(v -> {
            Intent intent = new Intent("net.bi4vmr.MY_BROADCAST_MESSAGE");
            sendOrderedBroadcast(intent, null);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver1);
        unregisterReceiver(receiver2);
    }
}
