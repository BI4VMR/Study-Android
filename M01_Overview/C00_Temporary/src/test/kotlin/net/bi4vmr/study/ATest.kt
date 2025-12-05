package net.bi4vmr.study

import android.os.Build
import io.mockk.coJustAwait
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class ATest {

    @Test
    fun test() {
        // 创建Mock对象
        val mockList = mockk<MutableList<Any>>()
        // 设置每次调用时返回的值序列
        val mockResult = listOf(1, 2, 3)
        // 定义行为：当 `mockList` 的 `size` 属性被访问时，依次返回 `mockResult` 列表中的值。
        every { mockResult.size } returnsMany mockResult

        // 多次调用Mock对象的方法并输出结果
        for (i in 1..5) {
            println("第 $i 次调用： Size:[${mockList.size}]")
        }
    }
}
