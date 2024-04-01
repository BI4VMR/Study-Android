package net.bi4vmr.study.base;

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.UUID

/**
 * Name        : MyViewModelKT
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 21:10
 * <p>
 * Description : ViewModel：基本功能测试。
 */
class MyViewModelKT : ViewModel() {

    companion object {
        val TAG: String = "TestApp-${MyViewModelKT::class.java.simpleName}"
    }

    // 实例ID
    var id: String
    // 共享数据
    private var num: Int = 0

    init {
        // 生成随机ID，标识当前实例。
        id = genRandomID()
        Log.i(TAG, "VM created. ID:[$id]")
    }

    // 读取共享数据
    fun getNum(): Int {
        return num
    }

    // 写入共享数据
    fun setNum(num: Int) {
        this.num = num
    }

    // 获取随机ID
    private fun genRandomID(): String {
        return UUID.randomUUID()
            .toString()
            .uppercase()
            .substring(0, 6)
    }
}
