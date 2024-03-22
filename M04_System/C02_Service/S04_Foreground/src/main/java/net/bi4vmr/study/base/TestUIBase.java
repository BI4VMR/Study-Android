package net.bi4vmr.study.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testStart());
        binding.btnStop.setOnClickListener(v -> testStop());
    }

    // 启动服务
    private void testStart() {
        binding.tvLog.append("\n--- 启动服务 ---\n");
        Log.i(TAG, "--- 启动服务 ---");

        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
    }

    // 停止服务
    private void testStop() {
        binding.tvLog.append("\n--- 停止服务 ---\n");
        Log.i(TAG, "--- 停止服务 ---");

        Intent intent = new Intent(this, ForegroundService.class);
        stopService(intent);
    }
}
