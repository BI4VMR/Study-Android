package net.bi4vmr.study.base

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBaseBinding

class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIBaseKT::class.java.simpleName
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
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
