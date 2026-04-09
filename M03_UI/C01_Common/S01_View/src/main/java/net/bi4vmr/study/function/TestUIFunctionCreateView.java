package net.bi4vmr.study.function;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiFunctionCreateviewBinding;

/**
 * 测试界面：常用方法 - 创建控件实例。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIFunctionCreateView extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFunctionCreateView.class.getSimpleName();

    private TestuiFunctionCreateviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFunctionCreateviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建TextView实例
        TextView textview = new TextView(this);
        // 设置文本内容
        textview.setText("这是一个动态创建的TextView。");
        // 设置文本颜色
        textview.setTextColor(Color.RED);
        // 设置背景颜色
        textview.setBackgroundColor(Color.CYAN);

        // 创建LayoutParams实例，指定宽高和外边距。
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.setMarginStart(100);

        // 将TextView添加到容器中
        FrameLayout layout = findViewById(R.id.container);
        layout.addView(textview, lp);
    }
}
