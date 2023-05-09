package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.UiDemoBaseBinding;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使用ViewBinding时，不再通过"setContentView(int ResID)"方法加载布局。
        // setContentView(R.layout.ui_demo_base);

        // 获取当前页面的ViewBinding对象
        UiDemoBaseBinding binding = UiDemoBaseBinding.inflate(getLayoutInflater());
        // 使用"setContentView(View view)"方法加载布局。
        setContentView(binding.getRoot());

        // 访问标题控件，设置标题
        binding.tvTitle.setText("请输入登录");
        // 访问登录按钮控件，设置点击事件监听器。
        binding.btnLogin.setOnClickListener(v -> {
            // 访问用户名与密码输入控件，获取当前内容。
            String name = binding.etUsername.getText().toString();
            String pwd = binding.etPassword.getText().toString();
            Log.i("myapp", "名称: " + name + " ; 密码: " + pwd);
        });
    }
}
