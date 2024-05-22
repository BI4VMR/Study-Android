package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.apkassets.TestUIAPKAssets;
import net.bi4vmr.study.apkraw.TestUIAPKRaw;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.private_external.TestUIPrivateExternal;
import net.bi4vmr.study.private_internal.TestUIPrivateInternal;
import net.bi4vmr.study.public_data.TestUIPublicData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 读取"raw"目录资源
        binding.btnAPKRaw.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAPKRaw.class);
            startActivity(intent);
        });

        // 读取"assets"目录资源
        binding.btnAPKAssets.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAPKAssets.class);
            startActivity(intent);
        });

        // 读写应用私有数据（内部）
        binding.btnPrivateInner.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPrivateInternal.class);
            startActivity(intent);
        });

        // 读写应用私有数据（外部）
        binding.btnPrivateOutter.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPrivateExternal.class);
            startActivity(intent);
        });

        // 读写共享数据
        binding.btnPublic.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPublicData.class);
            startActivity(intent);
        });
    }
}
