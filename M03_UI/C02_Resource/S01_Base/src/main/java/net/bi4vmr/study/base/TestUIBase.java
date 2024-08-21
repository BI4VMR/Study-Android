package net.bi4vmr.study.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetDisplayInfo.setOnClickListener(v -> testGetDisplayInfo());
        binding.btnUnitConversion.setOnClickListener(v -> testUnitConversion());
    }

    // 获取屏幕信息
    private void testGetDisplayInfo() {
        Log.i(TAG, "--- 获取屏幕信息 ---");
        binding.tvLog.append("\n--- 获取屏幕信息 ---\n");

        /* 单显示器环境 */
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Log.i(TAG, "屏幕宽度：" + dm.widthPixels);
        Log.i(TAG, "屏幕高度：" + dm.heightPixels);
        Log.i(TAG, "像素密度：" + dm.densityDpi);
        Log.i(TAG, "缩放倍率(DP)：" + dm.density);
        Log.i(TAG, "缩放倍率(SP)：" + dm.scaledDensity);

        binding.tvLog.append("屏幕宽度：" + dm.widthPixels + "\n");
        binding.tvLog.append("屏幕高度：" + dm.heightPixels + "\n");
        binding.tvLog.append("像素密度：" + dm.densityDpi + "\n");
        binding.tvLog.append("缩放倍率(DP)：" + dm.density + "\n");
        binding.tvLog.append("缩放倍率(SP)：" + dm.scaledDensity + "\n");

        /* 多显示器环境 */
        // 创建一个空的DisplayMetrics对象
        // DisplayMetrics dm = new DisplayMetrics();
        // 使用当前Context所关联的显示器设置DisplayMetrics参数
        // context.getDisplay().getRealMetrics(dm);
    }

    // 单位转换
    private void testUnitConversion() {
        Log.i(TAG, "--- 单位转换 ---");
        binding.tvLog.append("\n--- 单位转换 ---\n");

        Log.i(TAG, "100dp -> ?px: " + dpToPX(100));
        Log.i(TAG, "100dp -> ?sx: " + spToPX(100));
        Log.i(TAG, "300px -> ?dp: " + pxToDP(300));
        Log.i(TAG, "300px -> ?sp: " + pxToSP(300));

        binding.tvLog.append("100dp -> ?px: " + dpToPX(100) + "\n");
        binding.tvLog.append("100dp -> ?sx: " + spToPX(100) + "\n");
        binding.tvLog.append("300px -> ?dp: " + pxToDP(300) + "\n");
        binding.tvLog.append("300px -> ?sp: " + pxToSP(300) + "\n");
    }

    // 将DP转换为PX
    public static int dpToPX(float dpValue) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        // "applyDimension()"方法用于将指定的单位转换为像素
        float rawValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, dm);
        // 物理像素不可能为小数，因此保留整数部分即可。
        return Math.round(rawValue);
    }

    // 将SP转换为PX
    public static int spToPX(float spValue) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        float rawValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, dm);
        return Math.round(rawValue);
    }

    // 将PX转换为DP
    public static int pxToDP(int pxValue) {
        // 获取DP缩放倍率
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(pxValue / density);
    }

    // 将PX转换为SP
    public static int pxToSP(int pxValue) {
        // 获取SP缩放倍率
        float density = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return Math.round(pxValue / density);
    }
}
