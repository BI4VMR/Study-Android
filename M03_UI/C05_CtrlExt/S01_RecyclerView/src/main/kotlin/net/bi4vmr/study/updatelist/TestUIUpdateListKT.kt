package net.bi4vmr.study.updatelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.databinding.TestuiUpdatelistBinding

/**
 * 测试界面：动态更新表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIUpdateListKT : AppCompatActivity() {

    private val binding: TestuiUpdatelistBinding by lazy {
        TestuiUpdatelistBinding.inflate(layoutInflater)
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
        // 新增按钮
        binding.btAdd.setOnClickListener { _ ->
            // 插入一条记录到第3项
            val newItem = SimpleVOKT("这是新增加的表项")
            adapter.addItem(2, newItem)
        }

        // 删除按钮
        binding.btDel.setOnClickListener { _ ->
            // 删除第4项
            adapter.removeItem(4)
        }

        // 更新按钮
        binding.btUpdate.setOnClickListener { _ ->
            // 更新第6项
            val newItem = SimpleVOKT("这是更新后的表项")
            adapter.updateItem(5, newItem)
        }

        // 移动按钮
        binding.btMove.setOnClickListener { _ ->
            // 将当前列表中的第2项移动至5号位置
            adapter.moveItem(2, 5)
        }

        // 重置按钮
        binding.btReload.setOnClickListener { _ ->
            // 重新加载整个列表
            adapter.reloadItems(getTestDatas())
        }
    }

    // 生成测试数据
    private fun getTestDatas(): MutableList<SimpleVOKT> {
        // 制造测试数据
        val datas: MutableList<SimpleVOKT> = mutableListOf()
        for (i in 0..19) {
            datas.add(SimpleVOKT("项目" + (i + 1)))
        }

        return datas
    }
}
