package net.bi4vmr.study.style;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoStyleUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_style);

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> {
            // 获取MyDialog实例
            MyDialog dialog = MyDialog.newInstance();
            // 显示MyDialog
            dialog.show(getSupportFragmentManager(), null);
        });
    }
}
