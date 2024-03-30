package net.bi4vmr.study.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLifecycleBinding;
import net.bi4vmr.study.gotopage.TestActivity;

public class TestUILifeCycle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILifeCycle.class.getSimpleName();

    private TestuiLifecycleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate.");
        binding = TestuiLifecycleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 打开新的Activity
        binding.btnGoToNewPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        });

        // 关闭当前Activity
        binding.btnClose.setOnClickListener(v -> {
            Log.i(TAG, "Finish function called.");
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "OnStart.");
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
