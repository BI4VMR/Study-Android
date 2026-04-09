package net.bi4vmr.study.function

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiFunctionGetviewBinding

/**
 * 测试界面：常用方法 - 获取控件引用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIFunctionGetViewKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIFunctionGetViewKT::class.java.simpleName
    }

    private val binding: TestuiFunctionGetviewBinding by lazy {
        TestuiFunctionGetviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 获取按钮控件的引用（控件不存在则为空值）
        val tvTitle: TextView? = findViewById(R.id.text)
        // 设置文本颜色
        tvTitle?.setTextColor(Color.GREEN)


        // Android 9(API 28)新增方法：获取控件引用，如果控件不存在则抛出异常：IllegalArgumentException。
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        //     val tvTitle1: TextView = requireViewById(R.id.text)
        //     tvTitle1.setTextColor(Color.GREEN)
        // }


        // 推荐使用ViewBinding取代 `findViewById()` 方法。
        // binding.text.setTextColor(Color.GREEN)
    }
}
