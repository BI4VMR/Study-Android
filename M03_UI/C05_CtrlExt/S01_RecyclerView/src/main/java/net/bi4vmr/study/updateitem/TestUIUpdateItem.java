package net.bi4vmr.study.updateitem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.databinding.TestuiUpdateitemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：局部刷新。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIUpdateItem extends AppCompatActivity {

    private TestuiUpdateitemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiUpdateitemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvContent.setLayoutManager(linearLayoutManager);
        // 添加分割线
        binding.rvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getTestDatas());
        binding.rvContent.setAdapter(adapter);

        /* 初始化按钮 */
        // 改变第二项标题的按钮
        binding.btChange1.setOnClickListener(v -> {
            // 获取原表项
            ItemVO oldData = adapter.getDataSource().get(1);
            ItemVO newData = new ItemVO();
            // 修改标题
            newData.setTitle("这是新的标题");
            // 复制未改变变的属性
            newData.setInfo(oldData.getInfo());

            // 调用局部更新方法，指明需要更新标题。
            adapter.updateItem(1, newData, UpdateFlags.FLAG_TITLE);
        });

        // 改变第四项的按钮
        binding.btChange2.setOnClickListener(v -> {
            ItemVO newData = new ItemVO();
            newData.setTitle("这是新的标题");
            newData.setInfo("这是新的描述");

            // 调用局部更新方法，指明需要更新标题与描述。
            adapter.updateItem(3, newData, UpdateFlags.FLAG_TITLE | UpdateFlags.FLAG_INFO);
        });

        // 重置按钮
        binding.btReload.setOnClickListener(v -> {
            // 重新加载整个列表
            adapter.reloadItems(getTestDatas());
        });
    }

    // 生成测试数据
    private List<ItemVO> getTestDatas() {
        // 制造测试数据
        List<ItemVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new ItemVO("项目" + (i + 1)));
        }

        return datas;
    }
}
