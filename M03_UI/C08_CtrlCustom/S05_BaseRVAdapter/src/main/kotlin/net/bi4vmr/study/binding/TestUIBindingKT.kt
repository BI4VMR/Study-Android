package net.bi4vmr.study.binding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBindingBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBindingKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBindingKT::class.java.simpleName}"
    }

    private val binding: TestuiBindingBinding by lazy {
        TestuiBindingBinding.inflate(LayoutInflater.from(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding) {
            // 准备测试数据
            val dataSource: MutableList<ListItem> = initTestData()
            // 创建Adapter
            val adapter = TestMultiTypeAdapter(dataSource)
            // 配置RecyclerView
            recyclerview.setLayoutManager(LinearLayoutManager(this@TestUIBindingKT))
            recyclerview.setAdapter(adapter)

            // 按钮：添加单个 - 末尾
            btnAdd1.setOnClickListener {
                val item = ContentVO("新增表项（末尾）", "新增表项", R.drawable.ic_funny_256)
                adapter.addItem(item)
            }
            // 按钮：添加单个 - 中间
            btnAdd2.setOnClickListener {
                val item = ContentVO("新增表项（中间）", "新增表项", R.drawable.ic_funny_256)
                adapter.addItem(3, item)
            }
            // 按钮：添加一组 - 末尾
            btnAddList1.setOnClickListener {
                val item1 = ContentVO("新增表项1（末尾）", "新增表项", R.drawable.ic_funny_256)
                val item2 = ContentVO("新增表项2（末尾）", "新增表项", R.drawable.ic_funny_256)
                val list = listOf(item1, item2)
                adapter.addItems(list)
            }
            // 按钮：添加一组 - 中间
            btnAddList2.setOnClickListener {
                val item1 = ContentVO("新增表项1（中间）", "新增表项", R.drawable.ic_funny_256)
                val item2 = ContentVO("新增表项2（中间）", "新增表项", R.drawable.ic_funny_256)
                val list = listOf(item1, item2)
                adapter.addItems(1, list)
            }

            // 按钮：删除
            btnRemove.setOnClickListener {
                adapter.removeItem(5)
            }
            // 按钮：清空
            btnClear.setOnClickListener {
                adapter.clearItems()
            }
            // 按钮：更新
            btnUpdate.setOnClickListener {
                when (val item = adapter.getItem(4)) {
                    is TitleVO -> {
                        item.title = "更新后的标题"
                        adapter.updateItem(4, item)
                    }

                    is ContentVO -> {
                        item.title = "更新后的标题"
                        item.info = "更新后的内容"
                        adapter.updateItem(4, item)
                    }

                    else -> {
                        Log.w(TAG, "Item [4] not found!")
                    }
                }
            }
            // 按钮：重置
            btnReload.setOnClickListener {
                adapter.reloadItems(initTestData())
            }
        }
    }

    private fun initTestData(): MutableList<ListItem> {
        return ArrayList<ListItem>()
            .apply {
                add(TitleVO("标题一"))
                add(ContentVO("表项A", "表项A", R.drawable.ic_funny_256))
                add(ContentVO("表项B", "表项B", R.drawable.ic_funny_256))
                add(TitleVO("标题二"))
                add(ContentVO("表项C", "表项C", R.drawable.ic_funny_256))
                add(ContentVO("表项D", "表项D", R.drawable.ic_funny_256))
                add(ContentVO("表项E", "表项E", R.drawable.ic_funny_256))
            }
    }
}
