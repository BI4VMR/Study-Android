package net.bi4vmr.study.transaction

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import net.bi4vmr.study.databinding.TestuiTransactionBinding

/**
 * 测试界面：事务支持。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@OptIn(ExperimentalCoroutinesApi::class)
class TestUITransactionKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUITransactionKT::class.java.simpleName
    }

    private val binding: TestuiTransactionBinding by lazy {
        TestuiTransactionBinding.inflate(layoutInflater)
    }

    private val dbHelper: StudentDBHelperKT by lazy {
        StudentDBHelperKT(applicationContext)
    }

    private val mutex = Mutex()
    private val singleDispatcher = Dispatchers.IO.limitedParallelism(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnFailed.setOnClickListener { testFailed() }
            btnSuccess.setOnClickListener { testSuccess() }
            btnCoroutine.setOnClickListener { testCoroutine() }
            btnQueryAll.setOnClickListener { testQuery() }
        }
    }

    private fun testFailed() {
        Log.i(TAG, "----- 事务执行失败 -----")
        appendLog("\n----- 事务执行失败 -----")

        // 开启事务
        dbHelper.getDB().beginTransaction()

        try {
            // 将1号学生的书本数量加1
            val values1 = ContentValues()
            values1.put("book_count", 11)
            dbHelper.getDB().update("student_info", values1, "student_id = 1", null)

            // 模拟业务异常，触发事务回滚。
            1 / 0

            // 将2号学生的书本数量减1
            val values2 = ContentValues()
            values2.put("book_count", 9)
            dbHelper.getDB().update("student_info", values2, "student_id = 2", null)

            // 标记事务已完成
            dbHelper.getDB().setTransactionSuccessful()
            Log.i(TAG, "操作成功！")
            appendLog("操作成功！")
        } catch (e: Exception) {
            Log.e(TAG, "操作失败，事务回滚！", e)
            appendLog("操作失败，事务回滚！")
        } finally {
            // 终止事务
            dbHelper.getDB().endTransaction()
        }
    }

    private fun testSuccess() {
        Log.i(TAG, "----- 事务执行成功 -----")
        appendLog("\n----- 事务执行成功 -----")

        // 开启事务
        dbHelper.getDB().beginTransaction()

        try {
            // 将1号学生的书本数量加1
            val values1 = ContentValues()
            values1.put("book_count", 11)
            dbHelper.getDB().update("student_info", values1, "student_id = 1", null)

            // 将2号学生的书本数量减1
            val values2 = ContentValues()
            values2.put("book_count", 9)
            dbHelper.getDB().update("student_info", values2, "student_id = 2", null)

            // 标记事务已完成
            dbHelper.getDB().setTransactionSuccessful()
            Log.i(TAG, "操作成功！")
            appendLog("操作成功！")
        } catch (e: Exception) {
            Log.e(TAG, "操作失败，事务回滚！", e)
            appendLog("操作失败，事务回滚！")
        } finally {
            // 终止事务
            dbHelper.getDB().endTransaction()
        }
    }

    private fun testCoroutine() {
        Log.i(TAG, "----- 事务与协程 -----")
        appendLog("\n----- 事务与协程 -----")

        transaction(1)
        transaction(2)
        transaction(3)
    }

    private fun transaction(num: Int) {
        // 使用单线程调度器
        CoroutineScope(singleDispatcher).launch {
            // 添加互斥锁，确保线程安全。
            mutex.withLock {
                Log.i(TAG, "$num 号事务开始，工作线程：${Thread.currentThread().name}。")
                appendLog("$num 号事务开始，工作线程：${Thread.currentThread().name}。")

                dbHelper.getDB().beginTransaction()
                try {
                    // 模拟耗时操作
                    delay(3000L)
                    dbHelper.getDB().setTransactionSuccessful()
                } finally {
                    dbHelper.getDB().endTransaction()
                }

                Log.i(TAG, "$num 号事务结束，工作线程：${Thread.currentThread().name}。")
                appendLog("$num 号事务结束，工作线程：${Thread.currentThread().name}。")
            }
        }
    }

    private fun testQuery() {
        Log.i(TAG, "----- 查询所有记录 -----")
        appendLog("\n----- 查询所有记录 -----")

        val cursor: Cursor = dbHelper.getDB()
            .query("student_info", null, null, null, null, null, null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    // 根据列索引与类型，读取当前行的属性。
                    val id: Long = it.getLong(0)
                    val name: String = it.getString(1)
                    val bookCount: Int = it.getInt(2)

                    // 生成Kotlin对象
                    val student = StudentKT(id, name, bookCount)
                    // 显示对象信息
                    Log.i(TAG, student.toString())
                    appendLog(student)
                } while (it.moveToNext())
            } else {
                Log.e(TAG, "查询结果为空！")
                appendLog("查询结果为空！")
            }
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: Any) {
        binding.tvLog.apply {
            post { append("\n$text") }
            post {
                runCatching {
                    val offset = layout.getLineTop(lineCount) - height
                    if (offset > 0) {
                        scrollTo(0, offset)
                    }
                }.onFailure { e ->
                    Log.w(TAG, "TextView scroll failed!", e)
                }
            }
        }
    }
}
