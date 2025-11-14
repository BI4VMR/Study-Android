package net.bi4vmr.study.base;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.List;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;
    private StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 获取数据库实例
        StudentDB studentDB = StudentDB.getInstance(this);
        // 获取学生信息DAO实例
        dao = studentDB.getStudentDAO();

        binding.btnInsert.setOnClickListener(v -> testInsert());
        binding.btnUpdate.setOnClickListener(v -> testUpdate());
        binding.btnDelete.setOnClickListener(v -> testDelete());
        binding.btnQueryAll.setOnClickListener(v -> testQueryAll());
    }

    private void testInsert() {
        Log.i(TAG, "----- 插入记录 -----");
        appendLog("\n----- 插入记录 -----");

        try {
            // 获取待操作的数据项ID
            long id = Long.parseLong(binding.etID.getText().toString());
            String name = "田所浩二";

            // 插入记录
            Student student = new Student(id, name, 24);
            dao.addStudent(student);

            Log.i(TAG, "插入成功。");
            appendLog("插入成功。");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    private void testUpdate() {
        Log.i(TAG, "----- 更新记录 -----");
        appendLog("\n----- 更新记录 -----");

        try {
            // 获取待操作的数据项ID
            long id = Integer.parseInt(binding.etID.getText().toString());

            // 更新记录
            Student s = new Student(id, "远野", 25);
            dao.updateStudent(s);

            Log.i(TAG, "更新成功。");
            appendLog("更新成功。");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    private void testDelete() {
        Log.i(TAG, "----- 删除记录 -----");
        appendLog("\n----- 删除记录 -----");

        try {
            // 获取待操作的数据项ID
            long id = Integer.parseInt(binding.etID.getText().toString());

            // 删除记录（指定ID即可）
            Student student = new Student(id, null, 0);
            dao.delStudent(student);

            Log.i(TAG, "删除成功。");
            appendLog("删除成功。");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    private void testQueryAll() {
        Log.i(TAG, "----- 查询所有记录 -----");
        appendLog("\n----- 查询所有记录 -----");

        List<Student> result = dao.getStudents();
        for (Student student : result) {
            Log.i(TAG, student.toString());
            appendLog(student);
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        binding.tvLog.post(() -> binding.tvLog.append("\n" + text.toString()));
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
