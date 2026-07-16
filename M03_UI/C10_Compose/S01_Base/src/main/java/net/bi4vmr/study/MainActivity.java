package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.compatible.TestUIAndroidView;
import net.bi4vmr.study.compatible.TestUIComposeView;
import net.bi4vmr.study.data.TestUIData;
import net.bi4vmr.study.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 数据交互
        binding.btnData.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIData.class);
            startActivity(intent);
        });

        // 兼容性 - AndroidView
        binding.btnAndroidView.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAndroidView.class);
            startActivity(intent);
        });

        // 兼容性 - ComposeView
        binding.btnComposeView.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIComposeView.class);
            startActivity(intent);
        });
    }
}
