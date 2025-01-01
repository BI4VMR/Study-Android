package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.include.TestUIInclude;
import net.bi4vmr.study.merge.TestUIMerge;
import net.bi4vmr.study.useinadapter.TestUIUseInAdapter;
import net.bi4vmr.study.useinfragment.TestUIUseInFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 在Fragment中使用
        Button btnUseInFragment = findViewById(R.id.btnUseInFragment);
        btnUseInFragment.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUseInFragment.class);
            startActivity(intent);
        });

        // 在Adapter中使用
        Button btnUseInAdapter = findViewById(R.id.btnUseInAdapter);
        btnUseInAdapter.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUseInAdapter.class);
            startActivity(intent);
        });

        // 处理Include标签
        Button btnInclude = findViewById(R.id.btnInclude);
        btnInclude.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIInclude.class);
            startActivity(intent);
        });

        // 处理Merge标签
        Button btnMerge = findViewById(R.id.btnMerge);
        btnMerge.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIMerge.class);
            startActivity(intent);
        });
    }
}
