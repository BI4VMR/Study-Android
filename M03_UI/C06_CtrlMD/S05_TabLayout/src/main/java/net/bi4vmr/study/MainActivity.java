package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.splitline.DemoSplitLineUI;
import net.bi4vmr.study.tooltip.DemoToolTipUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBaseUI.class);
            startActivity(intent);
        });

        // 添加分隔线
        Button btnSplitLine = findViewById(R.id.btnSplitLine);
        btnSplitLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoSplitLineUI.class);
            startActivity(intent);
        });

        // 移除长按Tab时的工具提示
        Button btnToolTip = findViewById(R.id.btnToolTip);
        btnToolTip.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoToolTipUI.class);
            startActivity(intent);
        });
    }
}
