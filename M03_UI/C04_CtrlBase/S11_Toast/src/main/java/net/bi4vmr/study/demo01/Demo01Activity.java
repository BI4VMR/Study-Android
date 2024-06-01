package net.bi4vmr.study.demo01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.toast.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 显示Toast按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> {
            /*
             * Name        : Toast.makeText()
             * Description : 构造Toast实例。
             *
             * @param context  上下文环境
             * @param text     文本内容，可以是字符串或资源ID。
             * @param duration 持续时间
             */
            Toast.makeText(getApplicationContext(), "Text", Toast.LENGTH_SHORT)
                    // 显示Toast
                    .show();
        });
    }
}
