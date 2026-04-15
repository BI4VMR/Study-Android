package net.bi4vmr.study.shader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiShaderBinding;

/**
 * 测试界面：Shader。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIShader extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIShader.class.getSimpleName();

    private TestuiShaderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiShaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_Linear();
            test_Sweep();
        });
    }

    private void test_Linear() {
        int width = binding.ivLinear.getWidth();
        int height = binding.ivLinear.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 绘图区域
        RectF rect = new RectF(0, 0, width, height);

        // 创建线性渐变
        LinearGradient shader = new LinearGradient(0, 0, width, height,
                new int[]{0xFFFF0000, 0xFF00FF00, 0xFF0000FF},
                null, LinearGradient.TileMode.CLAMP);

        // 创建画笔
        Paint paint = new Paint();
        // 将渐变应用到画笔上
        paint.setShader(shader);
        // 绘制图形
        canvas.drawRect(rect, paint);

        binding.ivLinear.setImageBitmap(bmp);
    }

    private void test_Sweep() {
        int width = binding.ivSweep.getWidth();
        int height = binding.ivSweep.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 绘图区域
        RectF rect = new RectF(0, 0, width, height);

        // 创建扇形渐变
        SweepGradient gradient = new SweepGradient(rect.centerX(), rect.centerY(), Color.RED, Color.YELLOW);

        // 创建画笔
        Paint paint = new Paint();
        // 将渐变应用到画笔上
        paint.setShader(gradient);
        // 绘制图形
        canvas.drawRect(rect, paint);

        binding.ivSweep.setImageBitmap(bmp);
    }
}
