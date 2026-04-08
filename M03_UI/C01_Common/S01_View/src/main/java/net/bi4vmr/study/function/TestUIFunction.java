package net.bi4vmr.study.function;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiFunctionBinding;

public class TestUIFunction extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFunction.class.getSimpleName();

    private TestuiFunctionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnCoordinates.setOnClickListener(v -> testCoordinates());

        testGetView();
        testCreateView();
        testFgAndBg();
    }

    // 获取控件引用
    private void testGetView() {
        // 获取按钮控件的引用（控件不存在则为空值）
        TextView tvTitle = findViewById(R.id.tv_title);
        // 设置文本颜色
        tvTitle.setTextColor(Color.GREEN);


        // Android 9(API 28)新增方法：获取控件引用，如果控件不存在则抛出异常：IllegalArgumentException。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            TextView tvTitle1 = requireViewById(R.id.tv_title);
            tvTitle1.setTextColor(Color.GREEN);
        }


        // 推荐使用ViewBinding取代 `findViewById()` 方法。
        binding.tvTitle.setTextColor(Color.GREEN);
    }

    // 创建控件实例
    private void testCreateView() {
        // 创建TextView实例
        TextView textview = new TextView(this);
        // 设置文本内容
        textview.setText("这是一个动态创建的TextView");
        // 设置文本颜色
        textview.setTextColor(Color.RED);
        // 将TextView添加到容器中
        LinearLayout layout = findViewById(R.id.linearLayout);
        layout.addView(textview);
    }

    // 坐标系统
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
        binding.ivFgBg.setBackgroundResource(R.drawable.shape_icon_fg);

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
