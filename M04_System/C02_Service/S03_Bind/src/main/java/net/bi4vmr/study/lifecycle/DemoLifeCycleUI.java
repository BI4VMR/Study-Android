package net.bi4vmr.study.lifecycle;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoLifeCycleUI extends AppCompatActivity {

    private final ServiceConnection connectionA = new MyConnectionCallback("A");
    private final ServiceConnection connectionB = new MyConnectionCallback("B");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_lifecycle);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnBindA = findViewById(R.id.btnBindA);
        Button btnUnbindA = findViewById(R.id.btnUnbindA);
        Button btnBindB = findViewById(R.id.btnBindB);
        Button btnUnbindB = findViewById(R.id.btnUnbindB);

        // “启动服务”按钮
        btnStart.setOnClickListener(v -> {
            Log.i("myapp", "StartService.");
            Intent i = new Intent(this, TestService.class);
            startService(i);
        });

        // “停止服务”按钮
        btnStop.setOnClickListener(v -> {
            Log.i("myapp", "StopService.");
            Intent i = new Intent(this, TestService.class);
            stopService(i);
        });

        // “绑定服务”按钮A
        btnBindA.setOnClickListener(v -> {
            Log.i("myapp", "BindService.");
            Intent i = new Intent(this, TestService.class);
            i.setType("111");
            bindService(i, connectionA, BIND_AUTO_CREATE);
        });

        // “解绑服务”按钮A
        btnUnbindA.setOnClickListener(v -> {
            Log.i("myapp", "UnbindService.");
            unbindService(connectionA);
        });

        // “绑定服务”按钮B
        btnBindB.setOnClickListener(v -> {
            Log.i("myapp", "BindService.");
            Intent i = new Intent(this, TestService.class);
            i.setType("222");
            bindService(i, connectionB, BIND_AUTO_CREATE);
        });

        // “解绑服务”按钮B
        btnUnbindB.setOnClickListener(v -> {
            Log.i("myapp", "UnbindService.");
            unbindService(connectionB);
        });
    }

    // 连接回调
    static class MyConnectionCallback implements ServiceConnection {

        private final String tag;

        public MyConnectionCallback(String tag) {
            this.tag = tag;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("myapp", "OnServiceConnected. Tag:" + tag + " Binder:" + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("myapp", "OnServiceDisconnected. Tag:" + tag);
        }
    }
}
