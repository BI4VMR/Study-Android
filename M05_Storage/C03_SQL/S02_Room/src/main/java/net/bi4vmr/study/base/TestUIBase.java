package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.List;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;
    private StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 获取数据库实例
        StudentDB studentDB = StudentDB.getInstance(this);
        // 获取学生信息DAO实例
        dao = studentDB.getStudentDAO();

        binding.btnSelectAll.setOnClickListener(v -> testSelectAll());
        binding.btnAdd.setOnClickListener(v -> testAdd());
        binding.btnUpdate.setOnClickListener(v -> testUpdate());
        binding.btnDelete.setOnClickListener(v -> testDelete());
    }

    // 查询所有记录
    private void testSelectAll() {
        binding.tvLog.append("\n--- 查询所有记录 ---\n");
        Log.i(TAG, "--- 查询所有记录 ---");

        List<Student> result = dao.getStudent();
        binding.tvLog.append(result.toString());
        Log.i(TAG, result.toString());
    }

    // 插入记录
    private void testAdd() {
        binding.tvLog.append("\n--- 插入记录 ---\n");
        Log.i(TAG, "--- 插入记录 ---");

        try {
            // 获取待操作的数据项ID
            long id = Long.parseLong(binding.etID.getText().toString());
            String name = "田所浩二" + id;
            // 插入记录
            Student student = new Student(id, name, 24);
            dao.addStudent(student);

            binding.tvLog.append("\n插入成功。");
            Log.i(TAG, "插入成功。");
        } catch (Exception e) {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    // 更新记录
    private void testUpdate() {
        binding.tvLog.append("\n--- 更新记录 ---\n");
        Log.i(TAG, "--- 更新记录 ---");

        try {
            // 获取待操作的数据项ID
            long id = Integer.parseInt(binding.etID.getText().toString());
            // 更新记录
            Student s = new Student(id, "多田野数人", 25);
            dao.updateStudent(s);

            binding.tvLog.append("\n更新成功。");
            Log.i(TAG, "更新成功。");
        } catch (Exception e) {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    // 删除记录
    private void testDelete() {
        binding.tvLog.append("\n--- 删除记录 ---\n");
        Log.i(TAG, "--- 删除记录 ---");

        try {
            // 获取待操作的数据项ID
            long id = Integer.parseInt(binding.etID.getText().toString());
            // 删除记录
            Student student = new Student(id);
            dao.delStudent(student);

            binding.tvLog.append("\n删除成功。");
            Log.i(TAG, "删除成功。");
        } catch (Exception e) {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }
}
