package net.bi4vmr.study.demo04;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.alertdialog.R;

public class Demo04Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_04);

        // 配置对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("自定义对话框");
        AlertDialog dialog = builder.create();

        // 创建图片展示框
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_funny_256);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bmp);

        // 创建按钮
        Button button = new Button(this);
        button.setText("关闭");
        button.setOnClickListener(v -> dialog.dismiss());

        // 创建布局管理器并添加上述控件
        LinearLayout layout = new LinearLayout(this);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imageView);
        layout.addView(button);

        // 将自定义View设置到对话框上
        dialog.setView(layout);

        // 显示弹窗按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> dialog.show());
    }
}