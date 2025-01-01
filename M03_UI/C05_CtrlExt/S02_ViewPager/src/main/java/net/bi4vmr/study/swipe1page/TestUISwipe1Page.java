package net.bi4vmr.study.swipe1page;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import net.bi4vmr.study.databinding.TestuiSwipe1pageBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUISwipe1Page extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISwipe1Page.class.getSimpleName();

    private TestuiSwipe1pageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSwipe1pageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        // 创建适配器
        MyVPAdapter adapter = new MyVPAdapter(getSupportFragmentManager(), pages);
        // 将适配器与ViewPager绑定
        binding.viewpager.setAdapter(adapter);

        // 注册监听器：OnPageChangeListener
        binding.viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "OnPageChangeListener-OnPageScrollStateChanged. State:[" + state + "]");
                binding.touchMaskView.updateVPScrollState(state);
            }
        });

        binding.btnSwitchPage.setOnClickListener(v -> {
            // 切换至第三页
            binding.viewpager.setCurrentItem(2, false);
        });
    }
}
