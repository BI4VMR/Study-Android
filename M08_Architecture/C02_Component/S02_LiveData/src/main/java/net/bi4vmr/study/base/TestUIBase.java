package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_base);

        // 获取当前Activity的MyViewModel实例
        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnRemove = findViewById(R.id.btnRemove);
        TextView tvContent = findViewById(R.id.tvContent);

        // 初始化Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new TestFragment())
                .addToBackStack(null)
                .commit();

        btnPlus.setOnClickListener(v -> vm.plus());
        btnMinus.setOnClickListener(v -> vm.minus());
        btnRemove.setOnClickListener(v -> getSupportFragmentManager().popBackStack());

        // 读取LiveData的初始值
        Log.i(TAG, "LiveData初始值：" + vm.roNumberData.getValue());
        // 调用LiveData的"observe()"方法，注册本Activity为该LiveData的观察者。
        vm.roNumberData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i(TAG, "LiveData数值改变：" + integer);
                // 观察到数值改变时，将其更新到控件上。
                tvContent.setText("Num:" + integer);
            }
        });
    }
}
