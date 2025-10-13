package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.lifecycle.DemoLifeCycleUI;

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

        // Service的生命周期
        Button btnLifeCycle = findViewById(R.id.btnLifeCycle);
        btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoLifeCycleUI.class);
            startActivity(intent);
        });
    }
}
