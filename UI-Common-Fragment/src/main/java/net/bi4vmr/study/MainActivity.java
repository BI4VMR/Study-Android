package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.initdata.DemoInitDataUI;
import net.bi4vmr.study.manage.DemoManageUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBaseUI.class);
            startActivity(intent);
        });

        // 传递初始化参数
        Button btnInitData = findViewById(R.id.btnInitData);
        btnInitData.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoInitDataUI.class);
            startActivity(intent);
        });

        // Fragment管理
        Button btnManage = findViewById(R.id.btnManage);
        btnManage.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoManageUI.class);
            startActivity(intent);
        });
    }
}
