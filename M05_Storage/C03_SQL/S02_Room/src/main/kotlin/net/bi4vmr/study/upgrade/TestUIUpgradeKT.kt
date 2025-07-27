package net.bi4vmr.study.upgrade

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiUpgradeBinding

/**
 * 测试界面：数据库版本升级。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIUpgradeKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIUpgradeKT::class.java.simpleName}"
    }

    private val binding: TestuiUpgradeBinding by lazy {
        TestuiUpgradeBinding.inflate(layoutInflater)
    }

    // 获取数据库实例
    private val studentDB: StudentDBKT by lazy {
        StudentDBKT.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnInsert.setOnClickListener { testInsert() }
            btnUpdate.setOnClickListener { testUpdate() }
            btnDelete.setOnClickListener { testDelete() }
            btnQueryAll.setOnClickListener { testQueryAll() }
        }
    }

    private fun testInsert() {
        Log.i(TAG, "----- 插入记录 -----")
        appendLog("\n----- 插入记录 -----")

        runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            val name = "田所浩二$id"
            // 插入记录
            val student = StudentV2KT(id, name, "2025-01-01")
            studentDB.getStudentDAO().addStudent(student)

            Log.i(TAG, "插入成功。")
            appendLog("插入成功。")
        }.onFailure { e ->
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e)
            appendLog("操作失败！请检查是否已输入ID或ID冲突。")
        }
    }

    private fun testUpdate() {
        Log.i(TAG, "----- 更新记录 -----")
        appendLog("\n----- 更新记录 -----")

        runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            // 更新记录
            val student = StudentV2KT(id, "远野", "2026-01-01")
            studentDB.getStudentDAO().updateStudent(student)

            Log.i(TAG, "更新成功。")
            appendLog("更新成功。")
        }.onFailure { e ->
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e)
            appendLog("操作失败！请检查是否已输入ID或ID冲突。")
        }
    }

    private fun testDelete() {
        Log.i(TAG, "----- 删除记录 -----")
        appendLog("\n----- 删除记录 -----")

        runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            // 删除记录
            val student = StudentV2KT(id, "", "")
            studentDB.getStudentDAO().delStudent(student)

            Log.i(TAG, "删除成功。")
            appendLog("删除成功。")
        }.onFailure { e ->
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e)
            appendLog("操作失败！请检查是否已输入ID或ID冲突。")
        }
    }

    private fun testQueryAll() {
        Log.i(TAG, "----- 查询所有记录 -----")
        appendLog("\n----- 查询所有记录 -----")

        val result: List<StudentV2KT> = studentDB.getStudentDAO().getStudent()
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
