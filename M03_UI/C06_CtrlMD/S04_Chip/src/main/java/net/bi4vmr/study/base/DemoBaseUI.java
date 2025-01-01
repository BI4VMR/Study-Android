package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        Chip chipAction = findViewById(R.id.chipAction);
        Chip chipFilter = findViewById(R.id.chipFilter);
        Chip chipEntry = findViewById(R.id.chipEntry);

        // 点击事件监听器
        chipAction.setOnClickListener(v -> {
            // 打印日志
            Log.i("myapp", "Chip被点击。");
        });

        // 选中事件监听器
        chipFilter.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 打印日志
            Log.i("myapp", "Chip的选中状态发生改变，新状态：" + isChecked);
        });

        chipEntry.setOnCloseIconClickListener(v -> {
            // 打印日志
            Log.i("myapp", "Chip的关闭按钮被点击。");
        });
    }
}
