package net.bi4vmr.study.base;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 测试界面：基本应用。
 * <p>
 * `@HiltAndroidApp` 可以被Hilt识别到，其中的 `@Inject` 变量生效。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@AndroidEntryPoint
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Inject
    HTTPManager httpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btn01.setOnClickListener(v -> test());
    }

    // 功能模块
    private void test() {
        Log.i(TAG, "--- 功能模块 ---");
        appendLog("\n--- 功能模块 ---\n");

        // ...
        httpManager.login();
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(CharSequence text) {
        binding.tvLog.append(text);
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!");
                e.printStackTrace();
            }
        });
    }
}
