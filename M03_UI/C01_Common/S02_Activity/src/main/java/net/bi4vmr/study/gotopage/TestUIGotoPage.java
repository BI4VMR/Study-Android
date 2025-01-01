package net.bi4vmr.study.gotopage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiGotopageBinding;

public class TestUIGotoPage extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIGotoPage.class.getSimpleName();

    private TestuiGotopageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiGotopageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGoToInner.setOnClickListener(v -> testGoToInnerActivity());
        binding.btnGoToOuter.setOnClickListener(v -> testGoToOuterActivity());
        binding.btnGoToWeb.setOnClickListener(v -> testGoToWeb());
    }

    // 启动应用内的Activity
    private void testGoToInnerActivity() {
        Log.i(TAG, "--- 启动应用内的Activity ---");
        binding.tvLog.append("\n--- 启动应用内的Activity ---\n");

        // 创建Intent实例
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        // 传递初始化参数（可选功能）
        intent.putExtra("PARAM_INIT", "测试文本");
        // 启动TestActivity
        startActivity(intent);
    }

    // 启动应用外的Activity
    private void testGoToOuterActivity() {
        Log.i(TAG, "--- 启动应用外的Activity ---");
        binding.tvLog.append("\n--- 启动应用外的Activity ---\n");

        /*
         * 创建ComponentName实例，用于描述目标界面。
         *
         * ComponentName的构造方法中，第一参数为包名，第二参数为组件名称。
         */
        ComponentName cmpName = new ComponentName("com.android.settings", "com.android.settings.Settings");
        // 创建Intent实例
        Intent intent = new Intent();
        intent.setComponent(cmpName);

        // 外部组件可能并不存在，例如目标APP未安装、系统组件被ROM精简等情况，所以需要使用"try-catch"语句。
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // 目标应用程序未安装、目标组件没有在其Manifest注册都会造成此错误。
            Log.w(TAG, "未找到目标组件！");
            binding.tvLog.append("未找到目标组件！\n");
            Toast.makeText(this, "未找到目标组件", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // 打开一个网页链接
    private void testGoToWeb() {
        Log.i(TAG, "--- 打开一个网页链接 ---");
        binding.tvLog.append("\n--- 打开一个网页链接 ---\n");

        // 构造URI，指定目标URL地址。
        Uri uri = Uri.parse("https://cn.bing.com/");
        // 创建Intent对象
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        try {
            // 启动默认浏览器
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // 目标应用程序未安装、目标组件没有在其Manifest注册都会造成此错误。
            Log.w(TAG, "未找到目标组件！");
            binding.tvLog.append("未找到目标组件！\n");
            Toast.makeText(this, "未找到目标组件", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
