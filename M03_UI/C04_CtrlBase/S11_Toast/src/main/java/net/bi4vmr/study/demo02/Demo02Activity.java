package net.bi4vmr.study.demo02;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.toast.R;

public class Demo02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        Button btShow = findViewById(R.id.btShow);

        /*
         * 此示例不支持Android 11及以上版本系统。
         */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            btShow.setOnClickListener(v -> {
                Toast.makeText(this, "Android 10以上版本不支持自定义Toast", Toast.LENGTH_LONG)
                        .show();
            });
            return;
        }

        // 显示Toast按钮
        btShow.setOnClickListener(v -> {
            // 创建Toast实例
            Toast toast = Toast.makeText(getApplicationContext(), "My Toast", Toast.LENGTH_LONG);
            // 设置Toast相对于屏幕边缘的位置与偏移量
            toast.setGravity(Gravity.TOP, 0, 275);
            // 获取Toast的布局实例
            LinearLayout layout = (LinearLayout) toast.getView();

            // 创建ImageView实例
            ImageView iv = new ImageView(getApplicationContext());
            iv.setImageResource(R.drawable.ic_funny_256);

            // 将ImageView添加到Toast布局中
            layout.addView(iv);
            // 显示Toast
            toast.show();
        });
    }
}
