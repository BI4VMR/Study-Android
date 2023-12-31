package net.bi4vmr.study.updatelist;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoUpdateListUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_updatelist);

        RecyclerView recyclerView = findViewById(R.id.rvContent);
        Button btAdd = findViewById(R.id.btAdd);
        Button btDelete = findViewById(R.id.btDel);
        Button btUpdate = findViewById(R.id.btUpdate);
        Button btMove = findViewById(R.id.btMove);
        Button btReload = findViewById(R.id.btReload);

        /* 初始化RecyclerView */
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getApplicationContext(), getTestDatas());
        recyclerView.setAdapter(adapter);

        /* 初始化按钮 */
        // 新增按钮
        btAdd.setOnClickListener(v -> {
            // 插入一条记录到第3项
            SimpleVO newItem = new SimpleVO("这是新增加的表项");
            adapter.addItem(2, newItem);
        });
        // 删除按钮
        btDelete.setOnClickListener(v -> {
            // 删除第4项
            adapter.removeItem(4);
        });
        // 更新按钮
        btUpdate.setOnClickListener(v -> {
            // 更新第6项
            SimpleVO newItem = new SimpleVO("这是更新后的表项");
            adapter.updateItem(5, newItem);
        });
        // 移动按钮
        btMove.setOnClickListener(v -> {
            // 将当前列表中的第2项移动至5号位置
            adapter.moveItem(2, 5);
        });
        // 重置按钮
        btReload.setOnClickListener(v -> {
            // 重新加载整个列表
            adapter.reloadItem(getTestDatas());
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
