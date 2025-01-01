package net.bi4vmr.study.demo02;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.alertdialog.R;

public class Demo02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        // 表项数据
        final String[] res = {"语文", "数学", "英语", "物理", "化学"};
        // 配置对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("列表对话框")
                // 设置列表对话框
                .setItems(res, (dialog, which) -> {
                    // "which"参数表示选中的列表项索引
                    Log.i("Test", "你选择了：" + res[which]);
                });
        AlertDialog dialog = builder.create();

        // 显示弹窗按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> dialog.show());
    }
}