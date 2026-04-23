package net.bi4vmr.study.metadata;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiMetadataBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIMetaData extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIMetaData.class.getSimpleName();

    private TestuiMetadataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiMetadataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void test_GetApplicationInfo() {
        Log.i(TAG, "----- 获取Application信息 -----");
        appendLog("\n----- 获取Application信息 -----");

        PackageManager packageManager = this.getPackageManager();
        try {
            // 使用当前软件包作为目标
            String target = getPackageName();
            // 获取该软件包的ApplicationInfo实例
            ApplicationInfo info = packageManager.getApplicationInfo(target, 0);

            // 包名
            String packageName = info.packageName;
            Log.i(TAG, "包名：[" + packageName + "]");
            appendLog("包名：[" + packageName + "]");
        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            Log.e(TAG, "App not found! Info:[" + e.getMessage() + "]");
            appendLog("App not found! Info:[" + e.getMessage() + "]");
        }
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
