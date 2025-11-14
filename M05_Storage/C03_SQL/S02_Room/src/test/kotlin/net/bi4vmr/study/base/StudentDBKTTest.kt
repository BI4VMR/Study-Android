package net.bi4vmr.study.base

import android.os.Build
import androidx.room.Room
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * [StudentDBKT]功能测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class StudentDBKTTest {

    private lateinit var memoryDB: StudentDBKT

    @Before
    fun setUp() {
        val mockContext = RuntimeEnvironment.getApplication().applicationContext

        // 构建内存数据库
        memoryDB = Room.inMemoryDatabaseBuilder(mockContext, StudentDBKT::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        memoryDB.close()
    }

    @Test
    fun test_AddStudent() {
        println("----- Test AddStudent start -----")

        // 插入测试数据
        val testData1 = StudentKT(1L, "Alice", 18)
        val testData2 = StudentKT(2L, "Bob", 20)
        memoryDB.getStudentDAO().addStudent(testData1)
        memoryDB.getStudentDAO().addStudent(testData2)

        // 验证能否读取先前插入的数据
        val all = memoryDB.getStudentDAO().getStudents()
        all.forEach { println(it) }
        Assert.assertEquals(2, all.size)

        println("----- Test AddStudent end -----")
    }

    @Test
    fun test_DelStudent() {
        println("----- Test DelStudent start -----")

        // 插入测试数据
        val testData1 = StudentKT(1L, "Alice", 18)
        val testData2 = StudentKT(2L, "Bob", 20)
        memoryDB.getStudentDAO().addStudent(testData1)
        memoryDB.getStudentDAO().addStudent(testData2)

        // 删除编号为1的记录
        memoryDB.getStudentDAO().delStudent(testData1)

        // 验证当前记录数量是否正确
        val all = memoryDB.getStudentDAO().getStudents()
        all.forEach { println(it) }
        Assert.assertEquals(1, all.size)

        println("----- Test DelStudent end -----")
    }
}
