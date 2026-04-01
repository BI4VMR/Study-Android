package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.event.TestUIEvent;
import net.bi4vmr.study.skills.TestUISkills;
import net.bi4vmr.study.skills.TestUISkillsKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
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

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 事件监听器(KT)
        binding.btnSkillsKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISkillsKT.class);
            startActivity(intent);
        });

        // 实用技巧(KT)
        binding.btnSkillsKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISkillsKT.class);
            startActivity(intent);
        });
    }
}
