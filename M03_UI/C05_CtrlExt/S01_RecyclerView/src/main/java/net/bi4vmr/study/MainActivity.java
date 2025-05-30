package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.clickevent.TestUIClickEvent;
import net.bi4vmr.study.clickevent.TestUIClickEventKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.diffutil.DemoDiffUtilUI;
import net.bi4vmr.study.updateitem.DemoUpdateItemUI;
import net.bi4vmr.study.updatelist.DemoUpdateListUI;
import net.bi4vmr.study.viewcache.DemoViewCacheUI;
import net.bi4vmr.study.viewtype.TestUIViewType;

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

        // 点击事件
        binding.btnClickEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIClickEvent.class);
            startActivity(intent);
        });

        // 多种View类型
        binding.btnViewType.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIViewType.class);
            startActivity(intent);
        });

        // 动态更新列表
        binding.btnUpdateList.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoUpdateListUI.class);
            startActivity(intent);
        });

        // 局部刷新
        binding.btnUpdateItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoUpdateItemUI.class);
            startActivity(intent);
        });

        // DiffUtil
        binding.btnDiffUtil.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoDiffUtilUI.class);
            startActivity(intent);
        });

        // 缓存与复用
        binding.btnViewCache.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoViewCacheUI.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 点击事件(KT)
        binding.btnClickEventKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIClickEventKT.class);
            startActivity(intent);
        });
    }
}
