package net.bi4vmr.study.remote;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import net.bi4vmr.study.databinding.TestuiResourceRemoteBinding;

import java.lang.reflect.Method;

/**
 * 测试界面：外部资源。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIRemote extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIRemote.class.getSimpleName();

    private TestuiResourceRemoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiResourceRemoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnRemoteResource.setOnClickListener(v -> testGetRemoteResource());
        binding.btnAPKResource.setOnClickListener(v -> testGetAPKResource());
        binding.btnReset.setOnClickListener(v -> binding.imageview.setImageDrawable(null));
    }

    private void testGetRemoteResource() {
        Log.i(TAG, "----- 获取其他软件包中的资源 -----");
        appendLog("\n----- 获取其他软件包中的资源 -----");

        try {
            String targetPackage = "net.bi4vmr.study.ui.resource.base.res";
            // 创建指定软件包的Context
            Context remoteContext = createPackageContext(targetPackage, 0);
            // 查询资源名称对应的资源ID
            int resID = remoteContext.getResources().getIdentifier("alarm", "drawable", targetPackage);
            // 如果ID大于0，说明资源存在；否则表示资源不存在。
            if (resID > 0) {
                // 使用目标软件包的Resources获取该ID对应的资源
                Drawable drawable = ResourcesCompat.getDrawable(remoteContext.getResources(), resID, remoteContext.getTheme());
                // 使用资源
                binding.imageview.setImageDrawable(drawable);
            } else {
                Log.w(TAG, "未找到远程资源！");
                appendLog("未找到远程资源！");
            }
        } catch (Exception e) {
            // 如果找不到目标软件包，请先确保安装了 `S01_Base_ResPack` 示例程序。
            Log.e(TAG, "获取远程资源失败！", e);
            appendLog("获取远程资源失败！");
        }
    }

    private void testGetAPKResource() {
        Log.i(TAG, "----- 获取APK文件中的资源 -----");
        appendLog("\n----- 获取APK文件中的资源 -----");

        // 目标APK文件路径
        String apkPath = "/data/S01_Base_ResPack-debug.apk";
        // 解析APK包名
        String pkgName = "UNKNOWN";
        PackageInfo pkgInfo = getPackageManager().getPackageArchiveInfo(
                apkPath,
                PackageManager.PackageInfoFlags.of(0L)
        );
        if (pkgInfo != null) {
            pkgName = pkgInfo.packageName;
        }

        try {
            // 创建AssetManager实例并加载资源
            AssetManager remoteAM = AssetManager.class.newInstance();
            // 通过反射调用 `addAssetPath()` 方法加载APK资源
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            // 该方法将返回当前APK资源在AssetManager中的索引，若为 `0` 则表示加载失败。
            Object result = method.invoke(remoteAM, apkPath);
            int index = -1;
            if (result instanceof Integer) {
                index = (Integer) result;
            }

            if (index <= 0) {
                Log.e(TAG, "加载APK资源失败！");
                appendLog("加载APK资源失败！");
                return;
            }

            // 创建Resources实例，并套用当前应用的显示参数和配置。
            Resources remoteRes = new Resources(
                    remoteAM,
                    getResources().getDisplayMetrics(),
                    getResources().getConfiguration()
            );
            // 查询资源名称对应的资源ID
            int resID = remoteRes.getIdentifier("house", "drawable", pkgName);
            if (resID > 0) {
                Drawable drawable = ResourcesCompat.getDrawable(remoteRes, resID, null);
                binding.imageview.setImageDrawable(drawable);
            } else {
                Log.w(TAG, "未找到指定资源！");
                appendLog("未找到指定资源！");
            }
        } catch (Exception e) {
            Log.e(TAG, "获取远程资源失败！", e);
            appendLog("获取远程资源失败！");
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        binding.tvLog.post(() -> binding.tvLog.append("\n" + text.toString()));
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
