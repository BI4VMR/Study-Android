package net.bi4vmr.study.settings_observe;

import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoSettingsObserveUI extends AppCompatActivity {

    private final ContentObserver settingObserver = new SettingObserver(null);

    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_settings_observe);

        tvLog = findViewById(R.id.tvLog);
        tvLog.setText("日志输出区域：\n");
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnUnregister = findViewById(R.id.btnUnregister);

        btnRegister.setOnClickListener(v -> registerObserver());
        btnUnregister.setOnClickListener(v -> unregisterObserver());
    }

    // 注册监听器
    private void registerObserver() {
        getContentResolver().registerContentObserver(
                Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS),
                false,
                settingObserver
        );
    }

    // 注销监听器
    private void unregisterObserver() {
        getContentResolver().unregisterContentObserver(settingObserver);
    }

    /* 监听配置项的变化 */
    private class SettingObserver extends ContentObserver {

        public SettingObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            String value = Settings.System.getString(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            Log.i("myapp", "观察到屏幕亮度变化：" + value + "\n");
            runOnUiThread(() -> tvLog.append("观察到屏幕亮度变化：" + value));
        }
    }
}
