package net.bi4vmr.study.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLifecycleBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUILifeCycle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILifeCycle.class.getSimpleName();

    private TestuiLifecycleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLifecycleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            pages.add(TestFragment.newInstance("页面" + i + " "));
        }

        // 创建适配器实例
        MyVPAdapter adapter = new MyVPAdapter(this, pages);
        // 将适配器与ViewPager2绑定
        binding.viewpager2.setAdapter(adapter);

        // 预加载当前页面左右的1个页面
        binding.viewpager2.setOffscreenPageLimit(1);
    }
}
