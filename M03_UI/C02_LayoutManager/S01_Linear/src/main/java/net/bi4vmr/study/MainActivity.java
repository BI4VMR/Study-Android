package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.multiline.DemoMultiLineUI;
import net.bi4vmr.study.weight.DemoWeightUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBaseUI.class);
            startActivity(intent);
        });

        // 换行显示
        Button btnMultiLine = findViewById(R.id.btnMultiLine);
        btnMultiLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoMultiLineUI.class);
            startActivity(intent);
        });

        // 权重属性
        Button btnWeight = findViewById(R.id.btnWeight);
        btnWeight.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoWeightUI.class);
            startActivity(intent);
        });
    }
}
