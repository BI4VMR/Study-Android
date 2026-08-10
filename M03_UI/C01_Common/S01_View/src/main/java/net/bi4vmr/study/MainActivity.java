package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.event.TestUIEvent;
import net.bi4vmr.study.event.TestUIEventKT;
import net.bi4vmr.study.function.TestUIFunction;
import net.bi4vmr.study.function.TestUIFunctionKT;
import net.bi4vmr.study.layout.TestUILayout;
import net.bi4vmr.study.layout.TestUILayoutKT;
import net.bi4vmr.study.skills.TestUISkills;
import net.bi4vmr.study.skills.TestUISkillsKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 布局文件
        binding.btnLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILayout.class);
            startActivity(intent);
        });

        // 常用方法
        binding.btnFunction.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFunction.class);
            startActivity(intent);
        });

        // 事件监听器
        binding.btnEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEvent.class);
            startActivity(intent);
        });

        // 实用技巧
        binding.btnSkills.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISkills.class);
            startActivity(intent);
        });

        // 布局文件(KT)
        binding.btnLayoutKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILayoutKT.class);
            startActivity(intent);
        });

        // 常用方法(KT)
        binding.btnFunctionKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFunctionKT.class);
            startActivity(intent);
        });

        // 事件监听器(KT)
        binding.btnSkillsKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEventKT.class);
            startActivity(intent);
        });

        // 实用技巧(KT)
        binding.btnSkillsKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISkillsKT.class);
            startActivity(intent);
        });
    }
}
