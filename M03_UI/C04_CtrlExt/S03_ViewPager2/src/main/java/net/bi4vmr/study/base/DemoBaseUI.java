package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        // 为ViewPager设置适配器
        ViewPager2 viewPager2 = findViewById(R.id.vp2Content);
        MyVPAdapter adapter = new MyVPAdapter(this, pages);
        viewPager2.setAdapter(adapter);

        // 设置页面滑动监听器
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                Log.i("myapp", "PageChangeCallback-PageSelected. Index:" + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("myapp", "PageChangeCallback-PageScrolled. Offset:" + positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("myapp", "PageChangeCallback-PageScrollStateChanged. State:" + state);
            }
        });

        Button b = findViewById(R.id.btnSet);
        b.setOnClickListener(v -> {
            viewPager2.setCurrentItem(3);
        });
    }
}
