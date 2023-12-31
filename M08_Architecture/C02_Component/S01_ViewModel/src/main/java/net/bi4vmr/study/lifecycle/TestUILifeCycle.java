package net.bi4vmr.study.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

public class TestUILifeCycle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILifeCycle.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_lifecycle);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnRemove = findViewById(R.id.btnRemove);

        // 添加Fragment
        btnAdd.setOnClickListener(v -> {
            // 初始化Fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new TestFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // 移除Fragment
        btnRemove.setOnClickListener(v -> {
            getSupportFragmentManager().popBackStack();
        });

        // 获取当前Activity的MyViewModel2实例
        MyViewModel2 vm = new ViewModelProvider(this).get(MyViewModel2.class);
        Log.i(TAG, "Get VM in Activity. ID: " + vm.id);
    }
}
