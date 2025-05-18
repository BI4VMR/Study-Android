package net.bi4vmr.study.id.base

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiIdBaseBinding

/**
 * 测试界面：ID - 基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIIDBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIIDBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiIdBaseBinding by lazy {
        TestuiIdBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 根据ID获取控件
        val button1 = findViewById<Button>(R.id.btnTest)
        button1.setOnClickListener {
            Toast.makeText(this@TestUIIDBaseKT, "btnTest被点击了", Toast.LENGTH_SHORT).show()
        }

        // 使用代码设置控件的ID
        val button2 = Button(this)
        button2.id = R.id.btnTest2
        binding.container.addView(button2)

        // 根据ID获取控件
        val button2_ID = findViewById<Button>(R.id.btnTest2)
        button2_ID.setOnClickListener {
            Toast.makeText(this@TestUIIDBaseKT, "btnTest2被点击了", Toast.LENGTH_SHORT).show()
        }
    }
}
