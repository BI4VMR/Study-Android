package net.bi4vmr.study.upgrade

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiUpgradeBinding

class TestUIUpgradeKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIUpgradeKT::class.java.simpleName
    }

    private val binding: TestuiUpgradeBinding by lazy {
        TestuiUpgradeBinding.inflate(layoutInflater)
    }

    private val dbHelper: StudentDBHelperKT by lazy {
        StudentDBHelperKT(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnInsert.setOnClickListener { testInsert() }
            btnUpdate.setOnClickListener { testUpdate() }
            btnDelete.setOnClickListener { testDelete() }
            btnQueryAll.setOnClickListener { testQuery() }
        }
    }

    // 插入记录
    private fun testInsert() {
        Log.i(TAG, "----- 插入记录 -----")
        appendLog("\n----- 插入记录 -----")

        runCatching {
            // 获取待操作的数据项ID。
            val rawText: String = binding.etID.text.toString()
            val id: Long = if (rawText.isEmpty()) 0 else rawText.toLong()
            val name = "田所浩二$id"

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            val values = ContentValues()
            if (id != 0L) {
                values.put("student_id", id)
            }
            values.put("student_name", name)
            values.put("birthday", "2025-01-01")

            // 执行插入操作。
            dbHelper.getDB().insert("student_info", null, values)
        }.onFailure { e ->
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e)
            appendLog("操作失败！请检查是否已输入ID或ID冲突。")
        }
    }

    // 更新记录
    private fun testUpdate() {
        Log.i(TAG, "----- 更新记录 -----")
        appendLog("\n----- 更新记录 -----")

        runCatching {
            // 获取待操作的数据项ID。
            val id: Long = binding.etID.text.toString().toLong()

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            val values = ContentValues()
            values.put("student_name", "德川")
            values.put("age", 25)

            // 执行更新操作
            dbHelper.getDB().update("student_info", values, "student_id = ?", arrayOf("$id"))
        }.onFailure { e ->
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e)
            appendLog("操作失败！请检查是否已输入ID或ID冲突。")
        }
    }

    // 删除记录
    private fun testDelete() {
        Log.i(TAG, "----- 删除记录 -----")
        appendLog("\n----- 删除记录 -----")

        runCatching {
            // 获取待操作的数据项ID。
            val id: Long = binding.etID.text.toString().toLong()

            // 执行删除操作。
            dbHelper.getDB().delete("student_info", "student_id = ?", arrayOf("$id"))
        }.onFailure { e ->
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e)
            appendLog("操作失败！请检查是否已输入ID或ID冲突。")
        }
    }

    // 查询所有记录
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
                    val birthday: String = it.getString(2)

                    // 生成Kotlin对象
                    val student = StudentV2KT(id, name, birthday)
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
