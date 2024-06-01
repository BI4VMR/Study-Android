package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.demo01.Demo01Activity;
import net.bi4vmr.study.demo02.Demo02Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn01 = findViewById(R.id.btn01);
        btn01.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo01Activity.class);
            startActivity(intent);
        });

        // 外观定制
        Button btn02 = findViewById(R.id.btn02);
        btn02.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });
    }
}
