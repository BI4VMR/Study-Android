package net.bi4vmr.study.base

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIBaseKT::class.java.simpleName
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
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
        Log.i(TAG, "--- 插入记录 ---")
        binding.tvLog.append("\n--- 插入记录 ---\n")

        kotlin.runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            val name = "田所浩二$id"

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            val values = ContentValues()
            values.put("student_id", id)
            values.put("student_name", name)
            values.put("age", 24)

            // 执行插入操作
            dbHelper.getDB().insert("student_info", null, values)
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 更新记录
    private fun testUpdate() {
        Log.i(TAG, "--- 更新记录 ---")
        binding.tvLog.append("\n--- 更新记录 ---\n")

        kotlin.runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            val values = ContentValues()
            values.put("student_id", id)
            values.put("student_name", "德川")
            values.put("age", 25)

            // 执行更新操作
            dbHelper.getDB().update("student_info", values, "student_id = ?", arrayOf("$id"))
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 删除记录
    private fun testDelete() {
        Log.i(TAG, "--- 删除记录 ---")
        binding.tvLog.append("\n--- 删除记录 ---\n")

        kotlin.runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()

            // 执行删除操作
            dbHelper.getDB().delete("student_info", "student_id = ?", arrayOf("$id"))
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 查询所有记录
    private fun testQuery() {
        Log.i(TAG, "--- 查询所有记录 ---")
        binding.tvLog.append("\n--- 查询所有记录 ---\n")

        kotlin.runCatching {
            /*
             * Cursor实例包含查询结果，是一个二维表结构，“游标”指向表中的“行”，我们可以切换游标位置读取各行
             * 的数据。
             */
            val cursor: Cursor = dbHelper.getDB()
                .query("student_info", null, null, null, null, null, null)
            cursor.use {
                /*
                 * 判断游标实例中是否存在数据项。
                 *
                 * "moveToFirst()"方法会将游标移动至第一行。如果该行不存在，返回"false"；如果该行存在，则返回
                 * "true"。
                 */
                if (it.moveToFirst()) {
                    /*
                     * 遍历游标实例，读取所有数据项。
                     *
                     * "moveToNext()"方法会将游标移动至当前位置的后一行。如果该行不存在，返回"false"；如果该行存
                     * 在，则返回"true"。
                     */
                    do {
                        // 根据列索引与类型，读取当前行的属性。
                        val id: Long = it.getLong(0)
                        val name: String = it.getString(1)
                        val age: Int = it.getInt(2)

                        // 生成Kotlin对象
                        val studentKT = StudentKT(id, name, age)
                        // 显示对象信息
                        binding.tvLog.append("\n$studentKT")
                        Log.i(TAG, studentKT.toString())
                    } while (it.moveToNext())
                } else {
                    binding.tvLog.append("\n查询结果为空！")
                    Log.e(TAG, "查询结果为空！")
                }
            }
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }
}
