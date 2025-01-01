package net.bi4vmr.study.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            pages.add(TestFragment.newInstance("页面" + i));
        }

        // 创建适配器实例
        MyVPAdapter adapter = new MyVPAdapter(getSupportFragmentManager(), pages);
        // 将适配器与ViewPager绑定
        binding.viewpager.setAdapter(adapter);

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener(v -> {
            binding.viewpager.setCurrentItem(2, false);
        });
    }
}
