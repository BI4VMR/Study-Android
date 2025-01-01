package net.bi4vmr.study.skills;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiSkillsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试界面：进阶技巧。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
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
        binding.btnQueryAll.setOnClickListener(v -> testQueryAll());
    }

    private void testInsertItem() {
        Log.i(TAG, "--- 插入单条记录 ---");
        appendLog("\n--- 插入单条记录 ---\n");

        try {
            // 获取待操作的数据项ID
            String rawText = binding.etID.getText().toString();
            // 如果控件未填写数值，则使用自增ID。
            long id = rawText.isEmpty() ? 0L : Long.parseLong(rawText);
            String name = "田所浩二";

            // 插入记录
            Student student = new Student(id, name, 24);
            long rowID = dao.insertStudent(student);

            Log.i(TAG, "插入成功，RowID:[" + rowID + "]");
            appendLog("\n插入成功，RowID:[" + rowID + "]");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    private void testInsertItems() {
        Log.i(TAG, "--- 插入多条记录 ---");
        appendLog("\n--- 插入多条记录 ---\n");

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

            Log.i(TAG, "插入成功，RowIDs:" + rowIDs);
            appendLog("\n插入成功，RowIDs:" + rowIDs);
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    private void testUpdate() {
        Log.i(TAG, "--- 更新记录 ---");
        appendLog("\n--- 更新记录 ---\n");

        try {
            // 获取待操作的数据项ID
            long id = Integer.parseInt(binding.etID.getText().toString());
            // 更新记录
            Student s = new Student(id, "远野", 25);
            dao.updateStudent(s);

            Log.i(TAG, "更新成功。");
            appendLog("\n更新成功。");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    private void testDelete() {
        Log.i(TAG, "--- 删除记录 ---");
        appendLog("\n--- 删除记录 ---\n");

        try {
            // 获取待操作的数据项ID
            long id = Integer.parseInt(binding.etID.getText().toString());
            // 删除记录
            Student student = new Student(id);
            dao.delStudent(student);

            Log.i(TAG, "删除成功。");
            appendLog("\n删除成功。");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    private void testQueryAll() {
        appendLog("\n--- 查询所有记录 ---\n");
        Log.i(TAG, "--- 查询所有记录 ---");

        List<Student> result = dao.getStudent();
        Log.i(TAG, result.toString());
        appendLog(result.toString());
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
