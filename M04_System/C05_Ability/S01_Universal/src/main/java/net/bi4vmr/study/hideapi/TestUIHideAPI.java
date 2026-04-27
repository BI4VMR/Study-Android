package net.bi4vmr.study.hideapi;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiHideapiBinding;

import java.lang.reflect.Method;

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIHideAPI extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIHideAPI.class.getSimpleName();

    private TestuiHideapiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiHideapiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnCall.setOnClickListener(v -> {
            Log.i(TAG, "----- 调用隐藏接口 -----");
            appendLog("\n----- 调用隐藏接口 -----");

            try {
                Method method = WindowManager.class.getDeclaredMethod("shouldShowSystemDecors", int.class);
                method.invoke(getWindowManager(), 0);
            } catch (Exception e) {
                Log.e(TAG, "调用隐藏方法失败！", e);
                appendLog("调用隐藏方法失败！\n" + e);
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
