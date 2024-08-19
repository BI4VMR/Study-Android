package net.bi4vmr.study.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.NonNull;
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
    }

    // 获取屏幕信息
    private void testGetDisplayInfo() {
        Log.i(TAG, "--- 获取屏幕信息 ---");
        binding.tvLog.append("\n--- 获取屏幕信息 ---\n");

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Log.i(TAG, "屏幕宽度：" + dm.widthPixels);
        Log.i(TAG, "屏幕高度：" + dm.heightPixels);
        Log.i(TAG, "像素密度：" + dm.densityDpi);
        Log.i(TAG, "缩放倍率(DP)：" + dm.density);
        Log.i(TAG, "缩放倍率(SP)：" + dm.scaledDensity);

        // Log.i(TAG, "100 dp2px: " + dp2px(100));
        // Log.i(TAG, "100 px2dp: " + px2dp(this, 100));
    }

    private void testUnitConversion() {
        // DP -> PX
    }

    // 将DP转换为PX
    public static int dpToPX(float dp) {
        float rawValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
        // 物理像素不可能为小数，因此保留整数部分即可。
        return Math.round(rawValue);
    }

    // // 将DP转换为PX
    // public static int dpToPixel(float dp) {
    //     float rawValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_, dp, Resources.getSystem().getDisplayMetrics());
    //     // 物理像素不可能为小数，因此保留整数部分即可。
    //     return Math.round(rawValue);
    // }

    public static float pxToDP(@NonNull Context context, int pxValue) {
        // 获取缩放倍率
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / scaledDensity + 0.5f);
    }
}
