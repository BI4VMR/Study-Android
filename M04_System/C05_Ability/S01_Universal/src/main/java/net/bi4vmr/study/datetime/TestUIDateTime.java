package net.bi4vmr.study.datetime;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiDatetimeBinding;

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIDateTime extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIDateTime.class.getSimpleName();

    private TestuiDatetimeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiDatetimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnTimeInfo.setOnClickListener(v -> testTimeInfo());
    }

    private void testTimeInfo() {
        Log.i(TAG, "----- 时间信息 -----");
        appendLog("\n----- 时间信息 -----");

      long i =  SystemClock.uptimeMillis();
      // long i =  SystemClock.setCurrentTimeMillis()
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
