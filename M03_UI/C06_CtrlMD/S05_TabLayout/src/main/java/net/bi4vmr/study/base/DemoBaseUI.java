package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        TabLayout tabLayout = findViewById(R.id.viewTab);

        // 创建Tab
        TabLayout.Tab tab1 = tabLayout.newTab()
                .setText("页面一");
        TabLayout.Tab tab2 = tabLayout.newTab()
                .setText("页面二");
        TabLayout.Tab tab3 = tabLayout.newTab()
                .setText("页面三");

        // 将Tab按顺序添加至TabLayout中
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
    }
}
