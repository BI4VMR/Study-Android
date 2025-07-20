package net.bi4vmr.study.skills

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiSkillsBinding

/**
 * 测试界面：进阶技巧。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUISkillsKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUISkillsKT::class.java.simpleName}"
    }

    private val binding: TestuiSkillsBinding by lazy {
        TestuiSkillsBinding.inflate(layoutInflater)
    }

    // 获取数据库实例
    private val studentDB: StudentDBKT by lazy {
        StudentDBKT.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            // btnInsertItem.setOnClickListener { testInsertItem() }
            // btnInsertItems.setOnClickListener { testInsertItems() }
            btnUpdate.setOnClickListener { testUpdate() }
            btnDelete.setOnClickListener { testDelete() }
            btnQueryAll.setOnClickListener { testSelectAll() }
        }
    }

    // 插入单条记录
    private fun testInsertItem() {
        binding.tvLog.append("\n--- 插入单条记录 ---\n")
        Log.i(TAG, "--- 插入单条记录 ---")

        runCatching {
            // 获取待操作的数据项ID。
            val rawText: String = binding.etID.text.toString()
            val id: Long = if (rawText.isEmpty()) 0 else rawText.toLong()
            val name = "田所浩二$id"

            // 插入记录
            val student = StudentKT(id, name, 24)
            studentDB.getStudentDAO().insertStudent(student)

            binding.tvLog.append("\n插入成功。")
            Log.i(TAG, "插入成功。")
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 插入多条记录
    private fun testInsertItems() {
        binding.tvLog.append("\n--- 插入多条记录 ---\n")
        Log.i(TAG, "--- 插入多条记录 ---")

        runCatching {
            val datas: MutableList<StudentKT> = mutableListOf()
            for (i in 1..3) {
                // 自动生成ID。
                val id = 0L
                val name = "田所浩二"
                val age = 24

                val student = StudentKT(id, name, age)
                datas.add(student)
            }

            // 插入记录
            studentDB.getStudentDAO().insertStudents(datas)

            binding.tvLog.append("\n插入成功。")
            Log.i(TAG, "插入成功。")
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 更新记录
    private fun testUpdate() {
        binding.tvLog.append("\n--- 更新记录 ---\n")
        Log.i(TAG, "--- 更新记录 ---")

        runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            // 更新记录
            val student = StudentKT(id, "远野", 25)
            studentDB.getStudentDAO().updateStudent(student)

            binding.tvLog.append("\n更新成功。")
            Log.i(TAG, "更新成功。")
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 删除记录
    private fun testDelete() {
        binding.tvLog.append("\n--- 删除记录 ---\n")
        Log.i(TAG, "--- 删除记录 ---")

        runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            // 删除记录
            val student = StudentKT(id)
            studentDB.getStudentDAO().delStudent(student)

            binding.tvLog.append("\n删除成功。")
            Log.i(TAG, "删除成功。")
        }.onFailure {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。")
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。")
            it.printStackTrace()
        }
    }

    // 查询所有记录
    private fun testSelectAll() {
        binding.tvLog.append("\n--- 查询所有记录 ---\n")
        Log.i(TAG, "--- 查询所有记录 ---")

        val result: List<StudentKT> = studentDB.getStudentDAO().getStudent()
        binding.tvLog.append(result.toString())
        Log.i(TAG, result.toString())
    }
}
