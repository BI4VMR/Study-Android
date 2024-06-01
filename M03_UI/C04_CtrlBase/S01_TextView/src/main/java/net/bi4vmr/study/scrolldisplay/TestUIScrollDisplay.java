package net.bi4vmr.study.scrolldisplay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiScrollDisplayBinding;

public class TestUIScrollDisplay extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIScrollDisplay.class.getSimpleName();

    private TestuiScrollDisplayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiScrollDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 构造一段长的测试文本
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(i);
        }
        String text = builder.toString();

        // 设置文本
        binding.tvMarquee.setText(text);
        // 设置选中状态为"true"，使滚动生效。
        binding.tvMarquee.setSelected(true);
    }
}
