package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.font.TestUIFont;
import net.bi4vmr.study.font.TestUIFontKT;
import net.bi4vmr.study.i18n.TestUIMultiLanguage;
import net.bi4vmr.study.i18n.TestUIMultiLanguageKT;
import net.bi4vmr.study.plurals.TestUIPlurals;
import net.bi4vmr.study.template.TestUITemplate;
import net.bi4vmr.study.template.TestUITemplateKT;

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

        // 字符串模板
        binding.btnTemplate.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITemplate.class);
            startActivity(intent);
        });

        // 复数
        binding.btnPlurals.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPlurals.class);
            startActivity(intent);
        });

        // 多语言支持
        binding.btnI18N.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIMultiLanguage.class);
            startActivity(intent);
        });

        // 字体
        binding.btnFont.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFont.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 字符串模板(KT)
        binding.btnTemplateKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITemplateKT.class);
            startActivity(intent);
        });

        // 字符串模板(KT)
        binding.btnTemplateKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITemplateKT.class);
            startActivity(intent);
        });

        // 多语言支持(KT)
        binding.btnI18NKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIMultiLanguageKT.class);
            startActivity(intent);
        });

        // 字体(KT)
        binding.btnFontKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFontKT.class);
            startActivity(intent);
        });
    }
}
