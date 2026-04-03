package net.bi4vmr.study.layout;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

/**
 * 测试界面：布局文件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILayout extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILayout.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 自动加载布局
        setContentView(R.layout.testui_layout);


        // 设置手动加载布局示例界面的跳转事件
        findViewById(R.id.btn_layout_inflater).setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(this, TestUILayoutInflater.class);
                    startActivity(intent);
                }
        );
    }
}
