package net.bi4vmr.study.diffutil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.databinding.TestuiDiffutilBinding

/**
 * 测试界面：DiffUtil。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIDiffUtilKT : AppCompatActivity() {

    private val binding: TestuiDiffutilBinding by lazy {
        TestuiDiffutilBinding.inflate(layoutInflater)
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
        // 刷新数据集按钮
        binding.btRefresh.setOnClickListener { _ ->
            // 复制一份数据集
            val newDatas: MutableList<ItemVOKT> = adapter.getCopyOfDataSource()

            // 模拟数据变更
            newDatas[2].info = "这是新的备注"
            newDatas[4].title = "这是新的标题"
            newDatas.removeAt(1)
            newDatas.removeAt(5)
            newDatas.add(8, ItemVOKT("新增表项", "新增表项"))

            // 更新列表
            adapter.updateData(newDatas)
        }

        // 重置按钮
        binding.btReset.setOnClickListener { _ ->
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
