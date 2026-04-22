package net.bi4vmr.study.deviceinfo;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiDeviceinfoBinding;

import java.util.Arrays;

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIDeviceInfo extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIDeviceInfo.class.getSimpleName();

    private TestuiDeviceinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiDeviceinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnHardwareInfo.setOnClickListener(v -> testHardwareInfo());
        binding.btnSystemInfo.setOnClickListener(v -> testSystemInfo());
        binding.btnID.setOnClickListener(v -> testID());
    }

    private void testHardwareInfo() {
        Log.i(TAG, "----- 硬件信息 -----");
        appendLog("\n----- 硬件信息 -----");

        // 制造商
        String manufacturer = Build.MANUFACTURER;
        // 品牌
        String brand = Build.BRAND;
        // 型号（面向用户，通常系统设置中“关于设备”页面设备型号就是该值。）
        String model = Build.MODEL;

        Log.i(TAG, "制造商：[" + manufacturer + "]");
        Log.i(TAG, "品牌：[" + brand + "]");
        Log.i(TAG, "型号：[" + model + "]");
        appendLog("制造商：[" + manufacturer + "]");
        appendLog("品牌：[" + brand + "]");
        appendLog("型号：[" + model + "]");


        // Android 12 (API 31) 新增的字段
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // SoC型号
            String soc = Build.SOC_MODEL;
            // SoC制造商
            String soc_manufacturer = Build.SOC_MANUFACTURER;
            // 产品规格，用于区分型号相同但内存与闪存配置不同的设备。
            String sku = Build.SKU;
            // 代工厂产品规格
            String sku_odm = Build.ODM_SKU;

            Log.i(TAG, "SoC型号：[" + soc + "]");
            Log.i(TAG, "SoC制造商：[" + soc_manufacturer + "]");
            Log.i(TAG, "产品规格：[" + sku + "]");
            Log.i(TAG, "代工厂产品规格：[" + sku_odm + "]");
            appendLog("SoC型号：[" + soc + "]");
            appendLog("SoC制造商：[" + soc_manufacturer + "]");
            appendLog("产品规格：[" + sku + "]");
            appendLog("代工厂产品规格：[" + sku_odm + "]");
        }


        // 平台名称（例如： `qcom` 表示 `Qualcomm` 。）
        String hardware = Build.HARDWARE;
        // 开发代号
        String product = Build.PRODUCT;
        String device = Build.DEVICE;
        String board = Build.BOARD;
        // 基带版本（如果厂商未配置则值为 `unknown` 。）
        String bbVersion = Build.getRadioVersion();
        // BootLoader版本（如果厂商未配置则值为 `unknown` 。）
        String blVersion = Build.BOOTLOADER;

        Log.i(TAG, "平台名称：[" + hardware + "]");
        Log.i(TAG, "Product：[" + product + "]");
        Log.i(TAG, "Device：[" + device + "]");
        Log.i(TAG, "Board：[" + board + "]");
        Log.i(TAG, "基带版本：[" + bbVersion + "]");
        Log.i(TAG, "BootLoader版本：[" + blVersion + "]");
        appendLog("平台名称：[" + hardware + "]");
        appendLog("Product：[" + product + "]");
        appendLog("Device：[" + device + "]");
        appendLog("Board：[" + board + "]");
        appendLog("基带版本：[" + bbVersion + "]");
        appendLog("BootLoader版本：[" + blVersion + "]");


        // 获取当前设备支持的ABI列表
        String[] abiList = Build.SUPPORTED_ABIS;
        String abisAll = Arrays.toString(abiList);
        Log.i(TAG, "当前设备支持的指令集：" + abisAll);
        appendLog("当前设备支持的指令集：" + abisAll);

        // 获取当前设备支持的32位ABI列表
        String[] abiList32 = Build.SUPPORTED_32_BIT_ABIS;
        if (abiList32.length == 0) {
            Log.i(TAG, "当前设备不支持32位指令集！");
            appendLog("当前设备不支持32位指令集！");
        } else {
            String abis = Arrays.toString(abiList32);
            Log.i(TAG, "当前设备支持的32位指令集：" + abis);
            appendLog("当前设备支持的32位指令集：" + abis);
        }

        // 获取当前设备支持的64位ABI列表
        String[] abiList64 = Build.SUPPORTED_64_BIT_ABIS;
        if (abiList64.length == 0) {
            Log.i(TAG, "当前设备不支持64位指令集！");
            appendLog("当前设备不支持64位指令集！");
        } else {
            String abis = Arrays.toString(abiList64);
            Log.i(TAG, "当前设备支持的64位指令集：" + abis);
            appendLog("当前设备支持的64位指令集：" + abis);
        }
    }

    private void testSystemInfo() {
        Log.i(TAG, "----- 系统信息 -----");
        appendLog("\n----- 系统信息 -----");

        /* 基本信息 */
        // 系统版本ID
        String id = Build.ID;
        // 版本名称（面向用户，通常系统设置中“关于设备”页面系统版本就是该值。）
        String disp = Build.DISPLAY;


        String buildType = Build.TYPE;

        String tags = Build.TAGS;

        String fp = Build.FINGERPRINT;
        Log.i(TAG, "id: " + id);
        Log.i(TAG, "DISPLAY: " + disp);
        Log.i(TAG, "buildType: " + buildType);
        Log.i(TAG, "tags: " + tags);
        Log.i(TAG, "fingerprint: " + fp);

        /* 编译信息 */

        // 编译时间戳
        long time = Build.TIME;
        // 编译设备
        String host = Build.HOST;
        // 编译用户
        String user = Build.USER;
        Log.i(TAG, "buildTime: " + time);
        Log.i(TAG, "user: " + user);
        Log.i(TAG, "host: " + host);

        // 系统版本，13 表示 Android 13，12 表示 Android 12，以此类推。
        String r = Build.VERSION.RELEASE;
        Log.i(TAG, "RELEASE: " + r);
        // SDK版本，对应 Build.VERSION_CODES.O 等常量
        int i = Build.VERSION.SDK_INT;
        Log.i(TAG, "SDK_INT: " + i);

        String b = Build.VERSION.BASE_OS;
        Log.i(TAG, "BASE_OS: " + b);

        // 开发代号，正式版总是 "REL"。
        String cc = Build.VERSION.CODENAME;
        Log.i(TAG, "CODENAME: " + cc);
        // 内部版本号，通常是Git Hash等
        String inte = Build.VERSION.INCREMENTAL;
        Log.i(TAG, "INCREMENTAL: " + inte);
        // 安全补丁版本，一般为日期。
        String sp = Build.VERSION.SECURITY_PATCH;
        Log.i(TAG, "SECURITY_PATCH: " + sp);
    }

    private void test_MemoryInfo() {
        Log.i(TAG, "----- 内存信息 -----");
        appendLog("\n----- 内存信息 -----");

        ActivityManager am = getSystemService(ActivityManager.class);
        // 创建一个MemoryInfo实例
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        // 通过ActivityManager获取内存信息
        am.getMemoryInfo(info);

        Log.i(TAG, "Total Memory: ${mi.totalMem / 1024 / 1024} MB");
        Log.i(TAG, "Available Memory: ${mi.availMem / 1024 / 1024} MB");
        Log.i(TAG, "threshold Memory: ${mi.threshold / 1024 / 1024} MB");
        Log.i(TAG, "lowMemory?: ${mi.lowMemory}");
    }

    @SuppressLint("HardwareIds")
    private void testID() {
        Log.i(TAG, "----- 唯一标识符 -----");
        appendLog("\n----- 唯一标识符 -----");

        // 应用ID，无需权限，直接获取
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i(TAG, "androidId: " + androidId);
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
