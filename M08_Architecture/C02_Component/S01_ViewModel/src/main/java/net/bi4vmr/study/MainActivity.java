package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.androidvm.TestUIAndroidVM;
import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.lifecycle.TestUILifeCycle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // 生命周期
        Button btnLifeCycle = findViewById(R.id.btnLifeCycle);
        btnLifeCycle.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILifeCycle.class);
            startActivity(intent);
        });

        // AndroidViewModel
        Button btnAndroidVM = findViewById(R.id.btnAndroidVM);
        btnAndroidVM.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAndroidVM.class);
            startActivity(intent);
        });
    }
}
