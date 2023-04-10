package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        TextView tvText = findViewById(R.id.tv_text);
        Button btWrite = findViewById(R.id.bt_write);
        Button btRead = findViewById(R.id.bt_read);

        //写入按钮事件
        btWrite.setOnClickListener(v -> {
            // 获取SharedPreferences实例
            SharedPreferences sp = getSharedPreferences("test", Context.MODE_PRIVATE);
            // 获取Editor实例
            SharedPreferences.Editor editor = sp.edit();
            // 存入数据
            editor.putInt("Data_Int", 100);
            editor.putBoolean("Data_Boolean", true);
            editor.putString("Data_String", "测试文字");
            // 提交变更
            editor.apply();
            Toast.makeText(this, "数据写入成功", Toast.LENGTH_SHORT).show();
        });

        // 读取按钮事件
        btRead.setOnClickListener(v -> {
            // 获取SharedPreferences实例
            SharedPreferences sp = getSharedPreferences("test", Context.MODE_PRIVATE);
            // 读取数值
            int i = sp.getInt("Data_Int", 0);
            boolean b = sp.getBoolean("Data_Boolean", false);
            String s = sp.getString("Data_String", "Empty.");
            // 设置文本
            tvText.setText("数字：" + i + "\n布尔值：" + b + "\n字符串：" + s);
        });
    }
}
