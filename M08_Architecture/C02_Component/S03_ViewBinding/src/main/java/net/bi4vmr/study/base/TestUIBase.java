package net.bi4vmr.study.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取当前页面的ViewBinding对象
        TestuiBaseBinding binding = TestuiBaseBinding.inflate(getLayoutInflater());
        // 使用"setContentView(View view)"方法加载布局。
        setContentView(binding.getRoot());

        // 访问标题控件，设置标题
        binding.tvTitle.setText("请输入登录信息");
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
