package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.transaction.TestUITransaction;
import net.bi4vmr.study.transaction.TestUITransactionKT;
import net.bi4vmr.study.upgrade.TestUIUpgrade;
import net.bi4vmr.study.upgrade.TestUIUpgradeKT;

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

        // 事务支持
        binding.btnTransaction.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITransaction.class);
            startActivity(intent);
        });

        // 数据库版本升级
        binding.btnUpgrade.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUpgrade.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 事务支持(KT)
        binding.btnTransactionKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITransactionKT.class);
            startActivity(intent);
        });

        // 数据库版本升级(KT)
        binding.btnUpgradeKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUpgradeKT.class);
            startActivity(intent);
        });
    }
}
