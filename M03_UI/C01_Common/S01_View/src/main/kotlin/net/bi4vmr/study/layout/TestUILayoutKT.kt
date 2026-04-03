package net.bi4vmr.study.layout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 自动加载布局
        setContentView(R.layout.testui_layout)


        // 设置手动加载布局示例界面的跳转事件
        findViewById<Button>(R.id.btn_layout_inflater).setOnClickListener {
            val intent = Intent(this, TestUILayoutInflaterKT::class.java)
            startActivity(intent)
        }
    }
}
