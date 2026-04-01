package net.bi4vmr.study.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiEventBinding

class TestUIEventKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIEventKT::class.java.simpleName
    }

    private val binding: TestuiEventBinding by lazy {
        TestuiEventBinding.inflate(layoutInflater)
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
