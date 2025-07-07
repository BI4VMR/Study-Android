package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.lifecycle.TestUILifeCycle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Service的生命周期
        binding.btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycle.class);
            startActivity(intent);
        });
    }
}
