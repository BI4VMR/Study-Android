package net.bi4vmr.study.demo03;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.alertdialog.R;

public class Demo03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);

        // 表项数据
        final String[] res = {"语文", "数学", "英语", "物理", "化学"};
        // 选项状态标记
        final boolean[] status = {false, true, false, true, false};

        // 配置单选对话框
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
                .setTitle("单选对话框")
                // 设置单选组件
                .setSingleChoiceItems(res, 1, (dialog, which) ->
                        // 用户每次点击选项都会触发此回调
                        Log.i("Test", "你选中了：" + res[which])
                )
                // 设置确认按钮
                .setPositiveButton("确认", (dialog, which) -> {
                    // 按下确认按钮时触发此回调，可以从全局变量读取最终选择的项。
                });
        AlertDialog dialog1 = builder1.create();

        // 配置多选对话框
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
                .setTitle("多选对话框")
                // 设置多选组件
                .setMultiChoiceItems(res, status, (dialog, which, isChecked) -> {
                    // 选项状态变更时，修改状态标记。
                    status[which] = isChecked;
                })
                // 设置确认按钮
                .setPositiveButton("确认", (dialog, which) -> {
                    // 按下确认按钮时触发此回调，可以从全局变量读取最终选择的项。
                });
        AlertDialog dialog2 = builder2.create();

        // 显示单选弹窗按钮
        Button btShow1 = findViewById(R.id.btShow1);
        btShow1.setOnClickListener(v -> dialog1.show());

        // 显示多选弹窗按钮
        Button btShow2 = findViewById(R.id.btShow2);
        btShow2.setOnClickListener(v -> dialog2.show());
    }
}