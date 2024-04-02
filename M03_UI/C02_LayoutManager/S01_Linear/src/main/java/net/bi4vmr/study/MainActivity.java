package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.multiline.TestUIMultiLine;
import net.bi4vmr.study.weight.TestUIWeight;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 换行显示
        Button btnMultiLine = findViewById(R.id.btnMultiLine);
        btnMultiLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIMultiLine.class);
            startActivity(intent);
        });

        // 权重属性
        Button btnWeight = findViewById(R.id.btnWeight);
        btnWeight.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIWeight.class);
            startActivity(intent);
        });
    }
}
