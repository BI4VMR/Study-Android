package net.bi4vmr.study;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.box.TestUIBox;
import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.modifier.TestUIModifier;
import net.bi4vmr.study.scaffold.TestUIScaffold;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 根据主题设置状态栏图标颜色 */
        int darkModeFlag = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES);
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(!darkMode);

        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
         * Target SDK 为 Android 15 或更高版本时，系统强制启用 Edge-to-Edge 模式，且无法关闭。
         *
         * 此处添加 Insets 监听，将根布局内容绘制在状态栏和导航栏之间，避免重叠。
         */
        View rootView = binding.getRoot();
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, bars.top, 0, 0);
            return WindowInsetsCompat.CONSUMED;
        });
        ViewCompat.requestApplyInsets(rootView);


        // 全局属性
        binding.btnModifier.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIModifier.class);
            startActivity(intent);
        });

        // Column / Row
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // Box
        binding.btnBox.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBox.class);
            startActivity(intent);
        });

        // Scaffold
        binding.btnScaffold.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIScaffold.class);
            startActivity(intent);
        });
    }
}
