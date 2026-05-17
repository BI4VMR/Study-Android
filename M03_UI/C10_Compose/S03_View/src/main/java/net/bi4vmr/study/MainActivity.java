package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.modifier.TestUIModifierLayout;
import net.bi4vmr.study.modifier.TestUIModifierStyle;
import net.bi4vmr.study.modifier.TestUIModifierTransform;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Modifier - 布局
        binding.btnModifierLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIModifierLayout.class);
            startActivity(intent);
        });

        // Modifier - 样式
        binding.btnModifierStyle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIModifierStyle.class);
            startActivity(intent);
        });

        // Modifier - 变换
        binding.btnModifierTransform.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIModifierTransform.class);
            startActivity(intent);
        });
    }
}
