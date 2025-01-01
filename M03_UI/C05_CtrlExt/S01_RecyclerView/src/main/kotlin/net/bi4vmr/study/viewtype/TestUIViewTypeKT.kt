package net.bi4vmr.study.viewtype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：加载多种表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIViewTypeKT : AppCompatActivity() {

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 制造测试数据
        val datas: MutableList<ListItemKT> = ArrayList()
        datas.add(Type1VOKT("项目一", "这是类型I"))
        datas.add(Type1VOKT("项目二", "这是类型I"))
        datas.add(Type2VOKT("这是类型II"))
        datas.add(Type1VOKT("项目三", "这是类型I"))
        datas.add(Type2VOKT("这是类型II"))
        for (i in 1..5) {
            datas.add(Type1VOKT("项目$i"))
        }

        // 设置布局管理器
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvContent.layoutManager = linearLayoutManager
        // 设置适配器
        val adapter = MyAdapterKT(datas)
        binding.rvContent.adapter = adapter
    }
}
