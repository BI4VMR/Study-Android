package net.bi4vmr.study.diffutil;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoDiffUtilUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_diffutil);

        RecyclerView recyclerView = findViewById(R.id.rvContent);
        Button btRefresh = findViewById(R.id.btRefresh);
        Button btReset = findViewById(R.id.btReset);

        /* 配置RecyclerView */
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<ItemVO> datas = getTestDatas();
        MyAdapter adapter = new MyAdapter(getApplicationContext(), datas);
        recyclerView.setAdapter(adapter);

        // 刷新数据集按钮
        btRefresh.setOnClickListener(v -> {
            // 复制一份数据集
            List<ItemVO> newDatas = adapter.getCopyOfDataSource();
            // 模拟数据变更
            newDatas.get(2).setInfo("这是新的备注");
            newDatas.get(4).setTitle("这是新的标题");
            newDatas.remove(1);
            newDatas.remove(5);
            newDatas.add(8, new ItemVO(101, "新增表项"));
            // 更新列表
            adapter.updateData(newDatas);
        });

        // 重置按钮
        btReset.setOnClickListener(v -> adapter.reloadItem(getTestDatas()));
    }

    /**
     * Name        : 获取测试数据
     * <p>
     * Description : 获取测试数据。
     *
     * @return 测试数据List
     */
    private List<ItemVO> getTestDatas() {
        // 制造测试数据
        List<ItemVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            datas.add(new ItemVO(num, "项目" + num));
        }

        return datas;
    }
}
