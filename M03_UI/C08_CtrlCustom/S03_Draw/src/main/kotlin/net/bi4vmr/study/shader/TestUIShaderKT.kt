package net.bi4vmr.study.shader

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.SweepGradient
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import net.bi4vmr.study.databinding.TestuiShaderBinding

/**
 * 测试界面：Shader。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIShaderKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIShaderKT::class.java.simpleName}"
    }

    private val binding: TestuiShaderBinding by lazy {
        TestuiShaderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.post {
            test_Linear()
            test_Sweep()
        }
    }

    private fun test_Linear() {
        val width = binding.ivLinear.width
        val height = binding.ivLinear.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 绘图区域
        val rect = RectF(0F, 0F, width.toFloat(), height.toFloat())

        // 创建线性渐变
        val shader = LinearGradient(
            0F, 0F, width.toFloat(), height.toFloat(),
            intArrayOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()),
            null, Shader.TileMode.CLAMP
        )

        // 创建画笔
        val paint = Paint()
        // 将渐变应用到画笔上
        paint.shader = shader
        // 绘制图形
        canvas.drawRect(rect, paint)

        binding.ivLinear.setImageBitmap(bmp)
    }

    private fun test_Sweep() {
        val width = binding.ivSweep.width
        val height = binding.ivSweep.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 绘图区域
        val rect = RectF(0F, 0F, width.toFloat(), height.toFloat())

        // 创建扇形渐变
        val gradient = SweepGradient(rect.centerX(), rect.centerY(), Color.RED, Color.YELLOW)

        // 创建画笔
        val paint = Paint()
        // 将渐变应用到画笔上
        paint.shader = gradient
        // 绘制图形
        canvas.drawRect(rect, paint)

        binding.ivSweep.setImageBitmap(bmp)
    }
}
