package net.bi4vmr.study.updateitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.databinding.TestuiUpdateitemBinding

/**
 * 测试界面：动态更新表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIUpdateItemKT : AppCompatActivity() {

    private val binding: TestuiUpdateitemBinding by lazy {
        TestuiUpdateitemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* 初始化RecyclerView */
        // 设置布局管理器
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvContent.layoutManager = linearLayoutManager
        // 添加分割线
        binding.rvContent.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // 设置适配器
        val adapter = MyAdapterKT(getTestDatas())
        binding.rvContent.adapter = adapter

        /* 初始化按钮 */
        // 改变第二项标题的按钮
        binding.btChange1.setOnClickListener { _ ->
            // 获取原表项
            val oldItem = adapter.getDataSource()[1]
            val newItem = ItemVOKT()
            // 修改标题
            newItem.title = "这是新的标题"
            // 复制未改变变的属性
            newItem.info = oldItem.info

            // 调用局部更新方法，指明需要更新标题。
            adapter.updateItem(1, newItem, UpdateFlagsKT.FLAG_TITLE)
        }

        // 改变第四项的按钮
        binding.btChange2.setOnClickListener { _ ->
            val newItem = ItemVOKT()
            newItem.title = "这是新的标题"
            newItem.info = "这是新的描述"

            // 调用局部更新方法，指明需要更新标题与描述。
            adapter.updateItem(3, newItem, UpdateFlagsKT.FLAG_TITLE or UpdateFlagsKT.FLAG_INFO)
        }

        // 重置按钮
        binding.btReload.setOnClickListener { _ ->
            // 重新加载整个列表
            adapter.reloadItems(getTestDatas())
        }
    }

    // 生成测试数据
    private fun getTestDatas(): MutableList<ItemVOKT> {
        // 制造测试数据
        val datas: MutableList<ItemVOKT> = mutableListOf()
        for (i in 0..19) {
            datas.add(ItemVOKT("项目" + (i + 1)))
        }

        return datas
    }
}
