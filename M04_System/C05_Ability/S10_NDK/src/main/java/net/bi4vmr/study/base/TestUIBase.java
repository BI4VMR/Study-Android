package net.bi4vmr.study.base;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.Arrays;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnGetABIs.setOnClickListener(v -> testGetABIs());
        binding.btnPassBasicTypes.setOnClickListener(v -> testPassBasicTypes());
        binding.btnPassString.setOnClickListener(v -> testPassString());
        binding.btnPassStringArray.setOnClickListener(v -> testPassStringArray());

        binding.tvLog.setText("请查看控制台日志。");
    }

    // 获取ABI信息
    private void testGetABIs() {
        binding.tvLog.append("\n--- 获取ABI信息 ---\n");
        Log.i(TAG, "--- 获取ABI信息 ---");

        // 获取当前硬件平台支持的所有ABI
        String[] allABIs = Build.SUPPORTED_ABIS;
        binding.tvLog.append("当前硬件平台支持的所有ABI：" + Arrays.toString(allABIs) + "\n");
        Log.i(TAG, "当前硬件平台支持的所有ABI：" + Arrays.toString(allABIs));

        // 获取当前硬件平台支持的32位ABI
        String[] ABIs32 = Build.SUPPORTED_32_BIT_ABIS;
        binding.tvLog.append("当前硬件平台支持的32位ABI：" + Arrays.toString(ABIs32) + "\n");
        Log.i(TAG, "当前硬件平台支持的32位ABI：" + Arrays.toString(ABIs32));

        // 获取当前硬件平台支持的64位ABI
        String[] ABIs64 = Build.SUPPORTED_64_BIT_ABIS;
        binding.tvLog.append("当前硬件平台支持的64位ABI：" + Arrays.toString(ABIs64) + "\n");
        Log.i(TAG, "当前硬件平台支持的64位ABI：" + Arrays.toString(ABIs64));

        // 获取当前硬件平台首选的库文件存放路径
        ApplicationInfo appInfo = getApplication().getApplicationInfo();
        String libDir = appInfo.nativeLibraryDir;
        binding.tvLog.append("当前应用的库目录：" + libDir + "\n");
        Log.i(TAG, "当前应用的库目录：" + libDir);
    }

    // 传递基本数据类型参数
    private void testPassBasicTypes() {
        binding.tvLog.append("\n--- 传递基本数据类型参数 ---\n");
        Log.i(TAG, "--- 传递基本数据类型参数 ---");

        JNIClass c = new JNIClass();
        c.passBasicTypes(true, -1000, 3.1415);
    }

    // 传递字符串参数
    private void testPassString() {
        binding.tvLog.append("\n--- 传递字符串参数 ---\n");
        Log.i(TAG, "--- 传递字符串参数 ---");

        JNIClass c = new JNIClass();
        c.passString("This is some text info.");
    }

    // 传递字符串数组参数
    private void testPassStringArray() {
        binding.tvLog.append("\n--- 传递字符串数组参数 ---\n");
        Log.i(TAG, "--- 传递字符串数组参数 ---");

        // 测试数据
        String[] array = new String[]{"A", "B", "C", "D"};

        JNIClass c = new JNIClass();
        c.passStringArray(array);
    }
}
