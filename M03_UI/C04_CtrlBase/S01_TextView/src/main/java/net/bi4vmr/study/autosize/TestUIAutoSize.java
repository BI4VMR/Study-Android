package net.bi4vmr.study.autosize;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiAutosizeBinding;
import net.bi4vmr.study.databinding.TestuiScrollDisplayBinding;

/**
 * 测试界面：自动文本尺寸。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIAutoSize extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIAutoSize.class.getSimpleName();

    private TestuiAutosizeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiAutosizeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 构造一段长的测试文本
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(i);
        }
        String text = builder.toString();

        // 设置文本
        // binding.tvMarquee.setText(text);
    }
}
