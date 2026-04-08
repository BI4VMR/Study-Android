package net.bi4vmr.study.event;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiEventBinding;

/**
 * 测试界面：事件监听器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIEvent extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIEvent.class.getSimpleName();

    private TestuiEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 点击事件 */
        binding.btnClick.setOnClickListener(new View.OnClickListener() {

            // 该方法将在控件被点击时回调
            @Override
            public void onClick(View v) {
                Log.i(TAG, "按钮被点击了！");
                appendLog("按钮被点击了！\n");
            }
        });

        /* 长按事件 */
        binding.btnClick.setOnLongClickListener(new View.OnLongClickListener() {

            // 该方法将在控件被按住0.5秒后被回调
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "按钮被长按了！");
                appendLog("按钮被长按了！\n");

                /*
                 * 该方法的返回值用于控制用户按住控件0.5秒触发长按回调后，抬起手指时是否触发点击回调。
                 *
                 * 返回 `true` 表示长按回调已经处理完毕触控流程，用户抬手时不会触发点击事件回调方法。
                 * 返回 `false` 表示长按回调没有处理完毕触控流程，用户抬手时仍会触发点击事件回调方法。
                 *
                 * 对于大部分场景都应当返回 `true` 。
                 */
                return true;
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
