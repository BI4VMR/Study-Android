package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.demo01.Demo01Activity;
import net.bi4vmr.study.demo02.Demo02Activity;
import net.bi4vmr.study.demo03.Demo03Activity;
import net.bi4vmr.study.demo04.Demo04Activity;
import net.bi4vmr.study.demo05.Demo05Activity;
import net.bi4vmr.study.ui.ctrlbase.alertdialog.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button bt01 = findViewById(R.id.bt01);
        bt01.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo01Activity.class);
            startActivity(intent);
        });

        // 列表对话框
        Button bt02 = findViewById(R.id.bt02);
        bt02.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });

        // 选择对话框
        Button bt03 = findViewById(R.id.bt03);
        bt03.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo03Activity.class);
            startActivity(intent);
        });

        // 自定义布局
        Button bt04 = findViewById(R.id.bt04);
        bt04.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo04Activity.class);
            startActivity(intent);
        });

        // 自定义窗体
        Button bt05 = findViewById(R.id.bt05);
        bt05.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo05Activity.class);
            startActivity(intent);
        });
    }
}
