package net.bi4vmr.study.exceptions

import android.app.Service
import android.content.Intent
import android.os.IBinder
import net.bi4vmr.aidl.IExceptions

/**
 * 示例服务：异常传递测试服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
class ExceptionTestServiceKT : Service() {

    companion object {
        private val TAG: String = "TestApp-Server-${ExceptionTestServiceKT::class.simpleName}"
    }

    override fun onBind(intent: Intent): IBinder {
        return ServiceImpl()
    }

    /**
     * AIDL接口的实现类。
     */
    private inner class ServiceImpl : IExceptions.Stub() {

        // 计算除法
        override fun divide(a: Int, b: Int): Int {
            return a / b
        }

        // 计算除法（已传递原始异常）
        override fun divide2(a: Int, b: Int): Int {
            try {
                return a / b
            } catch (e: Exception) {
                // 捕获AIDL不支持的异常，并重新抛出IllegalArgumentException。
                throw IllegalArgumentException("除数不能为零！", e)
            }
        }
    }
}
