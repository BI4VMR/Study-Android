package net.bi4vmr.study.shape

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import net.bi4vmr.study.databinding.TestuiShapeBinding

/**
 * 测试界面：基本形状。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIShapeKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIShapeKT::class.java.simpleName}"
    }

    private val binding: TestuiShapeBinding by lazy {
        TestuiShapeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.post {
            test_Rectangle()
            test_RoundCornerRectangle()
            test_DoubleRoundRectangle()
            test_Circle()
            test_Oval()
            test_Arc()
            test_PaintStyle()
            test_StrokeCorner()
        }
    }

    private fun test_Rectangle() {
        val width = binding.ivRectangle.width
        val height = binding.ivRectangle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 创建Rect对象，描述矩形区域，左上角顶点为 `(50, 50)` ，右下角顶点为 `(250, 200)`。
        val rect = Rect(50, 50, 250, 200)
        // 在画布上绘制Rect对象所描述的矩形
        canvas.drawRect(rect, paint)


        // 等价写法
        // canvas.drawRect(50.0F, 50.0F, 250.0F, 200.0F, paint)

        binding.ivRectangle.setImageBitmap(bmp)
    }

    private fun test_RoundCornerRectangle() {
        val width = binding.ivRoundCornerRectangle.width
        val height = binding.ivRoundCornerRectangle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 创建RectF对象，描述矩形区域，左上角顶点为 `(50, 50)` ，右下角顶点为 `(250, 200)`。
        val rect = RectF(50F, 50F, 250F, 200F)
        // 在画布上绘制Rect对象所描述的矩形，圆角半径为 `25.0F`。
        canvas.drawRoundRect(rect, 25.0F, 25.0F, paint)


        // 等价写法
        // canvas.drawRoundRect(50.0F, 50.0F, 250.0F, 200.0F, 25.0F, 25.0F, paint)

        binding.ivRoundCornerRectangle.setImageBitmap(bmp)
    }

    private fun test_DoubleRoundRectangle() {
        val width = binding.ivDoubleRoundRectangle.width
        val height = binding.ivDoubleRoundRectangle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 外层矩形
        val rectOut = RectF(50F, 50F, 250F, 200F)
        // 内层矩形（应当比外层更小）
        val rectIn = RectF(60F, 60F, 240F, 190F)
        // Android 10新增API：绘制外层矩形减去内层矩形剩余的区域
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            canvas.drawDoubleRoundRect(rectOut, 25.0F, 25.0F, rectIn, 5.0F, 5.0F, paint)


            // 等价写法
            // canvas.drawDoubleRoundRect(rectOut, floatArrayOf(25.0F, 25.0F), rectIn, floatArrayOf(5.0F, 5.0F), paint)
        }

        binding.ivDoubleRoundRectangle.setImageBitmap(bmp)
    }

    private fun test_Circle() {
        val width = binding.ivCircle.width
        val height = binding.ivCircle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 以 `(150, 150)` 为圆心，绘制半径为75像素的圆形。
        canvas.drawCircle(150F, 150F, 75F, paint)

        binding.ivCircle.setImageBitmap(bmp)
    }

    private fun test_Oval() {
        val width = binding.ivOval.width
        val height = binding.ivOval.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 创建RectF对象，描述椭圆区域的外切矩形。
        val rect = RectF(50F, 50F, 250F, 200F)
        canvas.drawOval(rect, paint)


        // 等价写法
        // canvas.drawOval(50.0F, 50.0F, 250.0F, 200.0F, paint)

        binding.ivOval.setImageBitmap(bmp)
    }

    private fun test_Arc() {
        val width = binding.ivArc.width
        val height = binding.ivArc.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 创建RectF对象，描述椭圆区域的外切矩形。
        val rect1 = RectF(50F, 50F, 250F, 200F)
        // 在RectF所指明的区域内绘制扇形，起始角度为0度（矩形水平中线右侧），扫过270度（顺时针方向）。
        canvas.drawArc(rect1, 0.0F, 270.0F, true, paint)


        // 在RectF所指明的区域内绘制扇形，起始角度为0度（矩形水平中线右侧），扫过-90度（逆时针方向）。
        val rect2 = RectF(300F, 50F, 500F, 200F)
        canvas.drawArc(rect2, 0.0F, -90.0F, true, paint)


        // 在RectF所指明的区域内绘制扇形，起始角度为0度（矩形水平中线右侧），扫过-90度，图形不穿过中心点。
        val rect3 = RectF(550F, 50F, 750F, 200F)
        canvas.drawArc(rect3, 0.0F, -90.0F, false, paint)

        binding.ivArc.setImageBitmap(bmp)
    }

    private fun test_PaintStyle() {
        val width = binding.ivStyle.width
        val height = binding.ivStyle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED

        // 画笔样式：填充，默认值，表示仅绘制图形自身，无描边。
        paint.style = Paint.Style.FILL

        val rect1 = Rect(50, 50, 250, 200)
        canvas.drawRect(rect1, paint)


        // 画笔样式：描边，表示仅绘制图形外边框，宽度由 `setStrokeWidth()` 方法设置。
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 25.0F

        val rect2 = Rect(300, 50, 500, 200)
        canvas.drawRect(rect2, paint)


        // 画笔样式：填充与描边，表示绘制图形自身与外边框，边框宽度由 `setStrokeWidth()` 方法设置。
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 25.0F

        val rect3 = Rect(550, 50, 750, 200)
        canvas.drawRect(rect3, paint)

        binding.ivStyle.setImageBitmap(bmp)
    }

    private fun test_StrokeCorner() {
        val width = binding.ivStrokeCorner.width
        val height = binding.ivStrokeCorner.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 25F


        // 线段转角样式：直角，默认值。
        paint.strokeJoin = Paint.Join.MITER

        val rect1 = Rect(50, 50, 250, 200)
        canvas.drawRect(rect1, paint)

        // 线段转角样式：斜角
        paint.strokeJoin = Paint.Join.BEVEL

        val rect2 = Rect(300, 50, 500, 200)
        canvas.drawRect(rect2, paint)

        // 线段转角样式：圆弧
        paint.strokeJoin = Paint.Join.ROUND

        val rect3 = Rect(550, 50, 750, 200)
        canvas.drawRect(rect3, paint)

        binding.ivStrokeCorner.setImageBitmap(bmp)
    }
}
