package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.androidvm.TestUIAndroidVM;
import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.lifecycle.TestUILifeCycle;

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

        // 生命周期
        binding.btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycle.class);
            startActivity(intent);
        });

        // AndroidViewModel
        binding.btnAndroidVM.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAndroidVM.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 生命周期(KT)
        binding.btnLifeCycleKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycle.class);
            startActivity(intent);
        });

        // AndroidViewModel(KT)
        binding.btnAndroidVMKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAndroidVM.class);
            startActivity(intent);
        });
    }
}
