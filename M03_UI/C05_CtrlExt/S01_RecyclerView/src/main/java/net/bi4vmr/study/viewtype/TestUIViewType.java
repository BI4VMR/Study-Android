package net.bi4vmr.study.viewtype;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.databinding.TestuiViewtypeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：加载多种表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIViewType extends AppCompatActivity {

    private TestuiViewtypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiViewtypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvContent.setLayoutManager(linearLayoutManager);
        // 设置适配器
        MyAdapter adapter = new MyAdapter(datas);
        binding.rvContent.setAdapter(adapter);
    }
}
