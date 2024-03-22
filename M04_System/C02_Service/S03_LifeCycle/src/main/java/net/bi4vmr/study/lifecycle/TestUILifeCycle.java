package net.bi4vmr.study.lifecycle;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLifecycleBinding;

@SuppressLint("SetTextI18n")
public class TestUILifeCycle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILifeCycle.class.getSimpleName();

    private TestuiLifecycleBinding binding;

    private final ServiceConnection connectionA = new MyConnectionCallback("A");
    private final ServiceConnection connectionB = new MyConnectionCallback("B");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLifecycleBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testStartService());
        binding.btnStop.setOnClickListener(v -> testStopService());
        binding.btnBindA.setOnClickListener(v -> testBindServiceA());
        binding.btnUnbindA.setOnClickListener(v -> testUnbindServiceA());
        binding.btnBindB.setOnClickListener(v -> testBindServiceB());
        binding.btnUnbindB.setOnClickListener(v -> testUnbindServiceB());
    }

    private void testStartService() {
        binding.tvLog.append("\n--- 启动服务 ---\n");
        Log.i(TAG, "--- 启动服务 ---");

        Intent i = new Intent(this, TestService.class);
        startService(i);
    }

    private void testStopService() {
        binding.tvLog.append("\n--- 停止服务 ---\n");
        Log.i(TAG, "--- 停止服务 ---");

        Intent i = new Intent(this, TestService.class);
        stopService(i);
    }

    private void testBindServiceA() {
        binding.tvLog.append("\n--- 绑定服务（请求A） ---\n");
        Log.i(TAG, "--- 绑定服务（请求A） ---");

        Intent i = new Intent(this, TestService.class);
        i.setType("A");
        bindService(i, connectionA, BIND_AUTO_CREATE);
    }

    private void testUnbindServiceA() {
        binding.tvLog.append("\n--- 解绑服务（请求A） ---\n");
        Log.i(TAG, "--- 解绑服务（请求A） ---");

        unbindService(connectionA);
    }

    private void testBindServiceB() {
        binding.tvLog.append("\n--- 绑定服务（请求B） ---\n");
        Log.i(TAG, "--- 绑定服务（请求B） ---");

        Intent i = new Intent(this, TestService.class);
        i.setType("B");
        bindService(i, connectionB, BIND_AUTO_CREATE);
    }

    private void testUnbindServiceB() {
        binding.tvLog.append("\n--- 解绑服务（请求B） ---\n");
        Log.i(TAG, "--- 解绑服务（请求B） ---");

        unbindService(connectionB);
    }

    // 连接回调实现类
    private class MyConnectionCallback implements ServiceConnection {

        private final String cbName;

        public MyConnectionCallback(String cbName) {
            this.cbName = cbName;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binding.tvLog.append("OnServiceConnected. Name:[" + cbName + "], Binder:[" + service.hashCode() + "]\n");
            Log.i(TAG, "OnServiceConnected. Name:[" + cbName + "], Binder:[" + service.hashCode() + "]");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binding.tvLog.append("OnServiceDisconnected. Name:[" + cbName + "]\n");
            Log.i(TAG, "OnServiceDisconnected. Name:[" + cbName + "]");
        }
    }
}
