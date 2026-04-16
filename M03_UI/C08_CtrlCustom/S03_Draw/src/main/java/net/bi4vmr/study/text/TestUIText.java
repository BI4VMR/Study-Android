package net.bi4vmr.study.text;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiTextBinding;

/**
 * 测试界面：文本。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIText extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIText.class.getSimpleName();

    private TestuiTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_NoCenter();
            test_CenterV();
            test_CenterH();
        });
    }

    private void test_NoCenter() {
        int width = binding.ivNoCenter.getWidth();
        int height = binding.ivNoCenter.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        // 在 `(50, 50)` 的位置绘制文本
        paint.setStyle(Paint.Style.FILL);
        // 字号为25像素
        paint.setTextSize(25.0F);
        canvas.drawText("fgHIjklMNOpq", 50, 50, paint);

        // 在 `(50, 50)` 的位置绘制参考矩形
        RectF rect = new RectF(50, 50, 300, 150);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

        binding.ivNoCenter.setImageBitmap(bmp);
    }

    private void test_CenterV() {
        int width = binding.ivCenterV.getWidth();
        int height = binding.ivCenterV.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        // 在 `(50, 50)` 的位置绘制参考矩形
        RectF rect = new RectF(50, 50, 300, 150);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

        // 绘制文本
        paint.setStyle(Paint.Style.FILL);
        // 设置字号
        paint.setTextSize(25.0F);
        // 测量当前字号时的文本行高
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        // 计算文本基线在目标区域高度中心位置的偏移量
        float y = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText("fgHIjklMNOpq", 50, y, paint);

        binding.ivCenterV.setImageBitmap(bmp);
    }

    private void test_CenterH() {
        int width = binding.ivCenterH.getWidth();
        int height = binding.ivCenterH.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        // 在 `(50, 50)` 的位置绘制参考矩形
        RectF rect = new RectF(50, 50, 300, 150);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

        // 绘制文本
        paint.setStyle(Paint.Style.FILL);
        // 设置字号
        paint.setTextSize(25.0F);
        // 设置绘制位置相对X坐标水平居中
        paint.setTextAlign(Paint.Align.CENTER);
        // 绘制时X坐标对齐到目标区域水平中心位置
        canvas.drawText("fgHIjklMNOpq", rect.centerX(), 50, paint);

        binding.ivCenterH.setImageBitmap(bmp);
    }
}
