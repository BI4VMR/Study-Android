package net.bi4vmr.study.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 文本框
        TextView textView = findViewById(R.id.tvInfo);
        textView.setText("等待操作...");

        // 启动按钮
        Button btStart = findViewById(R.id.btStart);
        btStart.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            i.putExtra("LINK", "https://dl.test.com/file");
            startService(i);
            textView.setText("服务已启动");
        });

        // 停止按钮
        Button btStop = findViewById(R.id.btStop);
        btStop.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            boolean isSuccess = stopService(i);
            if (isSuccess) {
                textView.setText("服务已停止");
            } else {
                textView.setText("终止服务失败");
            }
        });
    }
}
