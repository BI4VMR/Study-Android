package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.config.TestUIConfig;
import net.bi4vmr.study.config.TestUIConfigKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.gotoforresult.TestUIGotoForResult;
import net.bi4vmr.study.gotoforresult.TestUIGotoForResultKT;
import net.bi4vmr.study.gotoforresult2.TestUIGotoForResult2;
import net.bi4vmr.study.gotoforresult2.TestUIGotoForResult2KT;
import net.bi4vmr.study.gotopage.TestUIGotoPage;
import net.bi4vmr.study.gotopage.TestUIGotoPageKT;
import net.bi4vmr.study.launchmode.TestUILaunchMode;
import net.bi4vmr.study.lifecycle.TestUILifeCycle;
import net.bi4vmr.study.lifecycle.TestUILifeCycleKT;
import net.bi4vmr.study.viewstate.TestUIViewState;
import net.bi4vmr.study.viewstate.TestUIViewStateKT;

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

        // 启动新的Activity
        binding.btnSwitchPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIGotoPage.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（旧）
        binding.btnGotoForResult.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIGotoForResult.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（新）
        binding.btnGotoForResult2.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIGotoForResult2.class);
            startActivity(intent);
        });

        // 生命周期
        binding.btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycle.class);
            startActivity(intent);
        });

        // 视图数据保持
        binding.btnViewState.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIViewState.class);
            startActivity(intent);
        });

        // 处理配置变更
        binding.btnConfig.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIConfig.class);
            startActivity(intent);
        });

        // 启动模式
        binding.btnLaunchMode.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILaunchMode.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 启动新的Activity(KT)
        binding.btnSwitchPageKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIGotoPageKT.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（旧）(KT)
        binding.btnGotoForResultKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIGotoForResultKT.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（新）(KT)
        binding.btnGotoForResult2KT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIGotoForResult2KT.class);
            startActivity(intent);
        });

        // 生命周期(KT)
        binding.btnLifeCycleKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycleKT.class);
            startActivity(intent);
        });

        // 视图数据保持(KT)
        binding.btnViewStateKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIViewStateKT.class);
            startActivity(intent);
        });

        // 处理配置变更(KT)
        binding.btnConfigKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIConfigKT.class);
            startActivity(intent);
        });
    }
}
