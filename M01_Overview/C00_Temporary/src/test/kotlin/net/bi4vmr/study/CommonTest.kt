package net.bi4vmr.study

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test

/**
 * 测试代码。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class CommonTest {

    private val mutex = Mutex()

    @Test
    fun test() = runBlocking {
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
