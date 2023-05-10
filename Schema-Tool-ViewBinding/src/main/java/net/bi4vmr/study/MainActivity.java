package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.include.DemoIncludeUI;
import net.bi4vmr.study.merge.DemoMergeUI;

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

        // 处理Include标签
        Button btnInclude = findViewById(R.id.btnInclude);
        btnInclude.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoIncludeUI.class);
            startActivity(intent);
        });

        // 处理Merge标签
        Button btnMerge = findViewById(R.id.btnMerge);
        btnMerge.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoMergeUI.class);
            startActivity(intent);
        });
    }
}
