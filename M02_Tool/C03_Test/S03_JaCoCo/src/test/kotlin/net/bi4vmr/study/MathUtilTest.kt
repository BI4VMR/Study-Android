package net.bi4vmr.study

import MathUtil
import kotlinx.coroutines.test.runTest
import net.bi4vmr.study.base.Flows
import org.junit.Test


/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MathUtilTest {

    @Test
    fun test() {
        MathUtil.devide(0, 1)
    }

    @Test
    fun test1() = runTest {
        Flows.main1()
        Flows.flow.value =1
        Thread.sleep(1000L) // 等待一段时间以确保协程执行完毕
        Flows.flow.emit(222)
        Thread.sleep(1000L) // 等待一段时间以确保协程执行完毕
    }
}
