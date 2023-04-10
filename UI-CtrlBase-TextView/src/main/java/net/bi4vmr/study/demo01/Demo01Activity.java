package net.bi4vmr.study.demo01;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.textview.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 获取控件对象
        TextView tvBase = findViewById(R.id.tvBase);
        // 设置文本内容
        tvBase.setText("这是一个文本框");
    }
}
