package net.bi4vmr.study.public_data;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiPublicDataBinding;

import java.io.File;

public class TestUIPublicData extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPublicData.class.getSimpleName();

    private TestuiPublicDataBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPublicDataBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnGetPath.setOnClickListener(v -> testGetPath());
        binding.btnGotoSetting.setOnClickListener(v -> testGotoSetting());
    }

    // 获取共享目录路径
    private void testGetPath() {
        binding.tvLog.append("\n--- 获取共享目录路径 ---\n");
        Log.i(TAG, "--- 获取共享目录路径 ---");

        // 获取共享目录的根路径
        File shareDir = Environment.getExternalStorageDirectory();
        binding.tvLog.append("\n共享存储-根目录: " + shareDir);
        Log.i(TAG, "共享存储-根目录: " + shareDir);
        binding.tvLog.append("\n共享存储-根目录: 读-" + shareDir.canRead() + " ，写-" + shareDir.canWrite());
        Log.i(TAG, "共享存储-根目录: 读-" + shareDir.canRead() + " ，写-" + shareDir.canWrite());

        // 获取指定类型的共享目录
        File shareDLDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        binding.tvLog.append("\n共享存储-下载: " + shareDLDir);
        Log.i(TAG, "共享存储-下载: " + shareDLDir);
        binding.tvLog.append("\n共享存储-下载: 读-" + shareDLDir.canRead() + " ，写-" + shareDLDir.canWrite());
        Log.i(TAG, "共享存储-下载: 读-" + shareDLDir.canRead() + " ，写-" + shareDLDir.canWrite());
    }

    // 打开应用详情页面
    private void testGotoSetting() {
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(uri);
        startActivity(intent);
    }
}
