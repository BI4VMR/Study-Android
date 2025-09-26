package net.bi4vmr.study.diffutil;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import net.bi4vmr.study.databinding.TestuiDiffutilBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：DiffUtil。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIDiffUtil extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIDiffUtil.class.getSimpleName();

    private TestuiDiffutilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiDiffutilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 配置RecyclerView */
        // 添加分割线
        binding.rvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<ItemVO> datas = getTestDatas();
        MyAdapter adapter = new MyAdapter(datas);
        binding.rvContent.setAdapter(adapter);

        // 刷新数据集按钮
        binding.btRefresh.setOnClickListener(v -> {
            // 复制一份数据集
            List<ItemVO> newDatas = adapter.getCopyOfDataSource();
            // 模拟数据变更
            newDatas.get(2).setInfo("这是新的备注");
            newDatas.get(4).setTitle("这是新的标题");
            newDatas.remove(1);
            newDatas.remove(5);
            newDatas.add(8, new ItemVO("新增表项", "新增表项"));
            // 更新列表
            adapter.updateData(newDatas);
        });

        // 重置按钮
        binding.btReset.setOnClickListener(v -> adapter.reloadItem(getTestDatas()));
    }

    /**
     * 获取测试数据。
     *
     * @return 测试数据。
     */
    private List<ItemVO> getTestDatas() {
        // 制造测试数据
        List<ItemVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            datas.add(new ItemVO("项目" + num));
        }

        return datas;
    }
}
