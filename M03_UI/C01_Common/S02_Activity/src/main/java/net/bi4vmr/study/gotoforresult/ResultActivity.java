package net.bi4vmr.study.gotoforresult;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            // 封装返回数据
            Intent intent = new Intent();
            intent.putExtra("RESULT", "RESULT");
            // 设置返回码和Intent对象
            setResult(999, intent);
            // 关闭当前Activity
            finish();
        });
    }
}
