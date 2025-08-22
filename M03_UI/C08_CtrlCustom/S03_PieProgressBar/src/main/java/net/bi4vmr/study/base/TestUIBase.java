package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：TODO 添加简述。
 * <p>
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // binding.btn01.setOnClickListener(v -> test());
    }

    // 功能模块
    private void test() {
        Log.i(TAG, "--- 功能模块 ---");
        // binding.tvLog.append("\n--- 功能模块 ---\n");

        // ...
    }
}
