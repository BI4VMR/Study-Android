package net.bi4vmr.study.scrolldisplay;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUIScrollDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_scroll_display);

        // 构造一段长的测试文本
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(i);
        }
        String text = builder.toString();

        // 获取控件对象
        TextView tvMarquee = findViewById(R.id.tvMarquee);
        // 设置文本
        tvMarquee.setText(text);

        // 设置选中状态为"true"
        tvMarquee.setSelected(true);
    }
}
