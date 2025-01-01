package net.bi4vmr.study.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：Kotlin协程。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUICoroutineKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUICoroutineKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnLifeCycle.setOnClickListener { testLifeCycle() }
            btnViewModel.setOnClickListener { testViewModel() }
            btnGlobal.setOnClickListener { testGlobal() }
        }
    }

    // 测试协程
    private suspend fun task() {
        try {
            Log.d(TAG, "Task start.")
            delay(5000L)
            Log.d(TAG, "Task end.")
        } catch (e: CancellationException) {
            Log.d(TAG, "Task was canceled!")
        }
    }

    // LifeCycle协程
    private fun testLifeCycle() {
        Log.i(TAG, "--- LifeCycle协程 ---")
        binding.tvLog.append("\n--- LifeCycle协程 ---\n")

        // 启动协程，并与当前Activity或Fragment的生命周期绑定，当UI组件销毁后自动终止协程。
        lifecycleScope.launch {
            task()
        }

        // 关闭当前页面
        Log.d(TAG, "Finish activity.")
        finish()
    }

    // ViewModel协程
    private fun testViewModel() {
        Log.i(TAG, "--- ViewModel协程 ---")
        binding.tvLog.append("\n--- ViewModel协程 ---\n")

        // 获取当前Activity的MyViewModel实例
        val vm: MyViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        // 调用VM实例中的方法启动协程
        vm.task()

        // 关闭当前页面
        Log.d(TAG, "Finish activity.")
        finish()
    }

    // 进程级协程
    private fun testGlobal() {
        Log.i(TAG, "--- 全局协程 ---")
        binding.tvLog.append("\n--- 全局协程 ---\n")

        // 启动进程级协程，这种协程不会自动取消，并且没有指定调度器，已废弃。
        // GlobalScope.launch {
        //     task()
        // }

        // 启动进程级协程，并指定调度器。
        CoroutineScope(Dispatchers.Default).launch {
            task()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy.")
    }
}
