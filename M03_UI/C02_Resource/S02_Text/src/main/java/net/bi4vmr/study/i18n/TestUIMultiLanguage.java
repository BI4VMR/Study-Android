package net.bi4vmr.study.i18n;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiMultilanguageBinding;

import java.lang.reflect.Method;
import java.util.Locale;

public class TestUIMultiLanguage extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIMultiLanguage.class.getSimpleName();

    private TestuiMultilanguageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiMultilanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetLocale.setOnClickListener(v -> testGetDefaultLocale());
        binding.btnGetUserLocales.setOnClickListener(v -> testGetLocaleList());
    }

    // 获取当前区域信息
    private void testGetDefaultLocale() {
        Log.i(TAG, "--- 获取当前区域信息 ---");
        binding.tvLog.append("\n--- 获取当前区域信息 ---\n");

        Locale locale = Locale.getDefault();
        // 获取语言名称
        String languageName = locale.getLanguage();
        // 获取地区名称
        String regionName = locale.getCountry();
        // 获取标准名称（“<语言>-<子类型>-<地区>”）
        String name = locale.toLanguageTag();
        // 获取显示名称
        String displayName = locale.getDisplayName();

        Log.i(TAG, "语言名称：[" + languageName + "]");
        Log.i(TAG, "地区名称：[" + regionName + "]");
        Log.i(TAG, "标准名称：[" + name + "]");
        Log.i(TAG, "显示名称:[" + displayName + "]");
        binding.tvLog.append("语言名称：[" + languageName + "]\n");
        binding.tvLog.append("地区名称：[" + regionName + "]\n");
        binding.tvLog.append("标准名称：[" + name + "]\n");
        binding.tvLog.append("显示名称:[" + displayName + "]\n");
    }

    // 获取用户选择的区域列表
    private void testGetLocaleList() {
        Log.i(TAG, "--- 获取用户选择的地区列表 ---");
        binding.tvLog.append("\n--- 获取用户选择的地区列表 ---\n");

        // 获取用户选择的地区列表
        // LocaleList locales = getResources().getConfiguration().getLocales();
        // // 遍历输出信息
        // for (int i = 0; i < locales.size(); i++) {
        //     Locale locale = locales.get(i);
        //     Log.i(TAG, "Locale:[" + locale.toLanguageTag() + "]");
        // }

        try {
            Class<?> c = Class.forName("com.android.internal.app.LocalePicker");
            Method method = c.getMethod("getAllAssetLocales", Context.class, boolean.class);
            Object o = method.invoke(null, this, false);
            Log.i("TESTapp", o.toString());
        } catch (Exception e) {
            Log.e("TESTapp", "e", e);
        }


    }
}
