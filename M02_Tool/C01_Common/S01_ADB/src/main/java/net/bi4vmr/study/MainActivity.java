package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.shellinapp.TestUIShellInAPP;
import net.bi4vmr.study.shellinapp.TestUIShellInAPPKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 在应用程序中执行ADB命令
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIShellInAPP.class);
            startActivity(intent);
        });

        // 在应用程序中执行ADB命令(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIShellInAPPKT.class);
            startActivity(intent);
        });
    }
}
