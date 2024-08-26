package net.bi4vmr.study.merge;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiMergeBinding;
import net.bi4vmr.study.databinding.TitleMergeBinding;

public class TestUIMerge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestuiMergeBinding binding = TestuiMergeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 使用Merge布局ViewBinding类的"bind()"方法获取实例
        TitleMergeBinding titleBinding = TitleMergeBinding.bind(binding.getRoot());
        // 访问标题布局中的文本控件，设置标题。
        titleBinding.tvTitle.setText("请输入登录信息");
        // 访问标题布局中的按钮控件，设置点击事件监听器。
        titleBinding.btnBack.setOnClickListener(v -> finish());

        // 访问登录按钮控件，设置点击事件监听器。
        binding.btnLogin.setOnClickListener(v -> {
            // 访问用户名与密码输入控件，获取当前内容。
            String name = binding.etUsername.getText().toString();
            String pwd = binding.etPassword.getText().toString();
            Toast.makeText(this, "名称: " + name + " ;密码: " + pwd, Toast.LENGTH_SHORT)
                    .show();
        });
    }
}
