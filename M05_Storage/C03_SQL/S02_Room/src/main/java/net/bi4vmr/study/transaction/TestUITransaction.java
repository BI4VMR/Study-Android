package net.bi4vmr.study.transaction;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiTransactionBinding;

import java.util.List;

/**
 * 测试界面：事务支持。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUITransaction extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUITransaction.class.getSimpleName();

    private TestuiTransactionBinding binding;

    private StudentDAO studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDAO = StudentDB.getInstance(this).getStudentDAO();

        binding.btnAnnotation.setOnClickListener(v -> testAnnotation());
        binding.btnFunction.setOnClickListener(v -> testFunction());
        binding.btnCoroutine.setOnClickListener(v -> testCoroutine());
        binding.btnQueryAll.setOnClickListener(v -> testQueryAll());
    }

    private void testAnnotation() {
        Log.i(TAG, "----- 使用注解编写事务 -----");
        appendLog("\n----- 使用注解编写事务 -----");

        studentDAO.borrowBook();
    }

    private void testFunction() {
        Log.i(TAG, "----- 使用快捷方法编写事务 -----");
        appendLog("\n----- 使用快捷方法编写事务 -----");

        Log.i(TAG, "Java不支持此方式，请到Kotlin实现中查看。");
        appendLog("Java不支持此方式，请到Kotlin实现中查看。");
    }

    private void testCoroutine() {
        Log.i(TAG, "----- 在协程中编写事务 -----");
        appendLog("\n----- 在协程中编写事务 -----");

        Log.i(TAG, "Java不支持此方式，请到Kotlin实现中查看。");
        appendLog("Java不支持此方式，请到Kotlin实现中查看。");
    }

    private void testQueryAll() {
        Log.i(TAG, "----- 查询所有记录 -----");
        appendLog("\n----- 查询所有记录 -----");

        List<Student> result = studentDAO.getStudents();
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
