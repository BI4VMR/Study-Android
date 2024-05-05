package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.event.TestUIEvent;
import net.bi4vmr.study.loop1.TestUILoop1;
import net.bi4vmr.study.swipe1page.TestUISwipe1Page;

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

        // 循环滑动（实现一）
        binding.btnLoop1.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILoop1.class);
            startActivity(intent);
        });
    }
}
