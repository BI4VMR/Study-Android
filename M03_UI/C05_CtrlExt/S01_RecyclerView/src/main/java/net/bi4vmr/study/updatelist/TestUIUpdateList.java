package net.bi4vmr.study.updatelist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.databinding.TestuiUpdatelistBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：动态更新表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIUpdateList extends AppCompatActivity {

    private TestuiUpdatelistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiUpdatelistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 初始化RecyclerView */
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvContent.setLayoutManager(linearLayoutManager);
        // 添加分割线
        binding.rvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getTestDatas());
        binding.rvContent.setAdapter(adapter);

        /* 初始化按钮 */
        // 新增按钮
        binding.btAdd.setOnClickListener(v -> {
            // 插入一条记录到第3项
            SimpleVO newItem = new SimpleVO("这是新增加的表项");
            adapter.addItem(2, newItem);
        });

        // 删除按钮
        binding.btDel.setOnClickListener(v -> {
            // 删除第4项
            adapter.removeItem(4);
        });

        // 更新按钮
        binding.btUpdate.setOnClickListener(v -> {
            // 更新第6项
            SimpleVO newItem = new SimpleVO("这是更新后的表项");
            adapter.updateItem(5, newItem);
        });

        // 移动按钮
        binding.btMove.setOnClickListener(v -> {
            // 将当前列表中的第2项移动至5号位置
            adapter.moveItem(2, 5);
        });

        // 重置按钮
        binding.btReload.setOnClickListener(v -> {
            // 重新加载整个列表
            adapter.reloadItems(getTestDatas());
        });
    }

    // 生成测试数据
    private List<SimpleVO> getTestDatas() {
        // 制造测试数据
        List<SimpleVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new SimpleVO("项目" + (i + 1)));
        }

        return datas;
    }
}
