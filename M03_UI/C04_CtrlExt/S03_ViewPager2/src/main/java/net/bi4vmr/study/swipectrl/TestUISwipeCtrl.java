package net.bi4vmr.study.swipectrl;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiSwipectrlBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUISwipeCtrl extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISwipeCtrl.class.getSimpleName();

    private TestuiSwipectrlBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSwipectrlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        // 创建适配器
        MyVPAdapter adapter = new MyVPAdapter(this, pages);
        // 将适配器与ViewPager绑定
        binding.viewpager2.setAdapter(adapter);

        binding.tbtnUserInput.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 设置用户输入状态
            binding.viewpager2.setUserInputEnabled(isChecked);
        });

        binding.btnFakeDrag.setOnClickListener(v -> {
            // 开始模拟拖拽
            binding.viewpager2.beginFakeDrag();
            // 向后一个页面偏移100像素
            binding.viewpager2.fakeDragBy(-100.0F);
            // 终止模拟拖拽
            binding.viewpager2.endFakeDrag();
        });
    }
}
