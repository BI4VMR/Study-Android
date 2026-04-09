package net.bi4vmr.study.function

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiFunctionBinding

/**
 * 测试界面：常用方法。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIFunctionKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIFunctionKT::class.java.simpleName
    }

    private val binding: TestuiFunctionBinding by lazy {
        TestuiFunctionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnGetView.setOnClickListener { testGetView() }
            btnCreateView.setOnClickListener { testCreateView() }
            btnCoordinates.setOnClickListener { testCoordinates() }
        }

        testFgAndBg()
    }

    private fun testGetView() {
        val intent = Intent(this, TestUIFunctionGetViewKT::class.java)
        startActivity(intent)
    }

    private fun testCreateView() {
        val intent = Intent(this, TestUIFunctionCreateViewKT::class.java)
        startActivity(intent)
    }

    private fun testCoordinates() {
        Log.i(TAG, "----- 坐标系统 -----")
        appendLog("----- 坐标系统 -----\n")

        // 获取按钮控件的引用
        val button: Button = findViewById(R.id.btn_coordinates)

        // 获取按钮在父容器中的坐标
        val left = button.left
        val top = button.top
        val right = button.right
        val bottom = button.bottom

        Log.i(TAG, "按钮在父容器中的坐标：($left, $top, $right, $bottom)")
        appendLog("按钮在父容器中的坐标：($left, $top, $right, $bottom)")

        // x轴偏移量，初始为0，播放动画后会改变，向右为正，向左为负
        val tx = button.translationX
        val ty = button.translationY

        Log.i(TAG, "变换偏移量：($tx, $ty)")
        appendLog("变换偏移量：($tx, $ty)")

        // 左侧坐标，Left+TranslationX
        val x = button.x
        val y = button.y

        Log.i(TAG, "实际坐标：($x, $y)")
        appendLog("实际坐标：($x, $y)")

        /* 获取控件在当前窗口中的位置 */
        val locationsW = IntArray(2)
        button.getLocationInWindow(locationsW)
        val windowX = locationsW[0]
        val windowY = locationsW[1]

        Log.i(TAG, "Window坐标：($windowX, $windowY)")
        appendLog("Window坐标：($windowX, $windowY)")

        /* 获取控件在当前屏幕中的位置 */
        val location = IntArray(2)
        button.getLocationOnScreen(location)
        val screenX = location[0]
        val screenY = location[1]

        Log.i(TAG, "Screen坐标：($screenX, $screenY)")
        appendLog("Screen坐标：($screenX, $screenY)")
    }

    // 前景与背景
    private fun testFgAndBg() {
        // 设置背景颜色为黑色
        binding.ivFgBg.setBackgroundColor(Color.BLACK)

        // 该方法可以使用一张图片作为背景，图片将自动拉伸与控件大小相匹配。
        // binding.ivFgBg.background = drawable

        // 该方法可以从资源解析图片并作为背景，但不支持主题。
        // binding.ivFgBg.setBackgroundResource(R.drawable.shape_icon_fg)

        // 设置前景为矢量图（蓝色圆圈）
        val fgDrawable = ResourcesCompat.getDrawable(resources, R.drawable.shape_icon_fg, theme)
        binding.ivFgBg.foreground = fgDrawable
        binding.ivFgBg.foregroundGravity = android.view.Gravity.BOTTOM
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: Any) {
        binding.tvLog.apply {
            post { append("\n$text") }
            post {
                runCatching {
                    val offset = layout.getLineTop(lineCount) - height
                    if (offset > 0) {
                        scrollTo(0, offset)
                    }
                }.onFailure { e ->
                    Log.w(TAG, "TextView scroll failed!", e)
                }
            }
        }
    }
}
