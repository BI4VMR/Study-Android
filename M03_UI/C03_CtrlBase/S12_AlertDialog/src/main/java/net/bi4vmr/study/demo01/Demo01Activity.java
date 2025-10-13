package net.bi4vmr.study.demo01;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.alertdialog.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 创建对话框构造器并进行配置
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                // 设置标题
                .setTitle("警告对话框")
                // 设置图标
                .setIcon(R.drawable.ic_funny_256)
                // 设置内容
                .setMessage("此处为消息内容")
                // 设置确认按钮
                .setPositiveButton("确认", (dialog, which) -> {
                    //按下确认按钮后的操作
                    Log.i("Test", "Positive");
                })
                // 设置取消按钮
                .setNegativeButton("取消", (dialog, which) -> Log.i("Test", "Negative"))
                // 设置中立按钮
                .setNeutralButton("中立", (dialog, which) -> Log.i("Test", "Neutral"));
        // 创建对话框
        AlertDialog dialog = builder.create();

        // 显示弹窗按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> {
            // 显示对话框
            dialog.show();
        });
    }
}
