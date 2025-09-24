package net.bi4vmr.study.binding_simple

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBaseBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 测试界面：ViewBinding。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUISimpleBindingKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUISimpleBindingKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(LayoutInflater.from(applicationContext))
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
            val adapter = TestSingleTypeAdapter(dataSource)
            // 配置RecyclerView
            recyclerview.setLayoutManager(LinearLayoutManager(this@TestUISimpleBindingKT))
            recyclerview.setAdapter(adapter)
        }
    }

    private fun initTestData(): MutableList<ListItem> {
        return ArrayList<ListItem>()
            .apply {
                add(ContentVO("表项A", "表项A", R.drawable.ic_funny_256))
                add(ContentVO("表项B", "表项B", R.drawable.ic_funny_256))
                add(ContentVO("表项C", "表项C", R.drawable.ic_funny_256))
                add(ContentVO("表项D", "表项D", R.drawable.ic_funny_256))
                add(ContentVO("表项E", "表项E", R.drawable.ic_funny_256))
            }
    }
}
