package net.bi4vmr.study.settings_base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoSettingsBaseUI extends AppCompatActivity {

    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_settings_base);

        tvLog = findViewById(R.id.tvLog);
        tvLog.setText("日志输出区域：\n");
        Button btnCheck = findViewById(R.id.btnCheck);
        Button btnGetSystem = findViewById(R.id.btnGetSystem);
        Button btnSetSystem = findViewById(R.id.btnSetSystem);
        Button btnGetSecure = findViewById(R.id.btnGetSecure);
        Button btnSetSecure = findViewById(R.id.btnSetSecure);

        btnCheck.setOnClickListener(v -> testCanWriteSystem());
        btnGetSystem.setOnClickListener(v -> getSystemItem());
        btnSetSystem.setOnClickListener(v -> setSystemItem());
        btnGetSecure.setOnClickListener(v -> getSecureItem());
        btnSetSecure.setOnClickListener(v -> setSecureItem());
    }

    // 测试当前应用是否有权限修改System
    private void testCanWriteSystem() {
        boolean canWriteSystem = Settings.System.canWrite(getApplicationContext());
        tvLog.append("当前应用是否可修改System区域:" + canWriteSystem + "\n");
        Log.i("myapp", "当前应用是否可修改System区域:" + canWriteSystem);

        // 若无权限，则跳转至系统界面引导用户开启。
        if (!canWriteSystem) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    // 读取System区域中的配置项
    private void getSystemItem() {
        // 读取System区域中的屏幕亮度配置项
        String value = Settings.System.getString(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        tvLog.append("读取屏幕亮度数值：" + value + "\n");
        Log.i("myapp", "读取屏幕亮度数值：" + value);
    }

    // 修改System区域中的配置项
    private void setSystemItem() {
        // 修改System区域中的屏幕亮度配置项
        boolean result = Settings.System.putString(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, "128");
        tvLog.append("修改屏幕亮度数值为128, 操作结果：" + result + "\n");
        Log.i("myapp", "修改屏幕亮度数值为128, 操作结果：" + result);
    }

    // 读取Secure区域中的配置项
    private void getSecureItem() {
        // 读取Secure区域中的自定义配置项
        int value = Settings.Secure.getInt(getContentResolver(), "key_test", -1);
        tvLog.append("读取自定义配置项：" + value + "\n");
        Log.i("myapp", "读取自定义配置项：" + value);
    }

    // 修改Secure区域中的配置项
    private void setSecureItem() {
        int currentValue = Settings.Secure.getInt(getContentResolver(), "key_test", -1);
        int newValue = currentValue + 1;
        // 修改Secure区域中的自定义配置项
        boolean result = Settings.Secure.putInt(getContentResolver(), "key_test", newValue);
        tvLog.append("修改自定义配置项的数值为：" + newValue + "，操作结果：" + result + "\n");
        Log.i("myapp", "修改自定义配置项的数值为：" + newValue + "，操作结果：" + result);
    }
}
