package net.bi4vmr.study.loop2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLoopBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUILoop2 extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILoop2.class.getSimpleName();

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

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener(v -> {
            binding.viewpager2.setCurrentItem(2, false);
        });
    }

    private void makeDataList() {
        // 创建测试页面1-3
        List<String> nameList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            nameList.add("页面" + i);
        }

        // 更新数据
        adapter.updateDatas(nameList);
        // 数据更新完毕后，切换至中间的一页。
        binding.viewpager2.post(() -> {
            if (adapter.getItemCount() > 0) {
                binding.viewpager2.setCurrentItem(adapter.getMiddlePosition(), false);
            }
        });
    }
}
