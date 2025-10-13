package net.bi4vmr.study.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        Button btStart = findViewById(R.id.btStart);
        Button btStop = findViewById(R.id.btStop);

        Intent intent = new Intent(this, ForegroundService.class);
        // 启动服务按钮
        btStart.setOnClickListener(v -> startService(intent));
        // 停止服务按钮
        btStop.setOnClickListener(v -> stopService(intent));
    }
}
