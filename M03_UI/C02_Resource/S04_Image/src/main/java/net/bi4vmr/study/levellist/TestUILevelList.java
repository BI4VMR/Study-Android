package net.bi4vmr.study.levellist;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：LevelList。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILevelList extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILevelList.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btn01.setOnClickListener(v -> test());
    }

    private void test() {
        Log.i(TAG, "----- 功能模块 -----");
        appendLog("\n----- 功能模块 -----");

        // LevelListDrawable drawable = (LevelListDrawable) getResources().getDrawable(R.drawable.ic_status_wlan);
        // drawable.setAutoMirrored(true);
        // drawable.setLevel(0);
        // binding.ivDraw.setImageDrawable(drawable);
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
