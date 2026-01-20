package net.bi4vmr.study.remote;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiResourceRemoteBinding;

public class TestUIRemote extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIRemote.class.getSimpleName();

    private TestuiResourceRemoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiResourceRemoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRemoteResource.setOnClickListener(v -> testGetRemoteResource());
        binding.btnAPKResource.setOnClickListener(v -> testGetAPKResource());
    }


    private void testGetRemoteResource() {
        Log.i(TAG, "--- 获取其他软件包中的资源 ---");
        binding.tvLog.append("\n--- 获取其他软件包中的资源 ---\n");

    }


    private void testGetAPKResource() {
        Log.i(TAG, "--- 获取APK文件中的资源 ---");
        binding.tvLog.append("\n--- 获取APK文件中的资源 ---\n");

    }
}
