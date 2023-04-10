package net.bi4vmr.study.ui.extctrl.viewpager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button bt1 = findViewById(R.id.bt01);
        bt1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo01Activity.class);
            startActivity(intent);
        });

        // ViewPager2嵌套使用
        Button bt2 = findViewById(R.id.bt02);
        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });
    }
}