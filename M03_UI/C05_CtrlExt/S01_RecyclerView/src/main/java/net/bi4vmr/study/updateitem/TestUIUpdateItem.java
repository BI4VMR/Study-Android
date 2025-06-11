package net.bi4vmr.study.updateitem;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：局部刷新。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIUpdateItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_updateitem);

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        Button bt1 = findViewById(R.id.btChange1);
        Button bt2 = findViewById(R.id.btChange2);
        Button btReload = findViewById(R.id.btReload);

        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getApplicationContext(), getTestDatas());
        recyclerView.setAdapter(adapter);

        // 改变第二项标题的按钮
        bt1.setOnClickListener(v -> {
            SimpleVO newData = new SimpleVO("这是新的标题");
            adapter.updateItem(1, newData);
        });

        // 改变第四项图标的按钮
        bt2.setOnClickListener(v -> {
            SimpleVO newData = new SimpleVO();
            newData.setIconRes(R.color.black);
            adapter.updateItem(3, newData);
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
