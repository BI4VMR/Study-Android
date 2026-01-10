package net.bi4vmr.study.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

/**
 * 测试ViewModel。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class MyViewModel : ViewModel() {

    companion object {
        private val TAG: String = "TestApp-${MyViewModel::class.java.simpleName}"
    }

    // 测试协程
    fun task() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Task start.")
                delay(5000L)
                Log.d(TAG, "Task end.")
            } catch (e: CancellationException) {
                Log.d(TAG, "Task was canceled!")
            }
        }
    }

    override fun onCleared() {
        Log.d(TAG, "OnCleared.")
    }
}
