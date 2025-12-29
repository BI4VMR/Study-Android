package net.bi4vmr.study

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Test

/**
 * 测试代码。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class CommonTest {
    val mainDispatcher = StandardTestDispatcher()
    private val mutex = Mutex()

    @Test
    fun test() = runTest {
        // A().a()
        // val s = StandardTestDispatcher(testScheduler)
        // val scope = CoroutineScope(s)
        // B(scope).b()
        // advanceUntilIdle()
        Dispatchers.setMain(mainDispatcher)
        val dispatcher = StandardTestDispatcher(testScheduler)
        val scope = CoroutineScope(dispatcher)
        UserManager(scope).b2()
        // testDispatcher.scheduler.advanceUntilIdle()
        mainDispatcher.scheduler.advanceUntilIdle()
        advanceUntilIdle()
        // mainDispatcher.scheduler.advanceUntilIdle()

        println("test function complete!")
    }

    class A {
        fun a() {
            CoroutineScope(Dispatchers.Default).launch {
                delay(10000L)
                println("A.a complete!")
            }
        }
    }

    class UserManager(
        private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    ) {
        fun b() {
            scope.launch {
                delay(10000L)
                println("B.b complete!")
            }
        }

        fun b2() {
            scope.launch {
                delay(10000L)
                withContext(Dispatchers.Main) {
                    println("B.b2 complete in main thread!")
                }
            }
        }
    }

    class UserManager2(
        private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    ) {
        fun b() {
            scope.launch {
                delay(10000L)
                println("B.b complete!")
            }
        }

        fun b2() {
            scope.launch {
                delay(10000L)
                withContext(Dispatchers.Main) {
                    println("B.b2 complete in main thread!")
                }
            }
        }
    }

    fun test111() = runBlocking {
        CoroutineScope(Dispatchers.Default).launch {
            work(1000L)
        }
        CoroutineScope(Dispatchers.IO).launch {
            work(1000L)
        }

        delay(3000L)
    }

    private suspend fun work(delayTime: Long) {
        mutex.withLock {
            println("Work start, will take $delayTime ms, use thread ${Thread.currentThread().name}.")
            delay(delayTime)
            println("Work complete! use thread ${Thread.currentThread().name}.")
        }
    }
}
