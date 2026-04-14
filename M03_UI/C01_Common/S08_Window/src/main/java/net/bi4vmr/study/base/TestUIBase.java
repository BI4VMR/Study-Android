package net.bi4vmr.study.base;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

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

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnAddWindow.setOnClickListener(v -> test_AddWindow());
        binding.btnGetActivityWindow.setOnClickListener(v -> test_GetActivityWindow());
    }

    private void test_AddWindow() {
        Log.i(TAG, "----- 添加Window -----");
        appendLog("\n----- 添加Window -----");

        // 创建View

        // 配置参数
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // 获取WindowManager
        WindowManager manager = getApplicationContext().getSystemService(WindowManager.class);
        manager.addView(binding.getRoot(), new WindowManager.LayoutParams());
    }

    private void test_GetActivityWindow() {
        Log.i(TAG, "----- 控制Activity的Window -----");
        appendLog("\n----- 控制Activity的Window -----");

        getWindowManager();
        // getWindow().addFlags();
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
