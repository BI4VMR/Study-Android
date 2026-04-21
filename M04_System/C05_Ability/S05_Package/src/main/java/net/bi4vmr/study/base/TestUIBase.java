package net.bi4vmr.study.base;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnGetPackageInfo.setOnClickListener(v -> test_GetPackageInfo());
        binding.btnGetAppInfo.setOnClickListener(v -> test_GetApplicationInfo());
        binding.btnGetAppList.setOnClickListener(v -> test_GetAllPackages());
    }

    private void test_GetPackageInfo() {
        Log.i(TAG, "----- 获取PackageInfo -----");
        appendLog("\n----- 获取PackageInfo -----");

        // 通过Context获取PackageManager实例
        PackageManager packageManager = this.getPackageManager();
        try {
            // ComponentName cn = new ComponentName(getPackageName(), "net.bi4vmr.study.MainActivity2");
            // ActivityInfo activityInfo = packageManager.getActivityInfo(cn, 0);
            // String t = activityInfo.targetActivity;
            // Log.i("TEA", "t: " + t);
            String target = getPackageName();
            PackageInfo info = packageManager.getPackageInfo(target, 0);

            // 包名
            String packageName = info.packageName;
            Log.i(TAG, "包名：" + packageName);
            appendLog("包名：" + packageName);

            // 数字版本号
            int versionCode = info.versionCode;
            Log.i(TAG, "数字版本号: " + versionCode);
            appendLog("数字版本号: " + versionCode);

            // 文本版本号
            String versionName = info.versionName;
            Log.i(TAG, "文本版本号: " + versionName);
            appendLog("文本版本号: " + versionName);

            // 首次安装时间
            long firstInstallTime = info.firstInstallTime;
            Log.i(TAG, "首次安装时间: " + firstInstallTime);
            appendLog("首次安装时间: " + firstInstallTime);

            // 最近更新时间
            long lastUpdateTime = info.lastUpdateTime;
            Log.i(TAG, "最近更新时间: " + lastUpdateTime);
            appendLog("最近更新时间: " + lastUpdateTime);

            // info.signingInfo.getApkContentsSigners();

            // 获取应用的所有权限信息
            String[] permissions = info.requestedPermissions;

            // 获取应用的Application信息
            ApplicationInfo applicationInfo = info.applicationInfo;
            // 获取应用的所有Activity信息
            ActivityInfo[] activities = info.activities;
            ActivityInfo[] receivers = info.receivers;
            // 获取应用的所有Service信息
            ServiceInfo[] services = info.services;
            // 获取应用的所有Provider信息
            ProviderInfo[] providers = info.providers;

        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            Log.e(TAG, "App not found! Info:[" + e.getMessage() + "]");
            appendLog("App not found! Info:[" + e.getMessage() + "]");
        }
    }

    private void test_GetApplicationInfo() {
        Log.i(TAG, "----- 获取ApplicationInfo -----");
        appendLog("\n----- 获取ApplicationInfo -----");

        // 通过Context获取PackageManager实例
        PackageManager packageManager = this.getPackageManager();
        try {
            String target = getPackageName();
            ApplicationInfo info = packageManager.getApplicationInfo(target, 0);
            Log.i(TAG, "包名：" + info.packageName);
            Log.i(TAG, "启用：" + info.enabled);
            Log.i(TAG, "uid：" + info.uid);
            Log.i(TAG, "sourceDir：" + info.sourceDir);
        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            Log.e(TAG, "App not found! Info:[" + e.getMessage() + "]");
            appendLog("App not found! Info:[" + e.getMessage() + "]");
        }
    }

    private void test_GetAllPackages() {
        Log.i(TAG, "----- 获取所有软件包列表 -----");
        appendLog("\n----- 获取所有软件包列表 -----");

        // 通过Context获取PackageManager实例
        PackageManager packageManager = this.getPackageManager();
        Log.i(TAG,"所有应用PackageInfo");
        packageManager.getInstalledPackages(0).forEach(info -> {
            System.out.println("app: " + info);
        });

        Log.i(TAG,"所有应用Applications");
        packageManager.getInstalledApplications(0).forEach(info -> {
            System.out.println("app: " + info);
        });
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        if (text == null) {
            Log.w(TAG, "Log item is NULL, ignored!");
            return;
        }

        TextView logArea = binding.tvLog;
        logArea.post(() -> logArea.append("\n" + text));
        logArea.post(() -> {
            try {
                int offset = logArea.getLayout().getLineTop(logArea.getLineCount()) - logArea.getHeight();
                if (offset > 0) {
                    logArea.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
