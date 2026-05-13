package net.bi4vmr.study.layout.grid.types;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import net.bi4vmr.study.databinding.TestuiViewtypeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：加载多种表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILayoutGridViewType extends AppCompatActivity {

    private TestuiViewtypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiViewtypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 制造测试数据
        List<ListItem> datas = new ArrayList<>();
        datas.add(new TitleVO("标题一", "这是类型I"));
        datas.add(new GridVO("A1"));
        datas.add(new GridVO("A2"));
        datas.add(new GridVO("A3"));
        datas.add(new GridVO("A4"));
        datas.add(new GridVO("A5"));
        datas.add(new TitleVO("标题二", "这是类型I"));
        datas.add(new GridVO("B1"));
        datas.add(new GridVO("B2"));
        datas.add(new GridVO("B3"));


        // 设置适配器
        MyAdapter adapter = new MyAdapter(datas);
        binding.rvContent.setAdapter(adapter);

        // 设置布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                if (viewType == 1) {
                    return 4; // 类型I占用4列
                } else {
                    return 1; // 类型II占用1列
                }
            }
        });
        binding.rvContent.addItemDecoration(new GridItemSpacer(10,0,new ArrayList<>(List.of(MyAdapter.Type1VH.class))));
        binding.rvContent.setLayoutManager(layoutManager);
    }
}
