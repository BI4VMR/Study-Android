package net.bi4vmr.study.path

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import net.bi4vmr.study.databinding.TestuiPathBinding

/**
 * 测试界面：路径。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIPathKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIPathKT::class.java.simpleName}"
    }

    private val binding: TestuiPathBinding by lazy {
        TestuiPathBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.post {
            test_Single()
        }
    }

    private fun test_Single() {
        val width = binding.ivLines.width
        val height = binding.ivLines.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // 创建画笔
        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5F

        paint.pathEffect = CornerPathEffect(10F)

        val path = Path()
        path.lineTo(100F, 100F)
        path.lineTo(150F, 100F)
        path.lineTo(200F, 150F)

        canvas.drawPath(path, paint)

        binding.ivLines.setImageBitmap(bmp)
    }
}
