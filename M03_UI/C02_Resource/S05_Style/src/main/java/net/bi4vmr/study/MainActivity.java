package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.theme.TestUITheme;
import net.bi4vmr.study.theme.TestUIThemeKT;
import net.bi4vmr.study.themeattrsinternal.TestUIThemeAttrsInternal;
import net.bi4vmr.study.themeattrsinternal.TestUIThemeAttrsInternalKT;
import net.bi4vmr.study.themeattrscustom.TestUIThemeAttrsCustom;
import net.bi4vmr.study.themeattrscustom.TestUIThemeAttrsCustomKT;
import net.bi4vmr.study.themedark.TestUIThemeDark;
import net.bi4vmr.study.themedark.TestUIThemeDarkKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 样式 - 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 主题 - 基本应用
        binding.btnTheme.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITheme.class);
            startActivity(intent);
        });

        // 主题 - 内置属性
        binding.btnThemeAttrsInternal.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeAttrsInternal.class);
            startActivity(intent);
        });

        // 主题 - 自定义属性
        binding.btnThemeAttrsCustom.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeAttrsCustom.class);
            startActivity(intent);
        });

        // 主题 - 深色模式
        binding.btnThemeDark.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeDark.class);
            startActivity(intent);
        });

        // 样式 - 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 主题 - 基本应用(KT)
        binding.btnThemeKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeKT.class);
            startActivity(intent);
        });

        // 主题 - 内置属性(KT)
        binding.btnThemeAttrsInternalKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeAttrsInternalKT.class);
            startActivity(intent);
        });

        // 主题 - 自定义属性(KT)
        binding.btnThemeAttrsCustomKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeAttrsCustomKT.class);
            startActivity(intent);
        });

        // 主题 - 深色模式(KT)
        binding.btnThemeDarkKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThemeDarkKT.class);
            startActivity(intent);
        });
    }
}
