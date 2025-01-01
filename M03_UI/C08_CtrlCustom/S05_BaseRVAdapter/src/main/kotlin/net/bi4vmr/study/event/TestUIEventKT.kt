package net.bi4vmr.study.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiEventBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseAdapter
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 测试界面：表项点击事件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIEventKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIEventKT::class.java.simpleName}"
    }

    private val binding: TestuiEventBinding by lazy {
        TestuiEventBinding.inflate(LayoutInflater.from(applicationContext))
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
            recyclerview.setLayoutManager(LinearLayoutManager(this@TestUIEventKT))
            recyclerview.setAdapter(adapter)

            // 设置或取消监听器
            tbtnClick.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    adapter.setItemClickListener(ItemClickListener())
                } else {
                    adapter.setItemClickListener(null)
                }
            }
        }
    }

    /**
     * 点击监听器实现类。
     */
    private inner class ItemClickListener : BaseAdapter.ItemClickListener<ListItem> {

        /**
         * 回调方法：表项点击事件。
         *
         * @param[position] 表项位置索引。
         * @param[item] 表项数据。
         * @param[view] 表项视图。
         */
        override fun onItemClick(position: Int, item: ListItem, view: View) {
            Toast.makeText(
                this@TestUIEventKT,
                "点击了第[$position]个表项，内容：${item}",
                Toast.LENGTH_SHORT
            ).show()
        }

        /**
         * 回调方法：表项长按事件。
         *
         * @param[position] 表项位置索引。
         * @param[item] 表项数据。
         * @param[view] 表项视图。
         */
        override fun onItemLongClick(position: Int, item: ListItem, view: View): Boolean {
            Toast.makeText(
                this@TestUIEventKT,
                "长按了第[$position]个表项，内容：${item}",
                Toast.LENGTH_SHORT
            ).show()
            return true
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
