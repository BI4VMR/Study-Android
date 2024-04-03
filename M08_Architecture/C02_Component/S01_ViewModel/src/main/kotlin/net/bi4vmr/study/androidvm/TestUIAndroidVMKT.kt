package net.bi4vmr.study.androidvm;

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import net.bi4vmr.study.databinding.TestuiAndroidvmBinding

class TestUIAndroidVMKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIAndroidVMKT::class.java.simpleName}"
    }

    private val vm: AndroidVMKT by lazy {
        // 获取AndroidViewModel实例
        ViewModelProvider(this)[AndroidVMKT::class.java]
    }

    private val binding: TestuiAndroidvmBinding by lazy {
        TestuiAndroidvmBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 使用AndroidViewModel
        val context: Context = vm.getApplication<Application>().applicationContext
        Log.i(TAG, "Get APPContext: $context")
    }
}
