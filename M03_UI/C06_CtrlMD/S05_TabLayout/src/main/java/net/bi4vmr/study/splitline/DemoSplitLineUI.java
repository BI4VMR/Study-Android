package net.bi4vmr.study.splitline;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;

import net.bi4vmr.study.R;

public class DemoSplitLineUI extends AppCompatActivity {

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

        // 获取TabLayout的容器实例
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        // 开启分隔符
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        // 设置分隔符图形素材
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divide_line));
        // 设置内边距
        linearLayout.setDividerPadding(25);
    }
}
