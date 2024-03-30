package net.bi4vmr.study.viewstate;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiViewstateBinding;

public class TestUIViewState extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIViewState.class.getSimpleName();

    private TestuiViewstateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate.");
        binding = TestuiViewstateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "OnStart.");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "OnRestoreInstanceState.");

        // 从Bundle对象读取先前保存的数据
        boolean isChecked = savedInstanceState.getBoolean("STATE");
        Log.i(TAG, "已读取数据：" + isChecked);
        // 重新设置给控件
        binding.btnTest.setChecked(isChecked);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause.");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "OnSaveInstanceState.");

        // 从文本框中读取数据并保存至系统提供的Bundle对象
        boolean isChecked = binding.btnTest.isChecked();
        outState.putBoolean("STATE", isChecked);
        Log.i(TAG, "已写入数据：" + isChecked);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "OnStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "OnRestart.");
    }
}
