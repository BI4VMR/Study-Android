package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.clickevent.TestUIClickEvent;
import net.bi4vmr.study.clickevent.TestUIClickEventKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.diffutil.TestUIDiffUtil;
import net.bi4vmr.study.diffutil.TestUIDiffUtilKT;
import net.bi4vmr.study.diffutilasync.TestUIDiffUtilAsync;
import net.bi4vmr.study.updateitem.TestUIUpdateItem;
import net.bi4vmr.study.updateitem.TestUIUpdateItemKT;
import net.bi4vmr.study.updatelist.TestUIUpdateList;
import net.bi4vmr.study.updatelist.TestUIUpdateListKT;
import net.bi4vmr.study.viewcache.TestUIViewCache;
import net.bi4vmr.study.viewtype.TestUIViewType;
import net.bi4vmr.study.viewtype.TestUIViewTypeKT;

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

        // 加载多种表项
        binding.btnViewType.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIViewType.class);
            startActivity(intent);
        });

        // 动态更新列表
        binding.btnUpdateList.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUpdateList.class);
            startActivity(intent);
        });

        // 局部刷新
        binding.btnUpdateItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUpdateItem.class);
            startActivity(intent);
        });

        // DiffUtil
        binding.btnDiffUtil.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDiffUtil.class);
            startActivity(intent);
        });

        // DiffUtil - 异步计算
        binding.btnDiffUtilAsync.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDiffUtilAsync.class);
            startActivity(intent);
        });

        // 缓存与复用
        binding.btnViewCache.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIViewCache.class);
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

        // 加载多种表项(KT)
        binding.btnViewTypeKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIViewTypeKT.class);
            startActivity(intent);
        });

        // 动态更新列表(KT)
        binding.btnUpdateListKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUpdateListKT.class);
            startActivity(intent);
        });

        // 局部刷新(KT)
        binding.btnUpdateItemKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUpdateItemKT.class);
            startActivity(intent);
        });

        // DiffUtil(KT)
        binding.btnDiffUtilKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDiffUtilKT.class);
            startActivity(intent);
        });

        // DiffUtil - 异步计算(KT)
        binding.btnDiffUtilAsyncKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDiffUtilKT.class);
            startActivity(intent);
        });
    }
}
