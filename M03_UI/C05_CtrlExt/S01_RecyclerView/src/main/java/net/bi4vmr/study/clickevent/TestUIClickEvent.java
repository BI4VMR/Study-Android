package net.bi4vmr.study.clickevent;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.databinding.TestuiClickeventBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：点击事件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIClickEvent extends AppCompatActivity {

    private TestuiClickeventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiClickeventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 制造测试数据
        List<SimpleVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new SimpleVO("项目" + (i + 1)));
        }

        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvContent.setLayoutManager(linearLayoutManager);
        // 设置适配器
        MyAdapter adapter = new MyAdapter(datas);
        binding.rvContent.setAdapter(adapter);
        // 设置表项点击监听器
        adapter.setItemClickListener((position, item) -> {
            /* “表项被点击”事件回调 */
            Toast.makeText(this, "表项" + (position + 1), Toast.LENGTH_SHORT)
                    .show();
        });
    }
}
