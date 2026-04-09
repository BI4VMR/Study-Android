package net.bi4vmr.study.function;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiFunctionBinding;

/**
 * 测试界面：常用方法。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIFunction extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFunction.class.getSimpleName();

    private TestuiFunctionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnGetView.setOnClickListener(v -> testGetView());
        binding.btnCreateView.setOnClickListener(v -> testCreateView());
        binding.btnCoordinates.setOnClickListener(v -> testCoordinates());

        testFgAndBg();
    }

    private void testGetView() {
        Intent intent = new Intent(this, TestUIFunctionGetView.class);
        startActivity(intent);
    }

    private void testCreateView() {
        Intent intent = new Intent(this, TestUIFunctionCreateView.class);
        startActivity(intent);
    }

    private void testCoordinates() {
        Log.i(TAG, "--- 坐标系统 ---");
        appendLog("\n--- 坐标系统 ---\n");

        // 获取按钮控件的引用
        Button button = findViewById(R.id.btn_coordinates);

        // 获取按钮在父容器中的坐标
        int left = button.getLeft();
        int top = button.getTop();
        int right = button.getRight();
        int bottom = button.getBottom();

        Log.i(TAG, "按钮在父容器中的坐标：(" + left + ", " + top + ", " + right + ", " + bottom + ")");
        appendLog("按钮在父容器中的坐标：(" + left + ", " + top + ", " + right + ", " + bottom + ")");

        // x轴偏移量，初始为0,播放动画后会改变，向右为正，向左为负
        float tx = button.getTranslationX();
        float ty = button.getTranslationY();

        Log.i(TAG, "变换偏移量：(" + tx + ", " + ty + ")");
        appendLog("变换偏移量：(" + tx + ", " + ty + ")");

        // 左侧坐标，Left+TranslationX
        float x = button.getX();
        float y = button.getY();

        Log.i(TAG, "实际坐标：(" + x + ", " + y + ")");
        appendLog("实际坐标：(" + x + ", " + y + ")");

        /* 获取控件在当前窗口中的位置 */
        int[] locationsW = new int[2];
        button.getLocationInWindow(locationsW);
        int windowX = locationsW[0];
        int windowY = locationsW[1];

        Log.i(TAG, "Window坐标：(" + windowX + ", " + windowY + ")");
        appendLog("Window坐标：(" + windowX + ", " + windowY + ")");

        /* 获取控件在当前屏幕中的位置 */
        int[] location = new int[2];
        button.getLocationOnScreen(location);
        int screenX = location[0];
        int screenY = location[1];

        Log.i(TAG, "Screen坐标：(" + screenX + ", " + screenY + ")");
        appendLog("Screen坐标：(" + screenX + ", " + screenY + ")");
    }

    // 前景与背景
    private void testFgAndBg() {
        // 设置背景颜色为黑色
        binding.ivFgBg.setBackgroundColor(Color.BLACK);

        // 该方法可以使用一张图片作为背景，图片将自动拉伸与控件大小相匹配。
        // binding.ivFgBg.setBackground(drawable);

        // 该方法可以从资源解析图片并作为背景，但不支持主题。
        // binding.ivFgBg.setBackgroundResource(R.drawable.shape_icon_fg);

        // 设置前景为矢量图（蓝色圆圈）
        Drawable fgDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shape_icon_fg, getTheme());
        binding.ivFgBg.setForeground(fgDrawable);
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
