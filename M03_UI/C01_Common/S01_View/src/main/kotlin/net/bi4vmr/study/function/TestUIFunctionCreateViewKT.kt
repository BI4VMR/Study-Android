package net.bi4vmr.study.function

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiFunctionCreateviewBinding

/**
 * 测试界面：常用方法 - 创建控件实例。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIFunctionCreateViewKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIFunctionCreateViewKT::class.java.simpleName
    }

    private val binding: TestuiFunctionCreateviewBinding by lazy {
        TestuiFunctionCreateviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 创建TextView实例
        val textview = TextView(this)
        // 设置文本内容
        textview.text = "这是一个动态创建的TextView。"
        // 设置文本颜色
        textview.setTextColor(Color.RED)
        // 设置背景颜色
        textview.setBackgroundColor(Color.CYAN)

        // 创建LayoutParams实例，指定宽高和外边距。
        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        lp.marginStart = 100

        // 将TextView添加到容器中
        val layout: FrameLayout = findViewById(R.id.container)
        layout.addView(textview, lp)
    }

    override fun onResume() {
        super.onResume()
    }
}
