package net.bi4vmr.study.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 获取MyDialog实例
        MyDialog dialog = MyDialog.newInstance();
        // 显示MyDialog
        dialog.show(getSupportFragmentManager(), null);
    }
}
