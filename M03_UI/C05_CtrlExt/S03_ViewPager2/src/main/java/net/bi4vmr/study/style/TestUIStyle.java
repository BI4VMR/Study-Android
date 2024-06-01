package net.bi4vmr.study.style;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.databinding.TestuiStyleBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUIStyle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIStyle.class.getSimpleName();

    private TestuiStyleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        MyVPAdapter adapter = new MyVPAdapter(this, pages);
        binding.vp2Direction.setAdapter(adapter);

        /* 设置边缘阴影效果 */
        View rv = binding.vp2Direction.getChildAt(0);
        if (rv instanceof RecyclerView) {
            // 关闭边缘阴影效果
            rv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }
}
