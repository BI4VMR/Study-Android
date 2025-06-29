package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.file.TestUIFile;
import net.bi4vmr.study.types.TestUITypes;
import net.bi4vmr.study.types.TestUITypesKT;

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

        // 自定义数据类型
        binding.btnTypes.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITypes.class);
            startActivity(intent);
        });

        // 文件传输
        binding.btnFile.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFile.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 自定义数据类型(KT)
        binding.btnTypesKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITypesKT.class);
            startActivity(intent);
        });

        // 文件传输
        binding.btnFile.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFile.class);
            startActivity(intent);
        });
    }
}
