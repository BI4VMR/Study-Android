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
            btnQueryAll.setOnClickListener { testSelectAll() }
        }
    }

    // 插入记录
    private fun testInsert() {
        binding.tvLog.append("\n--- 插入记录 ---\n")
        Log.i(TAG, "--- 插入记录 ---")

        runCatching {
            // 获取待操作的数据项ID
            val id: Long = binding.etID.text.toString().toLong()
            val name = "田所浩二$id"
            // 插入记录
            val student = StudentV2KT(id, name, "2025-01-01")
            studentDB.getStudentDAO().addStudent(student)

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
            val student = StudentV2KT(id, "远野", "2026-01-01")
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
            val student = StudentV2KT(id, "", "")
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

        val result: List<StudentV2KT> = studentDB.getStudentDAO().getStudent()
        binding.tvLog.append(result.toString())
        Log.i(TAG, result.toString())
    }
}
