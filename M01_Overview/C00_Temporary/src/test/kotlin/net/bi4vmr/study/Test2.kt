package net.bi4vmr.study

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class Test2 : RobolectricTest() {

    // @Test
    // fun test() = runTestWithMain {
    //     // UserManager(CoroutineScope(StandardTestDispatcher(testScheduler)))
    //     //     .b2()
    //     // advanceUntilIdle()
    // }

    @Test
    fun test() = runTest {
        // UserManager(CoroutineScope(StandardTestDispatcher(testScheduler)))
        //     .b2()
        val vm = VM()
        vm.flow.value = 1090
        advanceUntilIdle()

        CoroutineScope(Dispatchers.Main).launch{
            delay(5000L)
            println("test function complete!")
        }
    }


    class UserManager(
        private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    ) {
        val flow : MutableStateFlow<Int> = MutableStateFlow(0)
        init {
            scope.launch {
                flow.collect {
                    println("flow value: $it")
                }
            }
        }
        fun b2() {
            scope.launch {
                delay(10000L)
                println("B.b2 working!")
                withContext(Dispatchers.Main) {
                    delay(10000L)
                    println("B.b2 complete in main thread!")
                    flow.value = 1000
                }
            }
        }
    }
}
