package net.bi4vmr.study

import android.os.Build
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import java.io.IOException

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
        val mockDBHelper = mockk<DBHelper>()
        every { mockDBHelper.queryUserName(any<Int>()) } returns "MockUser"
        every { mockDBHelper.queryUserName(1) } returns "Alice"
        every { mockDBHelper.queryUserName(2) } returns "Bob"


        println("QueryUserName of ID=1:[${mockDBHelper.queryUserName(1)}]")
        println("QueryUserName of ID=2:[${mockDBHelper.queryUserName(2)}]")
        println("QueryUserName of ID=3:[${mockDBHelper.queryUserName(3)}]")
    }

    class DBHelper {

        // 根据用户ID查询姓名
        fun queryUserName(id: Int): String = "Real Name"

        // 根据身份证号查询姓名
        fun queryUserName(cardID: String): String = "Real Name"
    }

    fun returns() {
        // 创建Mock对象
        val mockFile = mockk<File>()
        // 定义行为：当 `mockFile` 的 `canonicalPath` 属性被访问时，返回 `/data/file1` 。
        every { mockFile.canonicalPath } returns "/data/file1"

        // 调用Mock对象的 `canonicalPath` 属性并输出结果
        println("File Path:[${mockFile.canonicalPath}]")

        // 修改行为：当 `mockFile` 的 `canonicalPath` 属性被访问时，返回 `/data/file2` 。
        every { mockFile.canonicalPath } returns "/data/file2"

        // 调用Mock对象的 `canonicalPath` 属性并输出结果
        println("File Path:[${mockFile.canonicalPath}]")
    }


    fun returnsMany() {
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

    fun ans() {
        // 创建Mock对象
        val mockFile = mockk<File>()
        // 定义行为：当 `mockFile` 的 `canonicalPath` 属性被访问时，返回 `/data/file1` 。
        every { mockFile.canonicalPath } answers {
            // 输出日志
            println("$mockFile canonicalPath' was called")

            // 此时 `answers {}` 块的最后一行将作为返回值
            "/data/file1"
        }

        // 调用Mock对象的 `canonicalPath` 属性并输出结果
        println("File Path:[${mockFile.canonicalPath}]")
    }

    fun error() {
        // 创建Mock对象
        val mockFile = mockk<File>()
        // 定义行为：当 `mockFile` 的 `canonicalPath` 属性被访问时，抛出异常。
        every { mockFile.canonicalPath } throws IOException("This is mock exception!")

        // 调用Mock对象的 `canonicalPath` 属性并输出结果
        println("File Path:[${mockFile.canonicalPath}]")
    }
}
