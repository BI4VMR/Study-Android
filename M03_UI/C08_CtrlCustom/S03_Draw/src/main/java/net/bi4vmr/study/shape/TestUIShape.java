package net.bi4vmr.study.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
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
            test_RoundCornerRectangle();
            test_DoubleRoundRectangle();
            test_Circle();
            test_Oval();
            test_Arc();
            test_PaintStyle();
            test_StrokeCorner();
        });
    }

    private void test_Rectangle() {
        int width = binding.ivRectangle.getWidth();
        int height = binding.ivRectangle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 创建Rect对象，描述矩形区域，左上角顶点为 `(50, 50)` ，右下角顶点为 `(250, 200)`。
        Rect rect = new Rect(50, 50, 250, 200);
        // 在画布上绘制Rect对象所描述的矩形
        canvas.drawRect(rect, paint);


        // 等价写法
        // canvas.drawRect(50.0F, 50.0F, 250.0F, 200.0F, paint);

        binding.ivRectangle.setImageBitmap(bmp);
    }

    private void test_RoundCornerRectangle() {
        int width = binding.ivRoundCornerRectangle.getWidth();
        int height = binding.ivRoundCornerRectangle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 创建RectF对象，描述矩形区域，左上角顶点为 `(50, 50)` ，右下角顶点为 `(250, 200)`。
        RectF rect = new RectF(50, 50, 250, 200);
        // 在画布上绘制Rect对象所描述的矩形，圆角半径为 `25.0F`。
        canvas.drawRoundRect(rect, 25.0F, 25.0F, paint);


        // 等价写法
        // canvas.drawRoundRect(50.0F, 50.0F, 250.0F, 200.0F, 25.0F, 25.0F, paint);

        binding.ivRoundCornerRectangle.setImageBitmap(bmp);
    }

    private void test_DoubleRoundRectangle() {
        int width = binding.ivDoubleRoundRectangle.getWidth();
        int height = binding.ivDoubleRoundRectangle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 外层矩形
        RectF rectOut = new RectF(50, 50, 250, 200);
        // 内层矩形（应当比外层更小）
        RectF rectIn = new RectF(60, 60, 240, 190);
        // Android 10新增API：绘制外层矩形减去内层矩形剩余的区域
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            canvas.drawDoubleRoundRect(rectOut, 25.0F, 25.0F, rectIn, 5.0F, 5.0F, paint);


            // 等价写法
            // canvas.drawDoubleRoundRect(rectOut, new float[]{25.0F, 25.0F}, rectIn, new float[]{5.0F, 5.0F}, paint);
        }

        binding.ivDoubleRoundRectangle.setImageBitmap(bmp);
    }

    private void test_Circle() {
        int width = binding.ivCircle.getWidth();
        int height = binding.ivCircle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 以 `(150, 150)` 为圆心，绘制半径为75像素的圆形。
        canvas.drawCircle(150, 150, 75, paint);

        binding.ivCircle.setImageBitmap(bmp);
    }

    private void test_Oval() {
        int width = binding.ivOval.getWidth();
        int height = binding.ivOval.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 创建RectF对象，描述椭圆区域的外切矩形。
        RectF rect = new RectF(50, 50, 250, 200);
        canvas.drawOval(rect, paint);


        // 等价写法
        // canvas.drawOval(50.0F, 50.0F, 250.0F, 200.0F, paint);

        binding.ivOval.setImageBitmap(bmp);
    }

    private void test_Arc() {
        int width = binding.ivArc.getWidth();
        int height = binding.ivArc.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 创建RectF对象，描述椭圆区域的外切矩形。
        RectF rect1 = new RectF(50, 50, 250, 200);
        // 在RectF所指明的区域内绘制扇形，起始角度为0度（矩形水平中线右侧），扫过270度（顺时针方向）。
        canvas.drawArc(rect1, 0.0F, 270.0F, true, paint);


        // 在RectF所指明的区域内绘制扇形，起始角度为0度（矩形水平中线右侧），扫过-90度（逆时针方向）。
        RectF rect2 = new RectF(300, 50, 500, 200);
        canvas.drawArc(rect2, 0.0F, -90.0F, true, paint);


        // 在RectF所指明的区域内绘制扇形，起始角度为0度（矩形水平中线右侧），扫过-90度，图形不穿过中心点。
        RectF rect3 = new RectF(550, 50, 750, 200);
        canvas.drawArc(rect3, 0.0F, -90.0F, false, paint);

        binding.ivArc.setImageBitmap(bmp);
    }

    private void test_PaintStyle() {
        int width = binding.ivStyle.getWidth();
        int height = binding.ivStyle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // 画笔样式：填充，默认值，表示仅绘制图形自身，无描边。
        paint.setStyle(Paint.Style.FILL);

        Rect rect1 = new Rect(50, 50, 250, 200);
        canvas.drawRect(rect1, paint);


        // 画笔样式：描边，表示仅绘制图形外边框，宽度由 `setStrokeWidth()` 方法设置。
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(25.0F);

        Rect rect2 = new Rect(300, 50, 500, 200);
        canvas.drawRect(rect2, paint);


        // 画笔样式：填充与描边，表示绘制图形自身与外边框，边框宽度由 `setStrokeWidth()` 方法设置。
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(25.0F);

        Rect rect3 = new Rect(550, 50, 750, 200);
        canvas.drawRect(rect3, paint);

        binding.ivStyle.setImageBitmap(bmp);
    }

    private void test_StrokeCorner() {
        int width = binding.ivStrokeCorner.getWidth();
        int height = binding.ivStrokeCorner.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(25);


        // 线段转角样式：直角，默认值。
        paint.setStrokeJoin(Paint.Join.MITER);

        Rect rect1 = new Rect(50, 50, 250, 200);
        canvas.drawRect(rect1, paint);

        // 线段转角样式：斜角
        paint.setStrokeJoin(Paint.Join.BEVEL);

        Rect rect2 = new Rect(300, 50, 500, 200);
        canvas.drawRect(rect2, paint);

        // 线段转角样式：圆弧
        paint.setStrokeJoin(Paint.Join.ROUND);

        Rect rect3 = new Rect(550, 50, 750, 200);
        canvas.drawRect(rect3, paint);

        binding.ivStrokeCorner.setImageBitmap(bmp);
    }
}
