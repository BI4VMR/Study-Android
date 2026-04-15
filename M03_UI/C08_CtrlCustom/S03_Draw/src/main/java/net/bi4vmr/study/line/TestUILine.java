package net.bi4vmr.study.line;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLineBinding;


/**
 * 测试界面：直线。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILine extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILine.class.getSimpleName();

    private TestuiLineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_SingleLine();
            test_StrokeCap();
        });
    }

    private void test_SingleLine() {
        // 创建与ImageView尺寸相同的Bitmap，将其作为画布。
        int width = binding.ivSingleLine.getWidth();
        int height = binding.ivSingleLine.getHeight();
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
        int width = binding.ivStrokeCap.getWidth();
        int height = binding.ivStrokeCap.getHeight();
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
