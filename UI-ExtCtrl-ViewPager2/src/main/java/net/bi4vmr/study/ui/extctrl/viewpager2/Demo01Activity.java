package net.bi4vmr.study.ui.extctrl.viewpager2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import net.bi4vmr.study.ui.extctrl.viewpager2.adapter.Adapter01;

import java.util.ArrayList;
import java.util.List;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        // 为ViewPager设置适配器
        ViewPager2 viewPager2 = findViewById(R.id.vp2Content);
        Adapter01 adapter = new Adapter01(this, pages);
        viewPager2.setAdapter(adapter);
    }
}