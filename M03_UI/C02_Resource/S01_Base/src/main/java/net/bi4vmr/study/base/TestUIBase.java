package net.bi4vmr.study.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnGetDisplayInfo.setOnClickListener(v -> testGetDisplayInfo());
        binding.btnUnitConversion.setOnClickListener(v -> testUnitConversion());
    }

    private void testGetDisplayInfo() {
        Log.i(TAG, "----- 获取屏幕信息 -----");
        appendLog("\n----- 获取屏幕信息 -----");

        /* 单显示器环境 */
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();

        Log.i(TAG, "屏幕宽度：" + dm.widthPixels);
        Log.i(TAG, "屏幕高度：" + dm.heightPixels);
        Log.i(TAG, "像素密度：" + dm.densityDpi);
        Log.i(TAG, "缩放倍率(DP)：" + dm.density);
        Log.i(TAG, "缩放倍率(SP)：" + dm.scaledDensity);

        appendLog("屏幕宽度：" + dm.widthPixels);
        appendLog("屏幕高度：" + dm.heightPixels);
        appendLog("像素密度：" + dm.densityDpi);
        appendLog("缩放倍率(DP)：" + dm.density);
        appendLog("缩放倍率(SP)：" + dm.scaledDensity);

        /* 多显示器环境 */
        // 创建一个空的DisplayMetrics对象
        // DisplayMetrics dm = new DisplayMetrics();
        // 使用当前Context所关联的显示器设置DisplayMetrics参数
        // context.getDisplay().getRealMetrics(dm);
    }

    private void testUnitConversion() {
        Log.i(TAG, "----- 单位转换 -----");
        appendLog("\n----- 单位转换 -----");

        Log.i(TAG, "100dp -> ?px: " + dpToPX(100));
        Log.i(TAG, "100sp -> ?px: " + spToPX(100));
        Log.i(TAG, "300px -> ?dp: " + pxToDP(300));
        Log.i(TAG, "300px -> ?sp: " + pxToSP(300));

        appendLog("100dp -> ?px: " + dpToPX(100));
        appendLog("100sp -> ?px: " + spToPX(100));
        appendLog("300px -> ?dp: " + pxToDP(300));
        appendLog("300px -> ?sp: " + pxToSP(300));
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
