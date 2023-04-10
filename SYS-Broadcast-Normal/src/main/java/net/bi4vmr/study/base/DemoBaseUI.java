package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    private TextView tvMsg;

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        tvMsg = findViewById(R.id.tvMsg);
        Button btSend = findViewById(R.id.btSend);

        // 创建过滤器，填写要接收的广播类型。
        IntentFilter filter = new IntentFilter();
        filter.addAction("net.bi4vmr.MY_BROADCAST");
        // 创建接收器实例，并进行动态注册。
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);

        // 发送广播按钮
        btSend.setOnClickListener(v -> {
            // 创建Intent实例，设置Action，并携带额外的数据。
            Intent intent = new Intent();
            intent.setAction("net.bi4vmr.MY_BROADCAST");
            intent.putExtra("MSG", "MyBroadcast.");
            // 发送广播
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 组件被销毁时，注销广播接收器。
        unregisterReceiver(receiver);
    }

    // 自定义广播接收器
    class MyReceiver extends BroadcastReceiver {

        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String msg = intent.getStringExtra("MSG");
            // 接收到广播时，需要执行的操作。
            tvMsg.setText("收到广播，数据：" + msg);
            Log.i("myapp", "Action:" + action + "，携带数据：" + msg);
        }
    }
}
