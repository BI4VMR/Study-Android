package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 创建MyDialog实例
        MyDialog dialog = MyDialog.newInstance();
        // 注册弹窗关闭监听器
        dialog.setDismissListener(() -> Log.i("myapp", "Dialog is closed."));

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> {
            // 显示MyDialog
            dialog.show(getSupportFragmentManager(), null);
        });
    }
}
