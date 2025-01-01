package net.bi4vmr.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R

class TestUIBaseKT : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置视图内容为布局文件"testui_base.xml"
        setContentView(R.layout.testui_base)
    }
}
