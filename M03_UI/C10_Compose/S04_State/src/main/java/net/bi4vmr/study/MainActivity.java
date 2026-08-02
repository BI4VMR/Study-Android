package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.data.TestUIData;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.statesave.TestUIStateSave;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 状态保持
        binding.btnStateSave.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIStateSave.class);
            startActivity(intent);
        });

        // 数据表示
        binding.btnData.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIData.class);
            startActivity(intent);
        });

        // 副作用
        binding.btnEffect.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIData.class);
            startActivity(intent);
        });
    }
}
