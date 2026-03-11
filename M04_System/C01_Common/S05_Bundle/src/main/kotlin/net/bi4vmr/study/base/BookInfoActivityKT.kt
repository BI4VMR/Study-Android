package net.bi4vmr.study.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.ActivityBookinfoBinding

/**
 * 测试界面：书籍信息界面。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class BookInfoActivityKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${BookInfoActivityKT::class.java.simpleName}"
    }

    private val binding: ActivityBookinfoBinding by lazy {
        ActivityBookinfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 从Intent中取出Bundle对象
        val bundle: Bundle? = intent.extras
        bundle?.let {
            // 使用Key取出对应的值
            val id: String? = it.getString("KEY_ID")
            val name: String? = it.getString("KEY_NAME")
            val price: Float = bundle.getFloat("KEY_PRICE", -1F)
            val isSoldout: Boolean = bundle.getBoolean("KEY_SOLDOUT", true)

            val bookInfo = "ID:$id\n名称:$name\n价格:$price\n售空:$isSoldout"
            Log.d(TAG, bookInfo)
            binding.tvInfo.text = bookInfo
        }
    }
}
