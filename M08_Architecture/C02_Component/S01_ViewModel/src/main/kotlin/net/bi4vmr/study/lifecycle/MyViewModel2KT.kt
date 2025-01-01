package net.bi4vmr.study.lifecycle;

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.UUID

/**
 * 自定义ViewModel：生命周期测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyViewModel2KT : ViewModel() {

    companion object {
        val TAG: String = "TestApp-${MyViewModel2KT::class.java.simpleName}"
    }

    // 实例ID
    var id: String

    init {
        // 生成随机ID，标识当前实例。
        id = genRandomID()
        Log.i(TAG, "VM created. ID:[$id]")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "OnCleared. ID:[$id]")
    }

    // 获取随机ID
    private fun genRandomID(): String {
        return UUID.randomUUID()
            .toString()
            .uppercase()
            .substring(0, 6)
    }
}
