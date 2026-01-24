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

    /**
     * 业务组件实例。
     * <p>
     * `@Inject` 注解表示该变量在运行时由Hilt进行依赖注入，此类变量必须是非私有的。
     */
    @Inject
    HTTPManager httpManager;

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnLogin.setOnClickListener(v -> testLogin());
    }

    private void testLogin() {
        Log.i(TAG, "----- 调用业务方法 -----");
        appendLog("\n----- 调用业务方法 -----");

        httpManager.login();
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        binding.tvLog.post(() -> binding.tvLog.append("\n" + text.toString()));
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
