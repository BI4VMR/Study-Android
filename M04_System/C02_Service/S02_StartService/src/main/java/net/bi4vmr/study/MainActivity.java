package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.foreground.TestUIForeground;
import net.bi4vmr.study.foreground.TestUIForegroundKT;

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

        // 前台服务
        binding.btnForeground.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIForeground.class);
            startActivity(intent);
        });


        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 前台服务(KT)
        binding.btnForegroundKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIForegroundKT.class);
            startActivity(intent);
        });
    }
}
