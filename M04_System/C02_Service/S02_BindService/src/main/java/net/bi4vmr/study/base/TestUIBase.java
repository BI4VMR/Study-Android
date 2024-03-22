package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

@SuppressLint("SetTextI18n")
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    /*
     * 服务连接回调实现类
     *
     * 用于接收服务连接成功、连接断开事件，并获取Binder实例。
     */
    private final ServiceConnection connection = new MyConnectionCallback();

    // Binder实例
    private DownloadService.DownloadBinder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnGetProgress.setOnClickListener(v -> testGetTaskProgress());
    }

    // 绑定服务
    private void testBind() {
        binding.tvLog.append("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    // 解绑服务
    private void testUnbind() {
        binding.tvLog.append("\n--- 解绑服务 ---\n");
        Log.i(TAG, "--- 解绑服务 ---");

        unbindService(connection);
    }

    // 获取任务进度
    private void testGetTaskProgress() {
        binding.tvLog.append("\n--- 获取任务进度 ---\n");
        Log.i(TAG, "--- 获取任务进度 ---");

        // 通过Binder调用服务中的方法
        double percent = binder.getProgress() * 100;
        binding.tvLog.setText("当前进度：" + percent + "%\n");
        Log.i(TAG, "当前进度：" + percent + "%");
    }

    // 服务连接回调实现类
    private class MyConnectionCallback implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binding.tvLog.setText("OnServiceConnected.");
            Log.i(TAG, "OnServiceConnected.");

            // 获取服务中的Binder对象，并保存至全局变量。
            binder = (DownloadService.DownloadBinder) service;
            // 设置回调监听，在进度更新时刷新界面。
            binder.getService().setCallback(percent -> {
                Log.i(TAG, "Callback-OnProgress:" + percent);
                // 切换至主线程更新UI
                runOnUiThread(() -> binding.tvLog.setText("Callback-OnProgress:" + percent));
            });

            // 启动下载任务
            binder.start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binding.tvLog.setText("OnServiceDisconnected.");
            Log.i(TAG, "OnServiceDisconnected.");
        }
    }
}
