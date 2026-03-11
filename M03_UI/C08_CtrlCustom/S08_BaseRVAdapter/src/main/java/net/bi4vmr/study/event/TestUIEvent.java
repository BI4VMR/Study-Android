package net.bi4vmr.study.event;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiEventBinding;
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseAdapter;
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：表项点击事件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIEvent extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIEvent.class.getSimpleName();

    private TestuiEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        // 准备测试数据
        List<ListItem> dataSource = initTestData();
        // 创建Adapter
        TestMultiTypeAdapter adapter = new TestMultiTypeAdapter(dataSource);
        // 配置RecyclerView
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);

        // 设置或取消监听器
        binding.tbtnClick.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                adapter.setItemClickListener(new ItemClickListener());
            } else {
                adapter.setItemClickListener(null);
            }
        });
    }

    private List<ListItem> initTestData() {
        List<ListItem> list = new ArrayList<>();

        list.add(new TitleVO("标题一"));
        list.add(new ContentVO("表项A", "表项A", R.drawable.ic_funny_256));
        list.add(new ContentVO("表项B", "表项B", R.drawable.ic_funny_256));
        list.add(new TitleVO("标题二"));
        list.add(new ContentVO("表项C", "表项C", R.drawable.ic_funny_256));
        list.add(new ContentVO("表项D", "表项D", R.drawable.ic_funny_256));
        list.add(new ContentVO("表项E", "表项E", R.drawable.ic_funny_256));

        return list;
    }

    /**
     * 点击监听器实现类
     */
    private class ItemClickListener implements BaseAdapter.ItemClickListener<ListItem> {

        @Override
        public void onItemClick(int position, ListItem item, @NonNull View view) {
            Toast.makeText(
                    getApplicationContext(),
                    "点击了第" + position + "个表项，内容：" + item,
                    Toast.LENGTH_SHORT
            ).show();
        }

        @Override
        public boolean onItemLongClick(int position, ListItem item, @NonNull View view) {
            Toast.makeText(
                    getApplicationContext(),
                    "长按了第" + position + "个表项，内容：" + item,
                    Toast.LENGTH_SHORT
            ).show();
            return true;
        }
    }
}
