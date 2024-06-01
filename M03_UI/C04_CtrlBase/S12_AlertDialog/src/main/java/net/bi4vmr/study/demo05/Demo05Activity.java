package net.bi4vmr.study.demo05;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.alertdialog.R;

public class Demo05Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);

        // 创建对话框并进行配置
        AlertDialog dialog = new AlertDialog.Builder(this, R.style.DialogCustom)
                // 设置标题
                .setTitle("警告对话框")
                // 设置内容
                .setMessage("此处为消息内容")
                // 创建对话框
                .create();

        /* 调整对话框的尺寸、位置等 */
        // 获取Window对象
        Window window = dialog.getWindow();
        // 更改内边距
        window.getDecorView().setPadding(10, 10, 10, 10);
        // 获取布局参数
        WindowManager.LayoutParams layoutParam = window.getAttributes();
        // 设置尺寸与位置
        layoutParam.width = 600;
        layoutParam.height = 300;
        // 设置窗口与父布局的对齐方式
        layoutParam.gravity = Gravity.TOP | Gravity.START;
        // 设置窗口在"gravity"所设置的对齐方向上的偏移量
        layoutParam.x = 100;
        layoutParam.y = 150;
        // 应用布局参数
        window.setAttributes(layoutParam);

        // 显示弹窗按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> {
            // 显示对话框
            dialog.show();
        });
    }
}