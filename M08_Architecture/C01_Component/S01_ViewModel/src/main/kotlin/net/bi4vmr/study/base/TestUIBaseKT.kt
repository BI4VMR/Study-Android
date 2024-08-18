package net.bi4vmr.study.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBaseBinding

class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 获取当前Activity的MyViewModel实例
        val vm: MyViewModelKT = ViewModelProvider(this)[MyViewModelKT::class.java]
        Log.i(TAG, "Get VM in Activity. ID:[${vm.id}]")

        // 向VM实例写入数据
        Log.i(TAG, "Set data to Activity's VM. Value:[1000]")
        vm.setNum(1000)

        // 初始化Fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TestFragmentKT.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
