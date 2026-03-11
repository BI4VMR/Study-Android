package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.binding.TestUIBindingKT;
import net.bi4vmr.study.binding_simple.TestUISimpleBindingKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.diffutil.TestUIDiffUtilKT;
import net.bi4vmr.study.event.TestUIEvent;
import net.bi4vmr.study.event.TestUIEventKT;
import net.bi4vmr.study.simple.TestUISimpleKT;

/**
 * 主页。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 表项点击事件
        binding.btnEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEvent.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 单一表项类型(KT)
        binding.btnSimpleKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISimpleKT.class);
            startActivity(intent);
        });

        // 表项点击事件(KT)
        binding.btnEventKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIEventKT.class);
            startActivity(intent);
        });

        // DiffUtil(KT)
        binding.btnDiffUtilKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDiffUtilKT.class);
            startActivity(intent);
        });

        // ViewBinding支持(KT)
        binding.btnBindingKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBindingKT.class);
            startActivity(intent);
        });

        // ViewBinding支持（单一表项类型）(KT)
        binding.btnSimpleBindingKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISimpleBindingKT.class);
            startActivity(intent);
        });
    }
}
