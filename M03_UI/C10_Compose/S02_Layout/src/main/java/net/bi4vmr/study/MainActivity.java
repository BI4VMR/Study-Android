package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.box.TestUIBox;
import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.modifier.TestUIModifier;
import net.bi4vmr.study.scaffold.TestUIScaffold;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
