package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.exceptions.TestUIExceptions;
import net.bi4vmr.study.exceptions.TestUIExceptionsKT;
import net.bi4vmr.study.file.TestUIFile;
import net.bi4vmr.study.file.TestUIFileKT;
import net.bi4vmr.study.paramsync.TestUIParamSync;
import net.bi4vmr.study.threads.TestUIThreads;
import net.bi4vmr.study.threads.TestUIThreadsKT;
import net.bi4vmr.study.types.TestUITypes;
import net.bi4vmr.study.types.TestUITypesKT;

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

        // 自定义数据类型
        binding.btnTypes.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITypes.class);
            startActivity(intent);
        });

        // 数据方向标签
        binding.btnParamSync.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIParamSync.class);
            startActivity(intent);
        });

        // 线程调度
        binding.btnThreads.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThreads.class);
            startActivity(intent);
        });

        // 异常处理
        binding.btnException.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIExceptions.class);
            startActivity(intent);
        });

        // 文件传输
        binding.btnFile.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFile.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 自定义数据类型(KT)
        binding.btnTypesKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITypesKT.class);
            startActivity(intent);
        });

        // 线程调度(KT)
        binding.btnThreadsKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIThreadsKT.class);
            startActivity(intent);
        });

        // 异常处理(KT)
        binding.btnExceptionKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIExceptionsKT.class);
            startActivity(intent);
        });

        // 文件传输(KT)
        binding.btnFileKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIFileKT.class);
            startActivity(intent);
        });
    }
}
