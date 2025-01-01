package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnWrite.setOnClickListener(v -> testWrite());
        binding.btnRead.setOnClickListener(v -> testRead());
    }

    // 写入数据
    private void testWrite() {
        binding.tvLog.append("\n--- 写入数据 ---\n");
        Log.i(TAG, "--- 写入数据 ---");

        // 获取SharedPreferences实例
        SharedPreferences sp = getSharedPreferences("kvdata", Context.MODE_PRIVATE);
        // 获取Editor实例
        SharedPreferences.Editor editor = sp.edit();
        // 存入数据
        editor.putInt("Data_Int", 100);
        editor.putBoolean("Data_Boolean", true);
        editor.putString("Data_String", "我能够吞下玻璃而不伤身。");
        // 提交变更
        editor.apply();

        binding.tvLog.append("数据写入成功，请点击读取按钮查看。");
        Log.i(TAG, "数据写入成功，请点击读取按钮查看。");
    }

    // 读取数据
    private void testRead() {
        binding.tvLog.append("\n--- 读取数据 ---\n");
        Log.i(TAG, "--- 读取数据 ---");

        // 获取SharedPreferences实例
        SharedPreferences sp = getSharedPreferences("kvdata", Context.MODE_PRIVATE);

        // 读取数值
        int i = sp.getInt("Data_Int", 0);
        boolean b = sp.getBoolean("Data_Boolean", false);
        String s = sp.getString("Data_String", "Empty.");

        // 显示内容
        String log = "数字：" + i + "\n布尔值：" + b + "\n字符串：" + s;
        binding.tvLog.append(log);
        Log.i(TAG, log);
    }
}
