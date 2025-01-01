package net.bi4vmr.study.gotopage

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiGotopageBinding

class TestUIGotoPageKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIGotoPageKT::class.java.simpleName
    }

    private val binding: TestuiGotopageBinding by lazy {
        TestuiGotopageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGoToInner.setOnClickListener { testGoToInnerActivity() }
        binding.btnGoToOuter.setOnClickListener { testGoToOuterActivity() }
        binding.btnGoToWeb.setOnClickListener { testGoToWeb() }
    }

    // 启动应用内的Activity
    private fun testGoToInnerActivity() {
        Log.i(TAG, "--- 启动应用内的Activity ---")
        binding.tvLog.append("\n--- 启动应用内的Activity ---\n")

        // 创建Intent实例
        val intent = Intent(applicationContext, TestActivityKT::class.java)
        // 传递初始化参数（可选功能）
        intent.putExtra("PARAM_INIT", "测试文本")
        // 启动TestActivity
        startActivity(intent)
    }

    // 启动应用外的Activity
    private fun testGoToOuterActivity() {
        Log.i(TAG, "--- 启动应用外的Activity ---")
        binding.tvLog.append("\n--- 启动应用外的Activity ---\n")

        /*
         * 创建ComponentName实例，用于描述目标界面。
         *
         * ComponentName的构造方法中，第一参数为包名，第二参数为组件名称。
         */
        val cmpName = ComponentName("com.android.settings", "com.android.settings.Settings")
        // 创建Intent实例
        val intent = Intent()
        intent.component = cmpName

        // 外部组件可能并不存在，例如目标APP未安装、系统组件被ROM精简等情况，所以需要使用"try-catch"语句。
        try {
            // 启动默认浏览器
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // 目标应用程序未安装、目标组件没有在其Manifest注册都会造成此错误。
            Log.w(TAG, "未找到目标组件！")
            binding.tvLog.append("未找到目标组件！\n")
            Toast.makeText(this, "未找到目标组件！", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    // 打开一个网页链接
    private fun testGoToWeb() {
        Log.i(TAG, "--- 打开一个网页链接 ---")
        binding.tvLog.append("\n--- 打开一个网页链接 ---\n")

        // 构造URI，指定目标URL地址。
        val uri: Uri = Uri.parse("https://cn.bing.com/")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        try {
            // 启动默认浏览器
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // 目标应用程序未安装、目标组件没有在其Manifest注册都会造成此错误。
            Log.w(TAG, "未找到目标组件！")
            binding.tvLog.append("未找到目标组件！\n")
            Toast.makeText(this, "未找到目标组件！", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
