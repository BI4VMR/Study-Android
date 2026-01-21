package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.index.TestUIIndex;
import net.bi4vmr.study.index.TestUIIndexKT;
import net.bi4vmr.study.remote.TestUIRemote;
import net.bi4vmr.study.remote.TestUIRemoteKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 资源索引
        binding.btnResIndex.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIIndex.class);
            startActivity(intent);
        });

        // 外部资源
        binding.btnResRemote.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIRemote.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 资源索引(KT)
        binding.btnResIndexKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIIndexKT.class);
            startActivity(intent);
        });

        // 外部资源(KT)
        binding.btnResRemoteKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIRemoteKT.class);
            startActivity(intent);
        });
    }
}
