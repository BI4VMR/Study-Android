package net.bi4vmr.study.config;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiConfigBinding;

public class TestUIConfig extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIConfig.class.getSimpleName();

    private TestuiConfigBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate.");
        binding = TestuiConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.root);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "OnConfigurationChanged.");

        // 从标志位中分离出主题代码
        switch (newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            /* 浅色模式（默认） */
            case Configuration.UI_MODE_NIGHT_NO:
                Log.i(TAG, "OnConfigurationChanged. NightMode OFF.");
                binding.tvInfo.setTextColor(Color.BLACK);
                binding.root.setBackgroundColor(Color.WHITE);
                break;
            /* 深色模式 */
            case Configuration.UI_MODE_NIGHT_YES:
                Log.i(TAG, "OnConfigurationChanged. NightMode ON.");
                binding.tvInfo.setTextColor(Color.WHITE);
                binding.root.setBackgroundColor(Color.BLACK);
                break;
        }
    }
}
