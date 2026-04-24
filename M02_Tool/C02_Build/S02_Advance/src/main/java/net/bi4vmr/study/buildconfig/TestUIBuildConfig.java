package net.bi4vmr.study.buildconfig;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.BuildConfig;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：读取BuildConfig中的变量。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBuildConfig extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBuildConfig.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnAccess.setOnClickListener(v -> testAccess());
    }

    // 访问变量
    private void testAccess() {
        Log.i(TAG, "----- 访问变量 -----");
        binding.tvLog.append("\n----- 访问变量 -----");

        // 访问内置变量
        boolean debug = BuildConfig.DEBUG;
        String buildType = BuildConfig.BUILD_TYPE;

        Log.i(TAG, "是否允许Debug:[" + debug + "]\n构建类型:[" + buildType + "]");
        binding.tvLog.append("\n是否允许Debug:[" + debug + "]\n构建类型:[" + buildType + "]");

        // 访问自定义变量
        Log.i(TAG, "自定义BuildConfig变量:[" + BuildConfig.SERVER_NAME + "]");
        binding.tvLog.append("\n自定义BuildConfig变量:[" + BuildConfig.SERVER_NAME + "]");
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        if (text == null) {
            Log.w(TAG, "Log item is NULL, ignored!");
            return;
        }

        TextView logArea = binding.tvLog;
        logArea.post(() -> logArea.append("\n" + text));
        logArea.post(() -> {
            try {
                int offset = logArea.getLayout().getLineTop(logArea.getLineCount()) - logArea.getHeight();
                if (offset > 0) {
                    logArea.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
