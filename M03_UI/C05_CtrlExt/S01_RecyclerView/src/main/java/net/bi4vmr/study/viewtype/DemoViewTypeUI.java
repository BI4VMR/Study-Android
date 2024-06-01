package net.bi4vmr.study.viewtype;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoViewTypeUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_viewtype);

        // 制造测试数据
        List<ListItem> datas = new ArrayList<>();
        datas.add(new Type1VO("项目一", "这是类型I"));
        datas.add(new Type1VO("项目二", "这是类型I"));
        datas.add(new Type2VO("这是类型II"));
        datas.add(new Type1VO("项目三", "这是类型I"));
        datas.add(new Type2VO("这是类型II"));
        for (int i = 1; i <= 5; i++) {
            datas.add(new Type1VO("项目" + i));
        }

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getApplicationContext(), datas);
        recyclerView.setAdapter(adapter);
    }
}
