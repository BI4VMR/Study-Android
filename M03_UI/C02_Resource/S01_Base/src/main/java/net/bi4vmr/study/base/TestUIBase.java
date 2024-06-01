package net.bi4vmr.study.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

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

        binding.btnGetResolution.setOnClickListener(v -> test());
        binding.btnGetResolution2.setOnClickListener(v -> test());
    }

    // 功能模块
    private void test() {
        Log.i(TAG, "--- 功能模块 ---");
        binding.tvLog.append("\n--- 功能模块 ---\n");

        // val windowManager = getWindowManager();
        WindowManager windowManager = this.getWindowManager();
        DisplayMetrics dm1 = Resources.getSystem().getDisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm1);
        Log.i(TAG, "dm.widthPixels: " + dm1.widthPixels);
        Log.i(TAG, "dm.heightPixels: " + dm1.heightPixels);
        Log.i(TAG, "dm.densityDpi: " + dm1.densityDpi);
        Log.i(TAG, "dm.density: " + dm1.density);
        Log.i(TAG, "dm.scaledDensity: " + dm1.scaledDensity);

        // int w = windowManager.getCurrentWindowMetrics().getBounds().width();

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Log.i(TAG, "dm.widthPixels: " + dm.widthPixels);
        Log.i(TAG, "dm.heightPixels: " + dm.heightPixels);
        Log.i(TAG, "dm.densityDpi: " + dm.densityDpi);
        Log.i(TAG, "dm.density: " + dm.density);
        Log.i(TAG, "dm.scaledDensity: " + dm.scaledDensity);

        Log.i(TAG, "100 dp2px: " + dp2px(100));
        Log.i(TAG, "100 px2dp: " + px2dp(this,100));
    }

    // 功能模块
    private void test2() {
        Log.i(TAG, "--- 功能模块 ---");
        binding.tvLog.append("\n--- 功能模块 ---\n");

        // val windowManager = getWindowManager();
        WindowManager windowManager = this.getWindowManager();
        DisplayMetrics dm1 = Resources.getSystem().getDisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm1);
        Log.i(TAG, "dm.widthPixels: " + dm1.widthPixels);
        Log.i(TAG, "dm.heightPixels: " + dm1.heightPixels);
        Log.i(TAG, "dm.densityDpi: " + dm1.densityDpi);
        Log.i(TAG, "dm.density: " + dm1.density);
        Log.i(TAG, "dm.scaledDensity: " + dm1.scaledDensity);

        // int w = windowManager.getCurrentWindowMetrics().getBounds().width();

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Log.i(TAG, "dm.widthPixels: " + dm.widthPixels);
        Log.i(TAG, "dm.heightPixels: " + dm.heightPixels);
        Log.i(TAG, "dm.densityDpi: " + dm.densityDpi);
        Log.i(TAG, "dm.density: " + dm.density);
        Log.i(TAG, "dm.scaledDensity: " + dm.scaledDensity);
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float px2dp(@NonNull Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }
}
