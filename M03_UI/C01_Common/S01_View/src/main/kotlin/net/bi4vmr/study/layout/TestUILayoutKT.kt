package net.bi4vmr.study.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiLayoutBinding

/**
 * 测试界面：布局文件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUILayoutKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUILayoutKT::class.java.simpleName
    }

    private val binding: TestuiLayoutBinding by lazy {
        TestuiLayoutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // /* 点击事件 */
        // // 获取按钮"btnTest"的实例
        // val btnTest: Button = findViewById(R.id.btnTest)
        // // 实现点击监听器并传递给"btnTest"
        // btnTest.setOnClickListener {
        //     Log.i(TAG, "按钮Test被点击了！")
        //     binding.tvLog.append("按钮Test被点击了！\n")
        // }
    }
}
