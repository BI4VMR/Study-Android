package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.cache.TestUICache;
import net.bi4vmr.study.cache.TestUICacheKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.event.TestUIEvent;
import net.bi4vmr.study.event.TestUIEventKT;
import net.bi4vmr.study.lifecycle.TestUILifeCycle;
import net.bi4vmr.study.lifecycle.TestUILifeCycleKT;
import net.bi4vmr.study.loop1.TestUILoop1;
import net.bi4vmr.study.loop1.TestUILoop1KT;
import net.bi4vmr.study.loop2.TestUILoop2;
import net.bi4vmr.study.loop2.TestUILoop2KT;
import net.bi4vmr.study.style.TestUIStyle;
import net.bi4vmr.study.style.TestUIStyleKT;
import net.bi4vmr.study.swipe1page.TestUISwipe1Page;
import net.bi4vmr.study.swipe1page.TestUISwipe1PageKT;
import net.bi4vmr.study.swipectrl.TestUISwipeCtrl;
import net.bi4vmr.study.swipectrl.TestUISwipeCtrlKT;

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

        // 外观定制
        binding.btnStyle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIStyle.class);
            startActivity(intent);
        });

        // 事件监听器
        binding.btnEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEvent.class);
            startActivity(intent);
        });

        // 滑动控制
        binding.btnSwipeCtrl.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISwipeCtrl.class);
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

        // 循环滑动（实现二）
        binding.btnLoop2.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILoop2.class);
            startActivity(intent);
        });

        // 缓存与复用
        binding.btnCache.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUICache.class);
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

        // 外观定制(KT)
        binding.btnStyleKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIStyleKT.class);
            startActivity(intent);
        });

        // 事件监听器(KT)
        binding.btnEventKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEventKT.class);
            startActivity(intent);
        });

        // 滑动控制(KT)
        binding.btnSwipeCtrlKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISwipeCtrlKT.class);
            startActivity(intent);
        });

        // 限制连续滑动(KT)
        binding.btnSwipe1PageKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISwipe1PageKT.class);
            startActivity(intent);
        });

        // 循环滑动（实现一）(KT)
        binding.btnLoop1KT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILoop1KT.class);
            startActivity(intent);
        });

        // 循环滑动（实现二）(KT)
        binding.btnLoop2KT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILoop2KT.class);
            startActivity(intent);
        });

        // 缓存与复用(KT)
        binding.btnCacheKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUICacheKT.class);
            startActivity(intent);
        });

        // 生命周期(KT)
        binding.btnLifeCycleKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycleKT.class);
            startActivity(intent);
        });
    }
}
