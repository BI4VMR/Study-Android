package net.bi4vmr.study.useinadapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class TestUIUseInAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_useinadapter);

        // 制造测试数据
        List<SimpleVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new SimpleVO("项目" + (i + 1)));
        }

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvList);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getApplicationContext(), datas);
        recyclerView.setAdapter(adapter);
    }
}
