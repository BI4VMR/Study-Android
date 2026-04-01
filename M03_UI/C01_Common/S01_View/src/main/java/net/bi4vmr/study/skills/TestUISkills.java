package net.bi4vmr.study.skills;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSkillsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 点击事件 */
        // binding.btnClick.setOnClickListener(new View.OnClickListener() {
        //
        //     @Override
        //     public void onClick(View v) {
        //         Log.i(TAG, "按钮被点击了！");
        //         appendLog("按钮被点击了！\n");
        //     }
        // });

        /* 长按事件 */
        // binding.btnClickTenTimes.setOnClickListener(() -> {
        //     // // 所有现有数据左移一位，舍弃最旧的一位数据。
        //     // System.arraycopy(clickTSRecords, 1, clickTSRecords, 0, clickTSRecords.size - 1)
        //     // // 将当前点击时间记录到数组末尾
        //     // clickTSRecords[clickTSRecords.size - 1] = SystemClock.uptimeMillis()
        //     // // 当前时间与最早一次的点击时间比较，如果差值小于5秒，则触发连点事件。
        //     // if (SystemClock.uptimeMillis() - clickTSRecords[0] <= 5000L) {
        //     //     "Repeat click 10 times in 5 seconds, open environment switch dialog.".logi(TAG)
        //     //     // showEnvironmentDialog()
        //     //     // 事件已经触发，重置记录器。
        //     //     Arrays.fill(clickTSRecords, 0L)
        //     // }
        // });
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
