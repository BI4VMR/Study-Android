package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        DrawerLayout layoutDrawer = findViewById(R.id.layoutDrawer);

        Button btnPopStart = findViewById(R.id.btnPopStart);
        btnPopStart.setOnClickListener(v -> {
            // 弹出左侧抽屉
            layoutDrawer.openDrawer(GravityCompat.START);
        });

        Button btnPopEnd = findViewById(R.id.btnPopEnd);
        btnPopEnd.setOnClickListener(v -> {
            // 弹出右侧抽屉
            layoutDrawer.openDrawer(GravityCompat.END);
        });

        // 设置抽屉状态监听器
        layoutDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // 当侧滑界面完全打开时触发一次
                Log.i("myapp", "OnDrawerOpened.");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // 当侧滑界面完全关闭时触发一次
                Log.i("myapp", "OnDrawerClosed.");
            }

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // 当侧滑界面滑动过程中持续触发，参数"slideOffset"为滑动偏移量。
                Log.i("myapp", "OnDrawerSlide. Offset:[" + slideOffset + "]");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // 当侧滑界面滑动状态改变时触发。
                Log.i("myapp", "OnDrawerStateChanged. State:[" + newState + "]");
            }
        });
    }
}
