package net.bi4vmr.study.skills;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiSkillsBinding;

import java.util.ArrayList;
import java.util.List;

public class TestUISkills extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISkills.class.getSimpleName();

    private TestuiSkillsBinding binding;
    private StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSkillsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 获取数据库实例
        StudentDB studentDB = StudentDB.getInstance(this);
        // 获取学生信息DAO实例
        dao = studentDB.getStudentDAO();

        binding.btnInsertItem.setOnClickListener(v -> testInsertItem());
        binding.btnInsertItems.setOnClickListener(v -> testInsertItems());
        binding.btnUpdate.setOnClickListener(v -> testUpdate());
        binding.btnDelete.setOnClickListener(v -> testDelete());
        binding.btnSelectAll.setOnClickListener(v -> testSelectAll());
    }

    // 插入单条记录
    private void testInsertItem() {
        binding.tvLog.append("\n--- 插入单条记录 ---\n");
        Log.i(TAG, "--- 插入单条记录 ---");

        try {
            // 获取待操作的数据项ID
            String rawText = binding.etID.getText().toString();
            // 如果控件未填写数值，则使用自增ID。
            long id = rawText.isEmpty() ? 0L : Long.parseLong(rawText);
            String name = "田所浩二";

            // 插入记录
            Student student = new Student(id, name, 24);
            long rowID = dao.insertStudent(student);

            binding.tvLog.append("\n插入成功，RowID:[" + rowID + "]");
            Log.i(TAG, "插入成功，RowID:[" + rowID + "]");
        } catch (Exception e) {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    // 插入多条记录
    private void testInsertItems() {
        binding.tvLog.append("\n--- 插入多条记录 ---\n");
        Log.i(TAG, "--- 插入多条记录 ---");

        try {
            List<Student> datas = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                // 自动生成ID
                long id = 0;
                String name = "田所浩二";
                int age = 24;

                Student student = new Student(id, name, age);
                datas.add(student);
            }

            // 插入记录
            List<Long> rowIDs = dao.insertStudents(datas);

            binding.tvLog.append("\n插入成功，RowIDs:" + rowIDs);
            Log.i(TAG, "插入成功，RowIDs:" + rowIDs);
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
            Student s = new Student(id, "远野", 25);
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

    // 查询所有记录
    private void testSelectAll() {
        binding.tvLog.append("\n--- 查询所有记录 ---\n");
        Log.i(TAG, "--- 查询所有记录 ---");

        List<Student> result = dao.getStudent();
        binding.tvLog.append(result.toString());
        Log.i(TAG, result.toString());
    }
}
