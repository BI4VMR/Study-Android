package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.clickevent.DemoClickEventUI;
import net.bi4vmr.study.diffutil.DemoDiffUtilUI;
import net.bi4vmr.study.updateitem.DemoUpdateItemUI;
import net.bi4vmr.study.updatelist.DemoUpdateListUI;
import net.bi4vmr.study.viewcache.DemoViewCacheUI;
import net.bi4vmr.study.viewtype.DemoViewTypeUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 点击事件
        Button btnClickEvent = findViewById(R.id.btnClickEvent);
        btnClickEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoClickEventUI.class);
            startActivity(intent);
        });

        // 多种View类型
        Button btnViewType = findViewById(R.id.btnViewType);
        btnViewType.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoViewTypeUI.class);
            startActivity(intent);
        });

        // 动态更新列表
        Button btnUpdateList = findViewById(R.id.btnUpdateList);
        btnUpdateList.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoUpdateListUI.class);
            startActivity(intent);
        });

        // 局部刷新
        Button btnUpdateItem = findViewById(R.id.btnUpdateItem);
        btnUpdateItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoUpdateItemUI.class);
            startActivity(intent);
        });

        // DiffUtil
        Button btnDiffUtil = findViewById(R.id.btnDiffUtil);
        btnDiffUtil.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoDiffUtilUI.class);
            startActivity(intent);
        });

        // 缓存与复用
        Button btnViewCache = findViewById(R.id.btnViewCache);
        btnViewCache.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoViewCacheUI.class);
            startActivity(intent);
        });
    }
}
