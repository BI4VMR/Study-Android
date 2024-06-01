package net.bi4vmr.study.cache;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiCacheBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUICache extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUICache.class.getSimpleName();

    private TestuiCacheBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiCacheBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            pages.add(TestFragment.newInstance("页面" + i));
        }

        // 创建适配器实例
        MyVPAdapter adapter = new MyVPAdapter(this, pages);
        // 将适配器与ViewPager2绑定
        binding.viewpager2.setAdapter(adapter);

        // 按钮：“更新数据”
        binding.btnUpdate.setOnClickListener(v -> {
            // 创建新的数据源
            List<TestFragment> pages2 = new ArrayList<>();
            for (int i = 11; i <= 13; i++) {
                pages2.add(TestFragment.newInstance("页面" + i));
            }
            adapter.updateDatas(pages2);
        });
    }
}
