package net.bi4vmr.study.gotoforresult

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.ActivityResultBinding

class ResultActivityKT : AppCompatActivity() {

    companion object {
        private val TAG: String = ResultActivityKT::class.java.simpleName
    }

    private val binding: ActivityResultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener {
            // 从EditText获取字符
            val input: String = binding.etInfo.text.toString()

            // 封装返回数据
            val intent = Intent()
            intent.putExtra("RESULT", input)

            // 设置返回码和Intent实例
            setResult(999, intent)
            // 关闭当前Activity
            finish()
        }
    }
}
