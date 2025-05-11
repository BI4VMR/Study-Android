package net.bi4vmr.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.bi4vmr.study.R

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testui_base)

        // 制造测试数据
        val datas: MutableList<SimpleVOKT> = ArrayList()
        for (i in 0..19) {
            datas.add(SimpleVOKT("项目" + (i + 1)))
        }

        // 获取控件实例
        val recyclerView = findViewById<RecyclerView>(R.id.rvContent)
        // 设置布局管理器
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        // 设置适配器
        val adapter = MyAdapterKT(datas)
        recyclerView.adapter = adapter
    }
}
