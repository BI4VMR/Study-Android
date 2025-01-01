package net.bi4vmr.study.base

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnWrite.setOnClickListener { testWrite() }
            btnRead.setOnClickListener { testRead() }
        }
    }

    // 写入数据
    private fun testWrite() {
        Log.i(TAG, "--- 写入数据 ---")
        binding.tvLog.append("\n--- 写入数据 ---\n")

        // 获取SharedPreferences实例
        val sp: SharedPreferences = getSharedPreferences("kvdata", MODE_PRIVATE)
        // 获取Editor实例
        val editor: SharedPreferences.Editor = sp.edit()
        // 存入数据
        editor.putInt("Data_Int", 100)
        editor.putBoolean("Data_Boolean", true)
        editor.putString("Data_String", "我能够吞下玻璃而不伤身。")
        // 提交变更
        editor.apply()

        binding.tvLog.append("数据写入成功，请点击读取按钮查看。")
        Log.i(TAG, "数据写入成功，请点击读取按钮查看。")
    }

    // 读取数据
    private fun testRead() {
        Log.i(TAG, "--- 读取数据 ---")
        binding.tvLog.append("\n--- 读取数据 ---\n")

        // 获取SharedPreferences实例
        val sp: SharedPreferences = getSharedPreferences("kvdata", MODE_PRIVATE)

        // 读取数值
        val i: Int = sp.getInt("Data_Int", 0)
        val b: Boolean = sp.getBoolean("Data_Boolean", false)
        val s: String? = sp.getString("Data_String", "Empty.")

        // 显示内容
        val log = "数字：$i\n布尔值：$b\n字符串：$s"
        binding.tvLog.append(log)
        Log.i(TAG, log)
    }
}
