package net.bi4vmr.study.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.Arrays;
import java.util.List;

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
        binding.btnGetActivityInfo.setOnClickListener(v -> test_GetActivityInfo());
        binding.btnGetAllPackages.setOnClickListener(v -> test_GetAllPackages());
        binding.btnQueryServiceInfo.setOnClickListener(v -> test_QueryServiceInfo());
        binding.btnResolveAPK.setOnClickListener(v -> test_ResolveAPK());
    }

    private void test_GetPackageInfo() {
        Log.i(TAG, "----- 获取软件包信息 -----");
        appendLog("\n----- 获取软件包信息 -----");

        // 通过Context获取PackageManager实例
        PackageManager packageManager = this.getPackageManager();
        try {
            // 使用当前软件包作为目标
            String target = getPackageName();

            /*
             * 获取该软件包的PackageInfo实例。
             *
             * 为了提高执行速度，默认情况下该方法只会返回基本信息，若要获取额外信息，需要通过第二参数 `flags` 指明。
             */
            PackageInfo info = packageManager.getPackageInfo(target, 0);

            /*
             * 自从Android 13 (API 33)开始，我们应当使用PackageInfoFlags表示标志位。
             *
             * PackageInfoFlags的静态方法 `of(int value)` 可以将数值转为PackageInfoFlags对象。
             */
            // PackageInfo info = packageManager.getPackageInfo(target, PackageManager.PackageInfoFlags.of(0L));

            // 包名
            String packageName = info.packageName;
            Log.i(TAG, "包名：[" + packageName + "]");
            appendLog("包名：[" + packageName + "]");

            // 数字版本号
            int versionCode = info.versionCode;
            Log.i(TAG, "数字版本号：[" + versionCode + "]");
            appendLog("数字版本号：[" + versionCode + "]");

            // 文本版本号
            String versionName = info.versionName;
            Log.i(TAG, "文本版本号：[" + versionName + "]");
            appendLog("文本版本号：[" + versionName + "]");

            // 首次安装时间
            long firstInstallTime = info.firstInstallTime;
            Log.i(TAG, "首次安装时间：[" + firstInstallTime + "]");
            appendLog("首次安装时间：[" + firstInstallTime + "]");

            // 最近更新时间
            long lastUpdateTime = info.lastUpdateTime;
            Log.i(TAG, "最近更新时间：[" + lastUpdateTime + "]");
            appendLog("最近更新时间：[" + lastUpdateTime + "]");

            // Application信息
            ApplicationInfo applicationInfo = info.applicationInfo;
            Log.i(TAG, "Application信息：[" + applicationInfo + "]");
            appendLog("Application信息：[" + applicationInfo + "]");

            // 所有Activity信息（需要 `PackageManager.GET_ACTIVITIES` 标志位，否则为空值。）
            ActivityInfo[] activities = info.activities;
            Log.i(TAG, "Activities:" + Arrays.toString(activities));
            appendLog("Activities:" + Arrays.toString(activities));

            // 所有BroadcastReceiver信息（需要 `PackageManager.GET_RECEIVERS` 标志位，否则为空值。）
            ActivityInfo[] receivers = info.receivers;
            Log.i(TAG, "Receivers:" + Arrays.toString(receivers));
            appendLog("Receivers:" + Arrays.toString(receivers));

            // 获取应用的所有Service信息（需要 `PackageManager.GET_SERVICES` 标志位，否则为空值。）
            ServiceInfo[] services = info.services;
            Log.i(TAG, "Services:" + Arrays.toString(services));
            appendLog("Services:" + Arrays.toString(services));

            // 获取应用的所有ContentProvider信息（需要 `PackageManager.GET_PROVIDERS` 标志位，否则为空值。）
            ProviderInfo[] providers = info.providers;
            Log.i(TAG, "Providers:" + Arrays.toString(providers));
            appendLog("Providers:" + Arrays.toString(providers));

            // 权限列表（需要 `PackageManager.GET_PERMISSIONS` 标志位，否则为空值。）
            String[] permissions = info.requestedPermissions;
            if (permissions != null) {
                Log.i(TAG, "--- 权限列表 开始 ---");
                for (String permission : permissions) {
                    Log.i(TAG, permission);
                }
                Log.i(TAG, "--- 权限列表 结束 ---");
            }
        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            Log.e(TAG, "App not found! Info:[" + e.getMessage() + "]");
            appendLog("App not found! Info:[" + e.getMessage() + "]");
        }
    }

    private void test_GetApplicationInfo() {
        Log.i(TAG, "----- 获取Application信息 -----");
        appendLog("\n----- 获取Application信息 -----");

        PackageManager packageManager = this.getPackageManager();
        try {
            // 使用当前软件包作为目标
            String target = getPackageName();
            // 获取该软件包的ApplicationInfo实例
            ApplicationInfo info = packageManager.getApplicationInfo(target, 0);

            // 包名
            String packageName = info.packageName;
            Log.i(TAG, "包名：[" + packageName + "]");
            appendLog("包名：[" + packageName + "]");

            // 当前是否启用
            boolean enable = info.enabled;
            Log.i(TAG, "是否启用：[" + enable + "]");
            appendLog("是否启用：[" + enable + "]");

            // UID
            int uid = info.uid;
            Log.i(TAG, "UID：[" + uid + "]");
            appendLog("UID：[" + uid + "]");

            // 最低SDK版本
            int minSDK = info.minSdkVersion;
            Log.i(TAG, "最低SDK版本：[" + minSDK + "]");
            appendLog("最低SDK版本：[" + minSDK + "]");

            // 目标SDK版本
            int targetSDK = info.targetSdkVersion;
            Log.i(TAG, "目标SDK版本：[" + targetSDK + "]");
            appendLog("目标SDK版本：[" + targetSDK + "]");

            // 编译SDK版本
            // Android 12 (API 31) 新增的字段
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                int compileSDK = info.compileSdkVersion;
                Log.i(TAG, "编译SDK版本：[" + compileSDK + "]");
                appendLog("编译SDK版本：[" + compileSDK + "]");
            }

            // 数据目录
            String dataDir = info.dataDir;
            Log.i(TAG, "数据目录：[" + dataDir + "]");
            appendLog("数据目录：[" + dataDir + "]");

            // 程序目录
            String sourceDir = info.sourceDir;
            Log.i(TAG, "程序目录：[" + sourceDir + "]");
            appendLog("程序目录：[" + sourceDir + "]");

            // 原生库目录
            String nativeLibraryDir = info.nativeLibraryDir;
            Log.i(TAG, "原生库目录：[" + nativeLibraryDir + "]");
            appendLog("原生库目录：[" + nativeLibraryDir + "]");

            // 应用名称
            CharSequence label = info.loadLabel(packageManager);
            Log.i(TAG, "标签：[" + label + "]");
            appendLog("标签：[" + label + "]");

            // 徽章图标（带有通知、工作区应用等角标）
            Drawable icon = info.loadIcon(packageManager);
            Log.i(TAG, "徽章图标：[" + icon + "]");
            appendLog("徽章图标：[" + icon + "]");

            // 原始图标（无角标，直接返回Manifest中指明的图标。）
            Drawable rawIcon = info.loadUnbadgedIcon(packageManager);
            Log.i(TAG, "原始图标：[" + rawIcon + "]");
            appendLog("原始图标：[" + rawIcon + "]");

            // 标志位
            int flags = info.flags;
            Log.i(TAG, "Flags：[" + flags + "]");
            appendLog("Flags：[" + flags + "]");

            // 是否为系统应用？
            boolean isSystemApp = ((flags & ApplicationInfo.FLAG_SYSTEM) == 1);
            Log.i(TAG, "系统应用？:[" + isSystemApp + "]");
            appendLog("系统应用？:[" + isSystemApp + "]");
        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            Log.e(TAG, "App not found! Info:[" + e.getMessage() + "]");
            appendLog("App not found! Info:[" + e.getMessage() + "]");
        }
    }

    private void test_GetActivityInfo() {
        Log.i(TAG, "----- 获取Activity信息 -----");
        appendLog("\n----- 获取Activity信息 -----");

        PackageManager packageManager = this.getPackageManager();
        try {
            // 指定当前示例Activity为目标
            ComponentName cn = new ComponentName(this, getClass());
            // 获取该Component的ActivityInfo实例
            ActivityInfo info = packageManager.getActivityInfo(cn, 0);

            // 包名
            String packageName = info.packageName;
            Log.i(TAG, "包名：[" + packageName + "]");
            appendLog("包名：[" + packageName + "]");

            // 类名
            String name = info.name;
            Log.i(TAG, "类名：[" + name + "]");
            appendLog("类名：[" + name + "]");

            // 目标Activity
            // 若当前ActivityInfo是一个Activity别名，该属性提供了别名指向的实际Activity；若ActivityInfo不是别名，该属性为空值。
            String targetActivity = info.targetActivity;
            Log.i(TAG, "目标Activity：[" + targetActivity + "]");
            appendLog("目标Activity：[" + targetActivity + "]");

            // Activity名称（如果Activity未定义Label属性，则返回Application中定义的名称。）
            CharSequence label = info.loadLabel(packageManager);
            Log.i(TAG, "标签：[" + label + "]");
            appendLog("标签：[" + label + "]");

            // 徽章图标（带有通知、工作区应用等角标）
            // 如果Activity未定义Icon属性，则返回Application中定义的图标。
            Drawable icon = info.loadIcon(packageManager);
            Log.i(TAG, "徽章图标：[" + icon + "]");
            appendLog("徽章图标：[" + icon + "]");

            // 原始图标（无角标，直接返回Manifest中指明的图标。）
            // 如果Activity未定义Icon属性，则返回Application中定义的图标。
            Drawable rawIcon = info.loadUnbadgedIcon(packageManager);
            Log.i(TAG, "原始图标：[" + rawIcon + "]");
            appendLog("原始图标：[" + rawIcon + "]");
        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            Log.e(TAG, "App not found! Info:[" + e.getMessage() + "]");
            appendLog("App not found! Info:[" + e.getMessage() + "]");
        }


        // 以下方法可以获取Service/ContentProvider/BroadcastReceiver信息，参数同上。
        // packageManager.getServiceInfo(component, 0);
        // packageManager.getProviderInfo(component, 0);
        // packageManager.getReceiverInfo(component, 0);
    }

    private void test_GetAllPackages() {
        Log.i(TAG, "----- 获取所有软件包列表 -----");
        appendLog("\n----- 获取所有软件包列表 -----");

        PackageManager packageManager = this.getPackageManager();

        Log.i(TAG, "--- PackageInfo列表 开始 ---");
        appendLog("--- PackageInfo列表 开始 ---");
        packageManager.getInstalledPackages(0).forEach(info -> {
            Log.i(TAG, info.toString());
            appendLog(info.toString());
        });
        Log.i(TAG, "--- PackageInfo列表 结束 ---");

        Log.i(TAG, "--- ApplicationInfo列表 开始 ---");
        packageManager.getInstalledApplications(0).forEach(info -> {
            Log.i(TAG, info.toString());
            appendLog(info.toString());
        });
        Log.i(TAG, "--- ApplicationInfo列表 结束 ---");
    }

    private void test_QueryServiceInfo() {
        Log.i(TAG, "----- 查询符合条件的Service信息 -----");
        appendLog("\n----- 查询符合条件的Service信息 -----");

        PackageManager packageManager = this.getPackageManager();

        // MediaSession服务的Intent
        Intent intent = new Intent();
        intent.setAction(MediaBrowserService.SERVICE_INTERFACE);

        // 查询所有的MediaSession服务
        List<ResolveInfo> resolveInfos = packageManager.queryIntentServices(intent, 0);
        resolveInfos.forEach(info -> {
            // 获取ServiceInfo
            ServiceInfo serviceInfo = info.serviceInfo;
            Log.i(TAG, "Service:[" + serviceInfo.name + "]");
        });


        // 以下方法可以查询更多信息
        // packageManager.queryIntentActivities(intent, 0);
        // packageManager.queryIntentContentProviders(intent, 0);
        // packageManager.queryBroadcastReceivers(intent, 0);
    }

    private void test_ResolveAPK() {
        Log.i(TAG, "----- 解析APK文件 -----");
        appendLog("\n----- 解析APK文件 -----");

        PackageManager packageManager = this.getPackageManager();
        // 解析指定路径的APK文件，获取PackageInfo实例。
        PackageInfo info = packageManager.getPackageArchiveInfo("/system_ext/priv-app/SystemUI/SystemUI.apk", 0);
        Log.i(TAG, "解析结果：" + info);
        appendLog("解析结果：" + info);
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
