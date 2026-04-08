package net.bi4vmr.study.skills;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiSkillsBinding;

import java.util.Arrays;

/**
 * 测试界面：实用技巧。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUISkills extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISkills.class.getSimpleName();

    private TestuiSkillsBinding binding;

    // 记录按钮上次被点击的时间点
    private long lastClickTS = 0L;

    // 记录按钮被点击的时间点
    private final long[] clickRecords = new long[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSkillsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        testDebounceClick();
        testClickTenTimes();
    }

    // 防止高频点击
    private void testDebounceClick() {
        binding.btnDebounceClick.setOnClickListener(v -> {
            // 当前时间点
            long currentTS = SystemClock.uptimeMillis();
            if (currentTS - lastClickTS < 1000L) {
                // 如果当前时间与上次点击的时间差值小于1秒，则认为是连续点击，不执行业务操作。
                Log.w(TAG, "连续点击过于频繁，忽略！");
                appendLog("连续点击过于频繁，忽略！");
            } else {
                // 如果当前时间与上次点击的时间差值达到1秒，更新时间记录，并执行业务操作。
                lastClickTS = currentTS;
                // 业务操作：打印日志。
                Log.i(TAG, "点击间隔超过1秒，允许触发事件。");
                appendLog("点击间隔超过1秒，允许触发事件。");
            }
        });
    }

    // 连续点击触发事件
    private void testClickTenTimes() {
        binding.btnClickTenTimes.setOnClickListener(v -> {
            // 现有元素全部左移一位，舍弃最旧的一条记录。
            System.arraycopy(clickRecords, 1, clickRecords, 0, clickRecords.length - 1);
            // 将当前点击时间记录到数组末尾
            clickRecords[clickRecords.length - 1] = SystemClock.uptimeMillis();
            // 当前时间与最早一次的点击时间比较，如果差值小于5秒，则触发连点事件。
            if (SystemClock.uptimeMillis() - clickRecords[0] <= 5000L) {
                // 重置记录器
                Arrays.fill(clickRecords, 0L);

                // 业务操作：打印日志。
                Log.i(TAG, "5秒内已点击10次，允许触发事件。");
                appendLog("5秒内已点击10次，允许触发事件。");
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
