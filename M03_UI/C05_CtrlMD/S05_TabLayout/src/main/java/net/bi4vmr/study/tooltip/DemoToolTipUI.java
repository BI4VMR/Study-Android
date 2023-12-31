package net.bi4vmr.study.tooltip;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import net.bi4vmr.study.R;

public class DemoToolTipUI extends AppCompatActivity {

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

        // 关闭工具提示
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                // 禁用长按事件
                tab.view.setLongClickable(false);
                // 针对API 26及以上系统还需要清空TooltipText
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // 可以设置为"null"，也可以设置为空字符串""。
                    tab.view.setTooltipText(null);
                }
            }
        }
    }
}
