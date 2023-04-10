package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.bind.DemoBindUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定指定的服务
        Button btnBase = findViewById(R.id.btnBind);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBindUI.class);
            startActivity(intent);
        });
    }
}
