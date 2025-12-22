package net.bi4vmr.study

import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

/**
 * 验证行为。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class VerifyBehaviorTest {

    @Test
    fun test() {
        // 创建监听器的Mock对象
        val mockListener: LogManager.StateCallback = mockk(relaxed = true)

        // 执行业务方法并传入监听器
        LogManager.saveLog(mockListener)

        // 验证行为：日志导出后，监听器中的起始和结束方法都应当被调用。
        verify { mockListener.onStart() }
        verify { mockListener.onEnd(any()) }
    }
}

object LogManager {

    // 业务方法：导出日志
    fun saveLog(callback: StateCallback) {
        // 通知外部监听者操作开始
        callback.onStart()

        // 模拟耗时操作
        Thread.sleep(200L)

        // 通知外部监听者操作结束
        callback.onEnd(200L)
    }

    // 事件监听器
    interface StateCallback {

        // 操作开始
        fun onStart()

        // 操作完成：通知消耗时长
        fun onEnd(time: Long)
    }
}
