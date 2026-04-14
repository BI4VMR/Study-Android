package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * Activity：触摸事件测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "Activity";

    private TestuiBaseBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

        // 注册点击事件监听器，查看点击事件。
        binding.btnTest.setOnClickListener(v -> {
            Log.i("View", "按钮被点击了！");
            appendLog("按钮被点击了！");
        });

        // 注册触摸事件监听器，查看触摸事件。
        binding.btnTest.setOnTouchListener((v, event) -> {
            Log.i("View", "OnTouch: " + event);
            return false;
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "DispatchTouchEvent. Type:[" + ev.getAction() + "]");
        boolean r = super.dispatchTouchEvent(ev);
        Log.i(TAG, "DispatchTouchEvent end, return:[" + r + "]");
        return r;
    }

    @Override
    public void onUserInteraction() {
        Log.i(TAG, "OnUserInteraction.");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "OnTouchEvent. Type:[" + event.getAction() + "]");
        boolean r = super.onTouchEvent(event);
        Log.i(TAG, "OnTouchEvent end, return:[" + r + "]");
        return r;
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
