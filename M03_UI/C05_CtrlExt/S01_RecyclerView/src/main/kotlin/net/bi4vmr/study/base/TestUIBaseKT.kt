package net.bi4vmr.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 制造测试数据
        val datas: MutableList<SimpleVOKT> = ArrayList()
        for (i in 0..19) {
            datas.add(SimpleVOKT("项目" + (i + 1)))
        }

        // 设置布局管理器
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvContent.layoutManager = linearLayoutManager
        // 设置适配器
        val adapter = MyAdapterKT(datas)
        binding.rvContent.adapter = adapter
    }
}
