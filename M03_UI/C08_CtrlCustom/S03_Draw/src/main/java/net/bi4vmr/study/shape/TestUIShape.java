package net.bi4vmr.study.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiShapeBinding;

/**
 * 测试界面：基本形状。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIShape extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIShape.class.getSimpleName();

    private TestuiShapeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiShapeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_Rectangle();
        });
    }

    private void test_Rectangle() {
        int width = binding.ivRectangle.getWidth();
        int height = binding.ivRectangle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔并设置样式：尺寸为5像素，颜色为红色。
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        // 样式：填充（FILL）或描边（STROKE）。这里设置为描边，表示只绘制矩形的边框。默认是填充（FILL），表示绘制实心矩形。
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        // 设置线条连接处的样式为圆角（ROUND），这会使矩形的边框在连接处呈现圆润的效果。默认是MITER（尖角），表示线条连接处呈现尖锐的效果。
        paint.setStrokeJoin(Paint.Join.BEVEL);

        // 创建Rect对象，描述一个矩形区域，左上角坐标为(20, 20)，宽度为200像素，高度为100像素。
        Rect rect = new Rect(20, 20, 220, 120);
        // 在画布上绘制一个矩形，使用之前创建的画笔。
        canvas.drawRect(rect, paint);
        // canvas.

        binding.ivRectangle.setImageBitmap(bmp);
    }
}
