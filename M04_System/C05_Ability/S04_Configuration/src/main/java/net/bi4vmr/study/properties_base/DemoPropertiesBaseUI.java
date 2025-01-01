package net.bi4vmr.study.properties_base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

import java.lang.reflect.Method;

public class DemoPropertiesBaseUI extends AppCompatActivity {

    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_properties_base);

        tvLog = findViewById(R.id.tvLog);
        tvLog.setText("日志输出区域：\n");
        Button btnGet = findViewById(R.id.btnGet);
        Button btnSetD = findViewById(R.id.btnSetD);
        Button btnSetI = findViewById(R.id.btnSetI);

        btnGet.setOnClickListener(v -> getLogLevel());
        btnSetD.setOnClickListener(v -> setLogD());
        btnSetI.setOnClickListener(v -> setLogI());
    }

    // 读取Logcat日志输出级别
    private void getLogLevel() {
        String level = getProperty("persist.log.tag", "D");
        Log.i("myapp", "当前日志级别：" + level);
        tvLog.append("当前日志级别：" + level + "\n");
    }

    // 设置Logcat日志输出级别为Debug
    private void setLogD() {
        setProperty("persist.log.tag", "D");
        Log.i("myapp", "日志级别已设为Debug。");
        tvLog.append("日志级别已设为Debug。\n");
    }

    // 设置Logcat日志输出级别为Info
    private void setLogI() {
        setProperty("persist.log.tag", "I");
        Log.i("myapp", "日志级别已设为Info。");
        tvLog.append("日志级别已设为Info。\n");
    }

    /**
     * Name        : 获取配置项的值
     * <p>
     * Description : 通过反射调用SDK中的隐藏方法"SystemProperties#get"读取配置项。
     * "get()"方法的第一参数为配置项名称，第二参数为默认值，配置项不存在或值为空字符串时，将返回默认值。
     *
     * @param key      配置项名称
     * @param defValue 默认值
     * @return 配置项的值，若配置项不存在或值为空字符串，则返回默认值。若反射操作失败，则返回空值。
     */
    private String getProperty(String key, String defValue) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("get", String.class, String.class);
            return (String) method.invoke(cls, key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Name        : 修改配置项的值
     * <p>
     * Description : 通过反射调用SDK中的隐藏方法"SystemProperties#set"读取配置项。
     * "set()"方法的第一参数为配置项名称，第二参数为新值，需要配置`android:sharedUserId="android.uid.system"`
     * 并使用系统签名打包才能调用。
     *
     * @param key   配置项名称
     * @param value 新值
     * @return 操作成功时返回"true"；操作失败时返回"false"。
     */
    private boolean setProperty(String key, String value) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("set", String.class, String.class);
            method.invoke(cls, key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
