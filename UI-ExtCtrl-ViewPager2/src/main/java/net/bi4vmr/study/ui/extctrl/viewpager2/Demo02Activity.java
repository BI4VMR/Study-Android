package net.bi4vmr.study.ui.extctrl.viewpager2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import net.bi4vmr.study.ui.extctrl.viewpager2.adapter.Adapter02Out;

import java.util.ArrayList;
import java.util.List;

public class Demo02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        // 创建测试页面
        List<NestedFragment> pages = new ArrayList<>();
        pages.add(NestedFragment.newInstance("外层页面1"));
        pages.add(NestedFragment.newInstance("外层页面2"));
        pages.add(NestedFragment.newInstance("外层页面3"));

        // 为ViewPager设置适配器
        ViewPager2 viewPager2 = findViewById(R.id.vp2Outter);
        Adapter02Out adapter = new Adapter02Out(this, pages);
        viewPager2.setAdapter(adapter);
    }
}