package net.bi4vmr.study.index;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiResourceIndexBinding;

public class TestUIIndex extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIIndex.class.getSimpleName();

    private TestuiResourceIndexBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiResourceIndexBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetResource.setOnClickListener(v -> testGetResource());
    }

    private void testGetResource() {
        Log.i(TAG, "--- 获取资源 ---");
        binding.tvLog.append("\n--- 获取资源 ---\n");

        // 在代码中获取颜色资源
        int colorValue = getColor(android.R.color.holo_blue_light);
    }

    // 获取屏幕信息
    private void testGetResourceInfo() {
        Log.i(TAG, "--- 获取资源信息 ---");
        binding.tvLog.append("\n--- 获取资源信息 ---\n");

        // 获取完整名称
        String fullName = getResources().getResourceName(R.string.app_name);
        // 获取资源名称
        String name = getResources().getResourceEntryName(R.string.app_name);
        // 获取资源类型
        String type = getResources().getResourceTypeName(R.string.app_name);
        // 获取资源所在包名
        String pkgName = getResources().getResourcePackageName(R.string.app_name);
    }
}
