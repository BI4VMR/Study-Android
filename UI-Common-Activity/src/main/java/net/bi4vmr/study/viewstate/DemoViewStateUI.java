package net.bi4vmr.study.viewstate;

import android.os.Bundle;
import android.util.Log;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoViewStateUI extends AppCompatActivity {

    private ToggleButton btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myapp", "OnCreate.");
        setContentView(R.layout.ui_demo_viewstate);

        btnTest = findViewById(R.id.btnTest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("myapp", "OnStart.");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("myapp", "OnRestoreInstanceState.");
        // 从Bundle对象读取先前保存的数据
        boolean isChecked = savedInstanceState.getBoolean("STATE");
        Log.i("myapp", "已读取数据：" + isChecked);
        // 重新设置给控件
        btnTest.setChecked(isChecked);
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("myapp", "OnSaveInstanceState.");
        // 从文本框中读取数据并保存至系统提供的Bundle对象
        boolean isChecked = btnTest.isChecked();
        outState.putBoolean("STATE", isChecked);
        Log.i("myapp", "已写入数据：" + isChecked);
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
