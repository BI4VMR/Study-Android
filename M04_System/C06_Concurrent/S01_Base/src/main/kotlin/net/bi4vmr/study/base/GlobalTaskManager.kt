package net.bi4vmr.study.base

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 进程级任务管理器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
object GlobalTaskManager {

    private val TAG: String = "TestApp-${GlobalTaskManager::class.java.simpleName}"

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun init() {
        ioScope.launch { task() }
    }

    private suspend fun task() {
        try {
            Log.d(TAG, "Task start.")
            delay(5000L)
            Log.d(TAG, "Task end.")
        } catch (e: CancellationException) {
            Log.d(TAG, "Task was canceled!")
        }
    }
}
