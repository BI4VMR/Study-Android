package net.bi4vmr.study.loop1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import net.bi4vmr.study.databinding.TestuiLoopBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUILoop1 extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILoop1.class.getSimpleName();

    private TestuiLoopBinding binding;
    private MyVPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLoopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new MyVPAdapter(this);
        binding.viewpager2.setAdapter(adapter);

        // 构建列表并应用到适配器中
        makeDataList();

        // 注册监听器：OnPageChangeCallback
        binding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "OnPageChangeCallback-PageScrollStateChanged. State:[" + state + "]");
                // 滑动停止时进行判断
                if (state == 0) {
                    // 当前页面索引
                    int index = binding.viewpager2.getCurrentItem();
                    // 最大页面索引
                    int maxIndex = adapter.getItemCount() - 1;
                    if (index == 0) {
                        /* 当前为首页（原始列表的最后一项） */
                        // 滑动至原始列表的最后一项（即当前列表的倒数第二项）
                        binding.viewpager2.setCurrentItem(maxIndex - 1, false);
                    } else if (index == maxIndex) {
                        /* 当前为末页（原始列表的第一项） */
                        // 滑动至原始列表的第一项（即当前列表的第二项）
                        binding.viewpager2.setCurrentItem(1, false);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "OnPageChangeCallback-PageSelected. Index:[" + position + "]");
            }
        });

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener(v -> {
            binding.viewpager2.setCurrentItem(2, false);
        });
    }

    private void makeDataList() {
        // 创建测试页面1-3
        List<TestFragment> fmList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            fmList.add(TestFragment.newInstance("页面" + i));
        }

        List<TestFragment> pages = new ArrayList<>();
        // 向列表首部添加最后一项
        pages.add(TestFragment.newInstance("页面3"));
        // 添加所有的列表项
        pages.addAll(fmList);
        // 向列表尾部添加第一项
        pages.add(TestFragment.newInstance("页面1"));

        // 更新数据
        adapter.updatePages(pages);
        // 数据更新完毕后，切换至原始列表中的第一页。
        binding.viewpager2.post(() -> {
            if (adapter.getItemCount() > 0) {
                binding.viewpager2.setCurrentItem(1, false);
            }
        });
    }
}
