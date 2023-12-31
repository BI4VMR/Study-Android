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
        setContentView(R.layout.ui_demo_base);

        Button btn = findViewById(R.id.btnStart);
        btn.setOnClickListener(v -> {
            // 新建Bundle对象，并存入一些数据。
            Bundle bundle = new Bundle();
            bundle.putString("KEY_ID", "0001");
            bundle.putString("KEY_NAME", "书籍");
            bundle.putFloat("KEY_PRICE", 39.9F);
            bundle.putBoolean("KEY_SOLDOUT", false);

            Intent intent = new Intent();
            intent.setClass(this, DstActivity.class);
            // 将数据存入Intent，然后启动目标Activity。
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
