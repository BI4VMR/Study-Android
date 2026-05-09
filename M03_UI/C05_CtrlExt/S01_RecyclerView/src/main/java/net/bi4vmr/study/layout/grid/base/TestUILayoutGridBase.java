package net.bi4vmr.study.layout.grid.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import net.bi4vmr.study.base.MyAdapter;
import net.bi4vmr.study.base.SimpleVO;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILayoutGridBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILayoutGridBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 制造测试数据
        List<net.bi4vmr.study.base.SimpleVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new SimpleVO("项目" + (i + 1)));
        }

        // 设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        binding.rvContent.setLayoutManager(gridLayoutManager);
        // 设置适配器
        MyAdapter adapter = new MyAdapter(datas);
        binding.rvContent.setAdapter(adapter);
    }
}
