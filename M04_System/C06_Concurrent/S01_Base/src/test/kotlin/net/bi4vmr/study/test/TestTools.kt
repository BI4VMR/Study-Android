package net.bi4vmr.study.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

/**
 * 示例代码：测试工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@OptIn(ExperimentalCoroutinesApi::class)
class TestTools {

    /**
     * 示例四：在本地测试中控制Main调度器。
     *
     * 在本示例中，我们手动控制Main调度器中的任务进度，实现测试线程与Main调度器的协作。
     */
    @Test
    fun test_Main() = runTest {
        // 创建测试调度器
        val mainDispatcher = StandardTestDispatcher()
        // 将测试调度器作为Main调度器
        Dispatchers.setMain(mainDispatcher)

        // 启动依赖Main调度器的协程
        runInMainThread()

        // 推进测试调度器执行所有任务，并挂起测试线程，直到任务完成。
        mainDispatcher.scheduler.advanceUntilIdle()

        // 测试完毕后重置Main调度器
        Dispatchers.resetMain()
    }

    fun runInMainThread() {
        MainScope().launch {
            println("Main thread task start.")
            delay(5000L)
            println("Main thread task end.")
        }
    }
}
