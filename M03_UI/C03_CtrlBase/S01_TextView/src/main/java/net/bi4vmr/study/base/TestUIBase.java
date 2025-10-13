package net.bi4vmr.study.base;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUIBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_base);

        // 获取控件对象
        TextView tvBase = findViewById(R.id.tvBase);
        // 设置文本内容
        tvBase.setText("这是一个文本框");
    }
}
