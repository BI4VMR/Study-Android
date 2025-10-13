package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.transform.TestUITransform;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 数据转换
        Button btnTransform = findViewById(R.id.btnTransform);
        btnTransform.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITransform.class);
            startActivity(intent);
        });
    }
}
