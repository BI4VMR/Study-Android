package net.bi4vmr.study.text;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiPointBinding;

/**
 * 测试界面：基本形状。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIText extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIText.class.getSimpleName();

    private TestuiPointBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPointBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_Single();
            test_Batch();
            test_SingleLine();
            test_StrokeCap();
        });
    }

    private void test_Single() {
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔并设置样式：尺寸为5像素，颜色为红色。
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);

        // 在画布 `(50, 50)` 的位置绘制一个点
        canvas.drawPoint(50, 50, paint);
        // 在画布 `(100, 100)` 的位置绘制一个点
        canvas.drawPoint(100, 100, paint);

        binding.ivSingle.setImageBitmap(bmp);
    }

    private void test_Batch() {
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setColor(Color.GREEN);

        // 使用数组表示一批点，每两个元素为一组，分别表示点的X坐标与Y坐标。
        float[] points = {50, 50, 100, 100, 50, 100, 100, 50};
        canvas.drawPoints(points, p);

        binding.ivBatch.setImageBitmap(bmp);
    }

    private void test_SingleLine() {
        // 创建与ImageView尺寸相同的Bitmap，将其作为画布。
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setColor(Color.GREEN);


        canvas.drawLine(50, 50, 100, 100, p);

        canvas.drawLine(50, 200, 400, 200, p);

        // 将绘制后的Bitmap设置到ImageView中。
        binding.ivSingleLine.setImageBitmap(bmp);
    }

    private void test_StrokeCap() {
        // 创建与ImageView尺寸相同的Bitmap，将其作为画布。
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint p = new Paint();
        p.setStrokeWidth(50);
        p.setColor(Color.CYAN);

        // 无
        p.setStrokeCap(Paint.Cap.BUTT);

        canvas.drawLine(50, 50, 50, 400, p);

        p.setStrokeCap(Paint.Cap.SQUARE);

        canvas.drawLine(150, 50, 150, 400, p);

        p.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawLine(250, 50, 250, 400, p);

        // 将绘制后的Bitmap设置到ImageView中。
        binding.ivStrokeCap.setImageBitmap(bmp);
    }
}
