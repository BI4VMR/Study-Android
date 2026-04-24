package net.bi4vmr.study.text

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import net.bi4vmr.study.databinding.TestuiTextBinding

/**
 * 测试界面：文本。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUITextKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUITextKT::class.java.simpleName}"
    }

    private val binding: TestuiTextBinding by lazy {
        TestuiTextBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.post {
            test_NoCenter()
            test_CenterV()
            test_CenterH()
            testStatic()
        }
    }

    private fun test_NoCenter() {
        val width = binding.ivNoCenter.width
        val height = binding.ivNoCenter.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        val paint = Paint()
        paint.color = Color.BLACK

        // 在 `(50, 50)` 的位置绘制文本
        paint.style = Paint.Style.FILL
        // 字号为25像素
        paint.textSize = 25.0F
        canvas.drawText("fgHIjklMNOpq", 50F, 50F, paint)

        // 在 `(50, 50)` 的位置绘制参考矩形
        val rect = RectF(50F, 50F, 300F, 150F)
        paint.style = Paint.Style.STROKE
        canvas.drawRect(rect, paint)

        binding.ivNoCenter.setImageBitmap(bmp)
    }

    private fun test_CenterV() {
        val width = binding.ivCenterV.width
        val height = binding.ivCenterV.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        val paint = Paint()
        paint.color = Color.BLACK

        // 在 `(50, 50)` 的位置绘制参考矩形
        val rect = RectF(50F, 50F, 300F, 150F)
        paint.style = Paint.Style.STROKE
        canvas.drawRect(rect, paint)

        // 绘制文本
        paint.style = Paint.Style.FILL
        // 设置字号
        paint.textSize = 25.0F
        // 测量当前字号时的文本行高
        val fontMetrics = paint.fontMetrics

        // 计算文本基线在目标区域高度中心位置的偏移量
        val y = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2
        canvas.drawText("fgHIjklMNOpq", 50F, y, paint)

        binding.ivCenterV.setImageBitmap(bmp)
    }

    private fun test_CenterH() {
        val width = binding.ivCenterH.width
        val height = binding.ivCenterH.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        val paint = Paint()
        paint.color = Color.BLACK

        // 在 `(50, 50)` 的位置绘制参考矩形
        val rect = RectF(50F, 50F, 300F, 150F)
        paint.style = Paint.Style.STROKE
        canvas.drawRect(rect, paint)

        // 绘制文本
        paint.style = Paint.Style.FILL
        // 设置字号
        paint.textSize = 25.0F
        // 设置绘制位置相对X坐标水平居中
        paint.textAlign = Paint.Align.CENTER
        // 绘制时X坐标对齐到目标区域水平中心位置
        canvas.drawText("fgHIjklMNOpq", rect.centerX(), 50F, paint)

        binding.ivCenterH.setImageBitmap(bmp)
    }

    private fun testStatic() {
        val width = binding.ivStaticLayout.width
        val height = binding.ivStaticLayout.height
        val bmp = createBitmap(width, height)
        val canvas = Canvas(bmp)

        val textPaint = TextPaint()
        textPaint.color = Color.BLACK
        textPaint.textSize = 32.0F

        val sl =
            StaticLayout("我能够吞下玻璃而不伤身。", textPaint, width, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false)
        sl.draw(canvas)

        binding.ivStaticLayout.setImageBitmap(bmp)
    }
}
