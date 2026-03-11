package net.bi4vmr.study.diffutil

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiDiffutilBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 测试界面：DiffUtil。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIDiffUtilKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIDiffUtilKT::class.java.simpleName}"
    }

    private val binding: TestuiDiffutilBinding by lazy {
        TestuiDiffutilBinding.inflate(LayoutInflater.from(applicationContext))
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
            recyclerview.setLayoutManager(LinearLayoutManager(this@TestUIDiffUtilKT))
            recyclerview.setAdapter(adapter)

            // 更新表项
            btnUpdate.setOnClickListener {
                val newData = initTestData()
                newData.removeAt(2)
                newData[4] = ContentVO("表项C（已修改）", "表项C（已修改）", R.drawable.ic_funny_256)
                adapter.submit(newData)
            }
            // 清空表项
            btnClear.setOnClickListener {
                adapter.submit(emptyList())
            }
            // 重置
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
