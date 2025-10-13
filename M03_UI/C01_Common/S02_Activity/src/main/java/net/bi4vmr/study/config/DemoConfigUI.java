package net.bi4vmr.study.config;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoConfigUI extends AppCompatActivity {

    private View viewRoot;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myapp", "OnCreate.");
        setContentView(R.layout.ui_demo_config);

        viewRoot = findViewById(R.id.root);
        tvInfo = findViewById(R.id.tvInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("myapp", "OnStart.");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("myapp", "OnRestoreInstanceState.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("myapp", "OnResume.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("myapp", "OnPause.");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("myapp", "OnSaveInstanceState.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("myapp", "OnStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "OnDestroy.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("myapp", "OnRestart.");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("myapp", "OnConfigurationChanged.");
        // 从标志位中分离出主题代码
        switch (newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_NO:
                /* 浅色模式（默认） */
                Log.i("myapp", "OnConfigurationChanged. NightMode OFF.");
                tvInfo.setTextColor(Color.BLACK);
                viewRoot.setBackgroundColor(Color.WHITE);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                /* 深色模式 */
                Log.i("myapp", "OnConfigurationChanged. NightMode ON.");
                tvInfo.setTextColor(Color.WHITE);
                viewRoot.setBackgroundColor(Color.BLACK);
                break;
        }
    }
}
