package net.bi4vmr.study.compatibility;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiCompatibilityBinding;

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUICompatibility extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUICompatibility.class.getSimpleName();

    private TestuiCompatibilityBinding binding;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiCompatibilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnFunction.setOnClickListener(v -> {
            Log.i(TAG, "----- 兼容性测试 -----");
            appendLog("\n----- 兼容性测试 -----");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                /* 系统版本高于Android 13 */
                Log.i(TAG, "系统版本高于Android 13，调用新的接口。");
                appendLog("系统版本高于Android 13，调用新的接口。");

                getPackageManager().getInstalledPackages(PackageManager.PackageInfoFlags.of(0L));
            } else {
                /* 系统版本低于Android 13 */
                Log.i(TAG, "系统版本低于Android 13，调用旧的接口。");
                appendLog("系统版本低于Android 13，调用旧的接口。");

                getPackageManager().getInstalledPackages(0);
            }
        });
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
