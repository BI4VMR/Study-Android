package net.bi4vmr.study.i18n;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiMultilanguageBinding;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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

        // 最好不要直接使用Locale.getDefault()，因为它可能不反映用户在系统设置中选择的语言和地区。相反，应该通过Context获取当前的Locale。
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

    // 获取系统当前的语言设置
    // settings get system system_locales
    private void testGetAppLocaleList() {
        // 旧版
        Locale locale = getResources().getConfiguration().locale;

        // Android 7.0 (API 24) 引入了语言列表概念。
        LocaleList locales = getResources().getConfiguration().getLocales();
        // 如果只关心主要语言，可以获取列表中的第一个元素
        getResources().getConfiguration().getLocales().get(0);
        // return locale.getDisplayLanguage();
    }

    // 获取系统支持的语言
    private void testGetavailableLocaleList() {
        List<String> list = new ArrayList<>();
        String[] lg = Resources.getSystem().getAssets().getLocales();
        for (String language : lg) {
            // String name = language.getDisplayLanguage();
            // 去掉重复的语言
            // if (!list.contains(name)){
            // list.add(name);
            // }
            Log.i("TestApp", "locale: " + language);
        }
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

        // try {
        //     Class<?> c = Class.forName("com.android.internal.app.LocalePicker");
        //     Method method = c.getMethod("getAllAssetLocales", Context.class, boolean.class);
        //     Object o = method.invoke(null, this, false);
        //     Log.i("TESTapp", o.toString());
        // } catch (Exception e) {
        //     Log.e("TESTapp", "e", e);
        // }


    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleList l = newConfig.getLocales();
        Log.i("TestApp", "onConfigurationChanged. list: " + l);
        for (int i = 0; i < l.size(); i++) {
            Log.i("TestApp", "onConfigurationChanged. i: " + i + ", c: " + l.get(i).getLanguage());
        }
    }

}
