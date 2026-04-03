package net.bi4vmr.study.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import net.bi4vmr.study.R

/**
 * 测试界面：手动渲染布局文件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUILayoutInflaterKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUILayoutInflaterKT::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
         * 获取LayoutInflater的三种方法：
         */

        // 通过Context获取LayoutInflater
        val layoutInflater1 = this.getSystemService<LayoutInflater>()
        // 通过Context获取LayoutInflater（简化方法）
        val layoutInflater2 = LayoutInflater.from(this)
        // 通过Activity获取LayoutInflater
        val layoutInflater3 = this.layoutInflater

        // 通过LayoutInflater将布局文件解析为View实例，并设置到窗口中。
        val viewOfWindow: ViewGroup = window.decorView as ViewGroup
        layoutInflater3.inflate(R.layout.testui_layout, viewOfWindow, true)
    }
}
