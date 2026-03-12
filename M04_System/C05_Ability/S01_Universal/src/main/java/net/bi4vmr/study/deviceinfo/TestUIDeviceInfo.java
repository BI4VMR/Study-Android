package net.bi4vmr.study.deviceinfo;

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
    }

    private void testHardwareInfo() {
        Log.i(TAG, "----- 硬件信息 -----");
        appendLog("\n----- 硬件信息 -----");

        String manufacturer = Build.MANUFACTURER;
        String brand = Build.BRAND;
        String device = Build.DEVICE;
        String model = Build.MODEL;
        String product = Build.PRODUCT;
        String board = Build.BOARD;

        String hardware = Build.HARDWARE;
        String radioVersion = Build.getRadioVersion();
        String blVersion = Build.BOOTLOADER;

        // Add on Android 12 (API level 31) and above
        String sku = Build.SKU;
        String osku = Build.ODM_SKU;
        String soc = Build.SOC_MODEL;
        String soc_mf = Build.SOC_MANUFACTURER;


        Log.i(TAG, "manufacturer: " + manufacturer);
        Log.i(TAG, "brand: " + brand);
        Log.i(TAG, "device: " + device);
        Log.i(TAG, "model: " + model);
        Log.i(TAG, "product: " + product);
        Log.i(TAG, "board: " + board);

        Log.i(TAG, "hardware: " + hardware);
        Log.i(TAG, "radioVersion: " + radioVersion);
        Log.i(TAG, "blVersion: " + blVersion);

        Log.i(TAG, "sku: " + sku);
        Log.i(TAG, "odm_sku: " + osku);
        Log.i(TAG, "soc: " + soc);
        Log.i(TAG, "soc_mf: " + soc_mf);


        // 获取当前设备支持的ABI列表
        String[] abiList = Build.SUPPORTED_ABIS;
        String abisAll = Arrays.toString(abiList);
        Log.i(TAG, "当前设备支持的指令集：" + abisAll);

        // 获取当前设备支持的32位ABI列表
        String[] abiList32 = Build.SUPPORTED_32_BIT_ABIS;
        if (abiList32.length == 0) {
            Log.i(TAG, "当前设备不支持32位指令集！");
        } else {
            String abis = Arrays.toString(abiList32);
            Log.i(TAG, "当前设备支持的32位指令集：" + abis);
        }

        // 获取当前设备支持的64位ABI列表
        String[] abiList64 = Build.SUPPORTED_64_BIT_ABIS;
        if (abiList64.length == 0) {
            Log.i(TAG, "当前设备不支持64位指令集！");
        } else {
            String abis = Arrays.toString(abiList64);
            Log.i(TAG, "当前设备支持的64位指令集：" + abis);
        }
    }

    private void testSystemInfo() {
        Log.i(TAG, "----- 系统信息 -----");
        appendLog("\n----- 系统信息 -----");

        String id = Build.ID;
        String buildType = Build.TYPE;
        String tags = Build.TAGS;
        String fp = Build.FINGERPRINT;
        Log.i(TAG, "id: " + id);
        Log.i(TAG, "buildType: " + buildType);
        Log.i(TAG, "tags: " + tags);
        Log.i(TAG, "fingerprint: " + fp);

        String host = Build.HOST;
        long buitime = Build.TIME;
        String user = Build.USER;
        Log.i(TAG, "buildTime: " + buitime);
        Log.i(TAG, "user: " + user);
        Log.i(TAG, "host: " + host);

        // Build.VERSION_CODES.O
        int i = Build.VERSION.SDK_INT;
        Log.i(TAG, "SDK_INT: " + i);

        String r = Build.VERSION.RELEASE;
        Log.i(TAG, "RELEASE: " + r);
        String b = Build.VERSION.BASE_OS;
        Log.i(TAG, "BASE_OS: " + b);
        String cc = Build.VERSION.CODENAME;
        Log.i(TAG, "CODENAME: " + cc);

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
