package net.bi4vmr.study.nested;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoNestedUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_nested);

        // 创建测试页面
        List<NestedFragment> pages = new ArrayList<>();
        pages.add(NestedFragment.newInstance("外层页面1"));
        pages.add(NestedFragment.newInstance("外层页面2"));
        pages.add(NestedFragment.newInstance("外层页面3"));

        // 为ViewPager设置适配器
        ViewPager2 viewPager2 = findViewById(R.id.vp2Outter);
        OutterVPAdapter adapter = new OutterVPAdapter(this, pages);
        viewPager2.setAdapter(adapter);
    }
}
