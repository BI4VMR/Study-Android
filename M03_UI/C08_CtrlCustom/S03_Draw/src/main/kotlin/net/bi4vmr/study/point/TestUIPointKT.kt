package net.bi4vmr.study.point

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import net.bi4vmr.study.databinding.TestuiPointBinding

/**
 * 测试界面：点。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIPointKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIPointKT::class.java.simpleName}"
    }

    private val binding: TestuiPointBinding by lazy {
        TestuiPointBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.post {
            test_Single()
            test_Batch()
        }
    }

    private fun test_Single() {
        val width = binding.ivSingle.width
        val height = binding.ivSingle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔并设置样式：尺寸为5像素，颜色为红色。
        val paint = Paint()
        paint.strokeWidth = 10F
        paint.color = Color.RED

        // 在画布 `(50, 50)` 的位置绘制一个点
        canvas.drawPoint(50F, 50F, paint)
        // 在画布 `(100, 100)` 的位置绘制一个点
        canvas.drawPoint(100F, 100F, paint)

        binding.ivSingle.setImageBitmap(bmp)
    }

    private fun test_Batch() {
        val width = binding.ivSingle.width
        val height = binding.ivSingle.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        val p = Paint()
        p.strokeWidth = 10F
        p.color = Color.GREEN

        // 使用数组表示一批点，每两个元素为一组，分别表示点的X坐标与Y坐标。
        val points = floatArrayOf(50F, 50F, 100F, 100F, 50F, 100F, 100F, 50F)
        canvas.drawPoints(points, p)

        binding.ivBatch.setImageBitmap(bmp)
    }
}
