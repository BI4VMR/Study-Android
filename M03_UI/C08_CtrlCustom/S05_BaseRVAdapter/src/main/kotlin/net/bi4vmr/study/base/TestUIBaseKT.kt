package net.bi4vmr.study.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBaseBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseAdapter
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
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
            val dataSource: MutableList<ListItem> = ArrayList<ListItem>()
                .apply {
                    add(TitleVO("标题一"))
                    add(ContentVO("表项A", "表项A", R.drawable.ic_funny_256))
                    add(ContentVO("表项B", "表项B", R.drawable.ic_funny_256))
                    add(ContentVO("表项C", "表项C", R.drawable.ic_funny_256))
                    add(TitleVO("标题二"))
                    add(ContentVO("表项D", "表项D", R.drawable.ic_funny_256))
                    add(ContentVO("表项E", "表项E", R.drawable.ic_funny_256))
                }
            val adapter = TestMultiTypeAdapter(dataSource)
            adapter.setUIEventListener(object : BaseAdapter.UIEventListener {
                override fun onItemClick(position: Int, item: ListItem) {
                    Toast.makeText(
                        this@TestUIBaseKT,
                        "点击了第${position}个表项，内容：${item}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onItemLongClick(position: Int, item: ListItem): Boolean {
                    Toast.makeText(
                        this@TestUIBaseKT,
                        "长按了第${position}个表项，内容：${item}",
                        Toast.LENGTH_SHORT
                    ).show()
                    return super.onItemLongClick(position, item)
                }
            })

            recyclerview.setLayoutManager(LinearLayoutManager(this@TestUIBaseKT))
            recyclerview.setAdapter(adapter)
        }
    }
}
