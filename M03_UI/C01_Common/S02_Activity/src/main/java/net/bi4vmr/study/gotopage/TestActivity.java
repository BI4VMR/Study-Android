package net.bi4vmr.study.gotopage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 获取开启当前Activity的Intent实例
        Intent intent = getIntent();
        if (intent != null) {
            // 根据Key获取初始化参数的值
            String info = intent.getStringExtra("PARAM_INIT");
            if (info != null) {
                Log.i("myapp", "初始化参数内容：" + info);
            } else {
                Log.i("myapp", "初始化参数为空");
            }
        }

        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            // 关闭当前Activity
            finish();
        });
    }
}
