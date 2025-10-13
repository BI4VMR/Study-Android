package net.bi4vmr.study.gotoforresult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoGotoForResultUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_startforresult);

        // 启动页面并等待结果
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            // 启动页面，并设置请求码。
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("myapp", "OnActivityResult. RequestCode:" + requestCode + " ,ResultCode:" + resultCode);
        // 判断为何种请求
        if (requestCode == 100) {
            // 判断为何种结果
            if (resultCode == 999) {
                // 获取结果
                if (data != null) {
                    String s = data.getStringExtra("RESULT");
                    Log.i("myapp", "OnActivityResult. Request 100; Result 999; Data:" + s);
                }
            }
        }
    }
}
