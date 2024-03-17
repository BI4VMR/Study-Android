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
        binding.btnEnd.setOnClickListener(v -> testEnd());
    }

    // 启动服务
    private void testStart() {
        binding.tvLog.append("\n--- 启动服务 ---\n");
        Log.i(TAG, "--- 启动服务 ---");

        // 指明目标服务
        Intent intent = new Intent(this, DownloadService.class);
        // 添加初始化数据
        intent.putExtra("LINK", "https://dl.test.com/file");
        // 启动服务
        startService(intent);
    }

    // 停止服务
    private void testEnd() {
        binding.tvLog.append("\n--- 停止服务 ---\n");
        Log.i(TAG, "--- 停止服务 ---");

        Intent intent = new Intent(this, DownloadService.class);
        boolean isSuccess = stopService(intent);
        if (isSuccess) {
            binding.tvLog.append("服务已被停止。\n");
            Log.i(TAG, "服务已被停止。");
        } else {
            binding.tvLog.append("服务停止失败！\n");
            Log.e(TAG, "服务停止失败！");
        }
    }
}
