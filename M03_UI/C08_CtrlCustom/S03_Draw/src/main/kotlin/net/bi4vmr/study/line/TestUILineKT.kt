package net.bi4vmr.study.line

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import net.bi4vmr.study.databinding.TestuiLineBinding

/**
 * 测试界面：直线。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUILineKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUILineKT::class.java.simpleName}"
    }

    private val binding: TestuiLineBinding by lazy {
        TestuiLineBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.post {
            test_SingleLine()
            test_StrokeCap()
        }
    }

    private fun test_SingleLine() {
        // 创建与ImageView尺寸相同的Bitmap，将其作为画布。
        val width = binding.ivSingleLine.width
        val height = binding.ivSingleLine.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        val p = Paint()
        p.strokeWidth = 10F
        p.strokeCap = Paint.Cap.ROUND
        p.color = Color.GREEN


        canvas.drawLine(50F, 50F, 100F, 100F, p)

        canvas.drawLine(50F, 200F, 400F, 200F, p)

        // 将绘制后的Bitmap设置到ImageView中。
        binding.ivSingleLine.setImageBitmap(bmp)
    }

    private fun test_StrokeCap() {
        // 创建与ImageView尺寸相同的Bitmap，将其作为画布。
        val width = binding.ivStrokeCap.width
        val height = binding.ivStrokeCap.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        val p = Paint()
        p.strokeWidth = 50F
        p.color = Color.CYAN

        // 无
        p.strokeCap = Paint.Cap.BUTT

        canvas.drawLine(50F, 50F, 50F, 400F, p)

        p.strokeCap = Paint.Cap.SQUARE

        canvas.drawLine(150F, 50F, 150F, 400F, p)

        p.strokeCap = Paint.Cap.ROUND

        canvas.drawLine(250F, 50F, 250F, 400F, p)

        // 将绘制后的Bitmap设置到ImageView中。
        binding.ivStrokeCap.setImageBitmap(bmp)
    }
}
