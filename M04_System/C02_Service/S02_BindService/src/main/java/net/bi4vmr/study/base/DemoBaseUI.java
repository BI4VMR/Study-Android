package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    private final ServiceConnection connection = new MyConnectionCallback();
    private DownloadService.DownloadBinder binder = null;
    private TextView textView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        textView = findViewById(R.id.tvInfo);
        Button btBind = findViewById(R.id.btnBind);
        Button btnUnbind = findViewById(R.id.btnUnbind);
        Button btnRefresh = findViewById(R.id.btnRefresh);

        // “绑定服务”按钮
        btBind.setOnClickListener(v -> {
            Log.i("myapp", "BindService.");
            Intent i = new Intent(this, DownloadService.class);
            bindService(i, connection, BIND_AUTO_CREATE);
        });

        // “解绑服务”按钮
        btnUnbind.setOnClickListener(v -> {
            Log.i("myapp", "UnbindService.");
            unbindService(connection);
        });

        // “刷新进度”按钮
        btnRefresh.setOnClickListener(v -> {
            // 通过Binder调用服务中的方法
            double percent = binder.getProgress() * 100;
            Log.i("myapp", "GetProgress:" + percent);
            textView.setText(percent + "%");
        });
    }

    // 连接回调
    class MyConnectionCallback implements ServiceConnection {

        @SuppressLint("SetTextI18n")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("myapp", "OnServiceConnected.");
            // 获取服务中的Binder对象，并保存至全局变量。
            binder = (DownloadService.DownloadBinder) service;

            // 设置回调监听，在进度更新时刷新界面。
            binder.getService().setCallback(percent -> {
                Log.i("myapp", "OnProgress:" + percent);
                // 切换至主线程更新UI
                runOnUiThread(() -> textView.setText(percent + "%"));
            });

            // 启动下载任务
            binder.start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("myapp", "OnServiceDisconnected.");
        }
    }
}
