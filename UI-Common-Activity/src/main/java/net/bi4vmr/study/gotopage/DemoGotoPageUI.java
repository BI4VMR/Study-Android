package net.bi4vmr.study.gotopage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoGotoPageUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_gotopage);

        // 启动应用内的Activity
        Button btnStartInner = findViewById(R.id.btnStartInner);
        btnStartInner.setOnClickListener(v -> {
            // 创建Intent对象
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            // 传递初始化参数（可选功能）
            intent.putExtra("PARAM_INIT", "测试文本");
            // 启动TestActivity
            startActivity(intent);
        });

        // 启动应用外的Activity
        Button btnStartOuter = findViewById(R.id.btnStartOuter);
        btnStartOuter.setOnClickListener(v -> {
            /*
             * 创建ComponentName对象，用于描述目标界面。
             *
             * ComponentName的构造方法中，第一参数为包名，第二参数为组件名称。
             */
            ComponentName cmpName = new ComponentName("com.android.settings", "com.android.settings.Settings");
            // 创建Intent对象
            Intent intent = new Intent();
            intent.setComponent(cmpName);
            // 外部组件可能并不存在，例如目标APP未安装、系统组件被ROM精简等情况，所以需要使用"try-catch"语句。
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // 目标应用程序未安装、目标组件没有在其Manifest注册都会造成此错误。
                Toast.makeText(this, "未找到目标组件", Toast.LENGTH_SHORT).show();
                Log.w("myapp", "未找到目标组件");
                e.printStackTrace();
            }
        });

        // 打开一个网页链接
        Button btGoToWeb = findViewById(R.id.btnGoToWeb);
        // 设置按钮控件的点击事件
        btGoToWeb.setOnClickListener(v -> {
            // 构造URI，指定目标URL地址。
            Uri uri = Uri.parse("https://cn.bing.com/");
            // 创建Intent对象
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // 启动默认浏览器
            startActivity(intent);
        });
    }
}
