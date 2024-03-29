package net.bi4vmr.study.gotopage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestActivity.class.getSimpleName();

    private ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 获取开启当前Activity的Intent实例
        Intent intent = getIntent();
        if (intent != null) {
            // 根据Key获取初始化参数的值
            String info = intent.getStringExtra("PARAM_INIT");
            if (info != null) {
                Log.i(TAG, "初始化参数内容：" + info);
                binding.tvLog.append("\n初始化参数内容：" + info);
            } else {
                Log.i(TAG, "初始化参数为空。");
                binding.tvLog.append("\n初始化参数为空。");
            }
        }

        binding.btnClose.setOnClickListener(v -> {
            // 关闭当前Activity
            finish();
        });
    }
}
