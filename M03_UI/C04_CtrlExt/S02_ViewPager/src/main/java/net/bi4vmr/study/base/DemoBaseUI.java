package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        ViewPager vpTest = findViewById(R.id.vpTest);
        Button btnSwitchPage = findViewById(R.id.btnSwitchPage);

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        // 将适配器与ViewPager绑定
        vpTest.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), pages));

        btnSwitchPage.setOnClickListener(v -> {
            // 切换至第三页
            vpTest.setCurrentItem(2, false);
        });

        // 注册页面滚动监听器
        vpTest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * Name        : 页面滑动状态改变事件
             * <p>
             * Description : 当滑动状态改变时，此事件触发。
             *
             * @param state 表示滑动状态。{@link ViewPager#SCROLL_STATE_IDLE}表示滑动执行完毕，动画播放结束，目标页面已经完全显示。
             *              {@link ViewPager#SCROLL_STATE_DRAGGING}表示用户正在用手指按住屏幕，正在进行拖拽。
             *              {@link ViewPager#SCROLL_STATE_SETTLING}表示用户抬起手指，随后ViewPager将向目标页面自动滑动使其完全显示。
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("myapp", "PageChangeCallback-PageScrollStateChanged. State:" + state);
            }

            /**
             * Name        : 页面滑动状态改变事件
             * <p>
             * Description : 当滑动状态改变时，此事件触发。
             *
             * @param position 表示当前正在滑动的页面索引
             * @param positionOffset 表示当前页面滑动至目标位置的进度，取值范围为 `[0, 1)` ，数值越大则离目标位置越近
             * @param positionOffsetPixels 表示当前页面滑过的像素，取值范围为 `[0, ViewPager宽度)`
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("myapp", "PageChangeCallback-PageScrolled. Index:" + position + " Offset:" + positionOffset + " OffsetPX:" + positionOffsetPixels);
            }

            /**
             * Name        : 页面被选中事件
             * <p>
             * Description : 当滑动完毕最终选定某个页面时，此事件触发。
             *
             * @param position 表示页面在ViewPager中的索引。
             */
            @Override
            public void onPageSelected(int position) {
                Log.i("myapp", "PageChangeCallback-PageSelected. Index:" + position);
            }
        });
    }
}
