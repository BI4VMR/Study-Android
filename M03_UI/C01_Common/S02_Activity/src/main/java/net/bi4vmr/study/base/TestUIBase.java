package net.bi4vmr.study.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUIBase extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置视图内容为布局文件"testui_base.xml"
        setContentView(R.layout.testui_base);
    }
}
