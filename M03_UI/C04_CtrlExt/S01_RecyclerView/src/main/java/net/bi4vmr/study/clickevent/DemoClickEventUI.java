package net.bi4vmr.study.clickevent;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;

public class DemoClickEventUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_clickevent);

        // 制造测试数据
        List<SimpleVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new SimpleVO("项目" + (i + 1)));
        }

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置适配器
        MyAdapter adapter = new MyAdapter(getApplicationContext(), datas);
        recyclerView.setAdapter(adapter);
        // 设置表项点击监听器
        adapter.setItemClickListener((position, item) -> {
            /* “表项被点击”事件回调 */
            Toast.makeText(this, "表项" + (position + 1), Toast.LENGTH_SHORT)
                    .show();
        });
    }
}
