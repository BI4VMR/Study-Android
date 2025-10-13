package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.contracts.DemoContractsUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 动态请求权限（旧API）
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBaseUI.class);
            startActivity(intent);
        });

        // 动态请求权限（新API）
        Button btnRequest2 = findViewById(R.id.btnRequest2);
        btnRequest2.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoContractsUI.class);
            startActivity(intent);
        });
    }
}
