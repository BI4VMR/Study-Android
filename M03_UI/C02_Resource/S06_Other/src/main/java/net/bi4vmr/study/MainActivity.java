package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.constant.base.TestUIConstantBase;
import net.bi4vmr.study.constant.base.TestUIConstantBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.dimen.base.TestUIDimenBase;
import net.bi4vmr.study.dimen.base.TestUIDimenBaseKT;
import net.bi4vmr.study.id.base.TestUIIDBase;
import net.bi4vmr.study.id.base.TestUIIDBaseKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ID - 基本应用
        binding.btnIDBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIIDBase.class);
            startActivity(intent);
        });

        // 尺寸 - 基本应用
        binding.btnDimenBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDimenBase.class);
            startActivity(intent);
        });

        // 常量 - 基本应用
        binding.btnConstBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIConstantBase.class);
            startActivity(intent);
        });

        // ID - 基本应用(KT)
        binding.btnIDBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIIDBaseKT.class);
            startActivity(intent);
        });

        // 尺寸 - 基本应用(KT)
        binding.btnDimenBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDimenBaseKT.class);
            startActivity(intent);
        });

        // 尺寸 - 基本应用(KT)
        binding.btnConstBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIConstantBaseKT.class);
            startActivity(intent);
        });
    }
}
