package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.event.TestUIEvent;
import net.bi4vmr.study.event.TestUIEventKT;
import net.bi4vmr.study.lifecycle.TestUILifeCycle;
import net.bi4vmr.study.lifecycle.TestUILifeCycleKT;
import net.bi4vmr.study.swipe1page.TestUISwipe1Page;
import net.bi4vmr.study.swipe1page.TestUISwipe1PageKT;

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

        // 事件监听器
        binding.btnEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEvent.class);
            startActivity(intent);
        });

        // 限制连续滑动
        binding.btnSwipe1Page.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISwipe1Page.class);
            startActivity(intent);
        });

        // 生命周期
        binding.btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycle.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 事件监听器(KT)
        binding.btnEventKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEventKT.class);
            startActivity(intent);
        });

        // 限制连续滑动(KT)
        binding.btnSwipe1PageKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISwipe1PageKT.class);
            startActivity(intent);
        });

        // 生命周期(KT)
        binding.btnLifeCycleKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycleKT.class);
            startActivity(intent);
        });
    }
}
