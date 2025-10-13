package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.config.DemoConfigUI;
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
        setContentView(R.layout.activity_main);

        // 启动新的Activity
        Button btnSwitchPage = findViewById(R.id.btnSwitchPage);
        btnSwitchPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGotoPageUI.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（旧方法）
        Button btnGotoForResult = findViewById(R.id.btnGotoForResult);
        btnGotoForResult.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGotoForResultUI.class);
            startActivity(intent);
        });

        // 启动Activity并等待返回结果（新方法）
        Button btnGotoForResult2 = findViewById(R.id.btnGotoForResult2);
        btnGotoForResult2.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGotoForResult2UI.class);
            startActivity(intent);
        });

        // 生命周期
        Button btnLifeCycle = findViewById(R.id.btnLifeCycle);
        btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoLifeCycleUI.class);
            startActivity(intent);
        });

        // 视图数据保持
        Button btnViewState = findViewById(R.id.btnViewState);
        btnViewState.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoViewStateUI.class);
            startActivity(intent);
        });

        // 处理配置变更
        Button btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoConfigUI.class);
            startActivity(intent);
        });

        // 启动模式
        Button btnLaunchMode = findViewById(R.id.btnLaunchMode);
        btnLaunchMode.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoLaunchModeUI.class);
            startActivity(intent);
        });
    }
}
