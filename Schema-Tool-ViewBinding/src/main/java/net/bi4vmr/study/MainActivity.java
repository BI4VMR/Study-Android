package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.include.DemoIncludeUI;
import net.bi4vmr.study.merge.DemoMergeUI;
import net.bi4vmr.study.useinadapter.DemoUseInAdapterUI;
import net.bi4vmr.study.useinfragment.DemoUseInFragmentUI;

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

        // 在Fragment中使用
        Button btnUseInFragment = findViewById(R.id.btnUseInFragment);
        btnUseInFragment.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoUseInFragmentUI.class);
            startActivity(intent);
        });

        // 在Adapter中使用
        Button btnUseInAdapter = findViewById(R.id.btnUseInAdapter);
        btnUseInAdapter.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoUseInAdapterUI.class);
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
