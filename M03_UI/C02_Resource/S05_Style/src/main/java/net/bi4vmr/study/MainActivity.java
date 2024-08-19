package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.theme.TestUITheme;
import net.bi4vmr.study.theme.TestUIThemeKT;
import net.bi4vmr.study.themeattr.TestUIThemeAttr;
import net.bi4vmr.study.themeattr.TestUIThemeAttrKT;

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

        // 主题
        binding.btnTheme.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITheme.class);
            startActivity(intent);
        });

        // 常用属性
        binding.btnThemeAttr.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeAttr.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 主题(KT)
        binding.btnThemeKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeKT.class);
            startActivity(intent);
        });

        // 常用属性(KT)
        binding.btnThemeAttrKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeAttrKT.class);
            startActivity(intent);
        });
    }
}
