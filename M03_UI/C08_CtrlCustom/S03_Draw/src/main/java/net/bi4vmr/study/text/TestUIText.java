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
            test_Single();
        });
    }

    private void test_Single() {
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(25.0F);

        // 参考矩形
        RectF rect = new RectF(50, 50, 150, 150);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

        // 绘制文本
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("fgHIjklMNOpq", 50, 50, paint);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        // targetRect.centerY() - (FontMetrics.bottom - FontMetrics.top) / 2 - FontMetrics.top
        float offset = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;

        canvas.drawText("fgHIjklMNOpq", 50, offset, paint);

        binding.ivSingle.setImageBitmap(bmp);
    }
}
