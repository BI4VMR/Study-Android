package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.buildconfig.TestUIBuildConfig;
import net.bi4vmr.study.buildconfig.TestUIBuildConfigKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;

/**
 * 主页。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 读取BuildConfig中的变量
        binding.btnBuildConfig.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBuildConfig.class);
            startActivity(intent);
        });

        // 读取BuildConfig中的变量(KT)
        binding.btnBuildConfigKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBuildConfigKT.class);
            startActivity(intent);
        });
    }
}
