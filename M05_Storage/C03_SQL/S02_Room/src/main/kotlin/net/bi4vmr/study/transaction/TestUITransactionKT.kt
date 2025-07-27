package net.bi4vmr.study.transaction

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.withTransaction
import kotlinx.coroutines.runBlocking
import net.bi4vmr.study.databinding.TestuiTransactionBinding

/**
 * 测试界面：事务支持。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUITransactionKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUITransactionKT::class.java.simpleName}"
    }

    private val binding: TestuiTransactionBinding by lazy {
        TestuiTransactionBinding.inflate(layoutInflater)
    }

    // 获取数据库实例
    private val studentDB: StudentDBKT by lazy {
        StudentDBKT.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnAnnotation.setOnClickListener { testAnnotation() }
            btnFunction.setOnClickListener { testFunction() }
            btnCoroutine.setOnClickListener { testCoroutine() }
            btnQueryAll.setOnClickListener { testQueryAll() }
        }
    }

    private fun testAnnotation() {
        Log.i(TAG, "----- 使用注解编写事务 -----")
        appendLog("\n----- 使用注解编写事务 -----")

        studentDB.getStudentDAO().borrowBook()
    }

    private fun testFunction() {
        Log.i(TAG, "----- 使用快捷方法编写事务 -----")
        appendLog("\n----- 使用快捷方法编写事务 -----")

        /*
         * `runInTransaction()` 方法等价于 `beginTransaction()` 等方法的组合调用，但遇到异常回滚时会忽略异常。
         */
        studentDB.runInTransaction {
            val dao = studentDB.getStudentDAO()
            // 将1号学生的书本数量加1
            dao.getStudent(1)?.let {
                it.bookCount += 1
                dao.updateStudent(it)
            }

            // 将2号学生的书本数量减1
            dao.getStudent(2)?.let {
                it.bookCount -= 1
                dao.updateStudent(it)
            }
        }
    }

    private fun testCoroutine() {
        Log.i(TAG, "----- 在协程中编写事务 -----")
        appendLog("\n----- 在协程中编写事务 -----")

        suspend fun transactionInCoroutine() {
            /*
             * `withTransaction()` 方法的作用与 `runInTransaction()` 方法类似，并且能够保证协程调度器总是使用相同的线程执行任务，避
             * 免调度器切换线程导致事务失效。
             */
            studentDB.withTransaction {
                val dao = studentDB.getStudentDAO()
                // 将1号学生的书本数量加1
                dao.getStudent(1)?.let {
                    it.bookCount += 1
                    dao.updateStudent(it)
                }

                // 将2号学生的书本数量减1
                dao.getStudent(2)?.let {
                    it.bookCount -= 1
                    dao.updateStudent(it)
                }
            }
        }

        // 调用包含事务的挂起函数
        runBlocking {
            transactionInCoroutine()
        }
    }

    private fun testQueryAll() {
        Log.i(TAG, "----- 查所有记录 -----")
        appendLog("\n----- 查询所有记录 -----")

        val result: List<StudentKT> = studentDB.getStudentDAO().getStudents()
        result.forEach {
            Log.i(TAG, it.toString())
            appendLog(it)
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
