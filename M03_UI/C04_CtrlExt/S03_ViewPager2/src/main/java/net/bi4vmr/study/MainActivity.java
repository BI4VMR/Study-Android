package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.nested.DemoNestedUI;

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

        // ViewPager2嵌套
        Button btnNested = findViewById(R.id.btnNested);
        btnNested.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoNestedUI.class);
            startActivity(intent);
        });
    }
}
