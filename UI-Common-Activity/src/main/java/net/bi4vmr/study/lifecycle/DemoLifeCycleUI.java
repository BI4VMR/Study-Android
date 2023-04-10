package net.bi4vmr.study.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.gotopage.TestActivity;

public class DemoLifeCycleUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myapp", "OnCreate.");
        setContentView(R.layout.ui_demo_lifecycle);

        // 打开新的Activity
        Button btnStartInner = findViewById(R.id.btnGoToNewPage);
        btnStartInner.setOnClickListener(v -> {
            // 创建Intent对象
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            // 启动SecondActivity
            startActivity(intent);
        });

        // 关闭当前Activity
        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            Log.i("myapp", "Finish function called.");
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("myapp", "OnStart.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("myapp", "OnResume.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("myapp", "OnPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("myapp", "OnStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "OnDestroy.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("myapp", "OnRestart.");
    }
}
