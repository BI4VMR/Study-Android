package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.config.DemoConfigUI;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.gotoforresult.DemoGotoForResultUI;
import net.bi4vmr.study.gotoforresult2.DemoGotoForResult2UI;
import net.bi4vmr.study.gotopage.DemoGotoPageUI;
import net.bi4vmr.study.launchmode.DemoLaunchModeUI;
import net.bi4vmr.study.lifecycle.DemoLifeCycleUI;
import net.bi4vmr.study.viewstate.DemoViewStateUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 启动新的Activity
        binding.btnSwitchPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGotoPageUI.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（旧方法）
        binding.btnGotoForResult.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGotoForResultUI.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（新方法）
        binding.btnGotoForResult2.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGotoForResult2UI.class);
            startActivity(intent);
        });

        // 生命周期
        binding.btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoLifeCycleUI.class);
            startActivity(intent);
        });

        // 视图数据保持
        binding.btnViewState.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoViewStateUI.class);
            startActivity(intent);
        });

        // 处理配置变更
        binding.btnConfig.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoConfigUI.class);
            startActivity(intent);
        });

        // 启动模式
        binding.btnLaunchMode.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoLaunchModeUI.class);
            startActivity(intent);
        });
    }
}
