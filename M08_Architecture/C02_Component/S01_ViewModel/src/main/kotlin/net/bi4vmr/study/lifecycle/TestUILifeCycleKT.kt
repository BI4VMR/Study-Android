package net.bi4vmr.study.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiLifecycleBinding

class TestUILifeCycleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUILifeCycleKT::class.java.simpleName}"
    }

    private val binding: TestuiLifecycleBinding by lazy {
        TestuiLifecycleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 获取当前Activity的MyViewModel2实例
        val vm: MyViewModel2 = ViewModelProvider(this)[MyViewModel2::class.java]
        Log.i(TAG, "Get VM in Activity. ID:[${vm.id}]")

        // 添加Fragment
        binding.btnAdd.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, TestFragmentKT.newInstance())
                .addToBackStack(null)
                .commit()
        }

        // 移除Fragment
        binding.btnRemove.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }
}
