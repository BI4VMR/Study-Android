package net.bi4vmr.study.lifecycle;

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.UUID

/**
 * Name        : MyViewModel2KT
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 21:10
 * <p>
 * Description : ViewModel：基本功能测试。
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
