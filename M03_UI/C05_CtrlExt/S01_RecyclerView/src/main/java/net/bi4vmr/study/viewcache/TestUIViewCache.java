package net.bi4vmr.study.viewcache;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class TestUIViewCache extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_viewcache);

        // 配置RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 数据源中第三、四项是类型II,其它项都是类型I。
        MyAdapter adapter = new MyAdapter(getTestDatas());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Name        : 获取测试数据
     * <p>
     * Description : 获取测试数据。
     *
     * @return 测试数据List
     */
    private List<ListItem> getTestDatas() {
        // 制造测试数据
        List<ListItem> datas = new ArrayList<>();
        Type1VO item1 = new Type1VO("项目1");
        Type1VO item2 = new Type1VO("项目2");
        Type2VO item3 = new Type2VO("项目3");
        Type2VO item4 = new Type2VO("项目4");
        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);
        for (int i = 5; i <= 20; i++) {
            datas.add(new Type1VO("项目" + i));
        }

        return datas;
    }
}
