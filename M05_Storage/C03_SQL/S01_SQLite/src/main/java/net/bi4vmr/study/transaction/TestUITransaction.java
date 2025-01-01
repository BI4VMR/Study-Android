package net.bi4vmr.study.transaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiTransactionBinding;

/**
 * 测试界面：事务支持。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUITransaction extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUITransaction.class.getSimpleName();

    private TestuiTransactionBinding binding;

    private StudentDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 创建学生信息数据库工具类的实例。
        dbHelper = new StudentDBHelper(getApplicationContext());

        binding.btnFailed.setOnClickListener(v -> testFailed());
        binding.btnSuccess.setOnClickListener(v -> testSuccess());
        binding.btnCoroutine.setOnClickListener(v -> testCoroutine());
        binding.btnQueryAll.setOnClickListener(v -> testQuery());
    }

    private void testFailed() {
        Log.i(TAG, "----- 事务执行失败 -----");
        appendLog("\n----- 事务执行失败 -----");

        // 开启事务
        dbHelper.getDB().beginTransaction();

        try {
            // 将1号学生的书本数量加1
            ContentValues values1 = new ContentValues();
            values1.put("book_count", 11);
            dbHelper.getDB().update("student_info", values1, "student_id = 1", null);

            // 模拟业务异常，触发事务回滚。
            int i = 1 / 0;

            // 将2号学生的书本数量减1
            ContentValues values2 = new ContentValues();
            values2.put("book_count", 9);
            dbHelper.getDB().update("student_info", values2, "student_id = 2", null);

            // 标记事务已完成
            dbHelper.getDB().setTransactionSuccessful();
            Log.i(TAG, "操作成功！");
            appendLog("操作成功！");
        } catch (Exception e) {
            Log.e(TAG, "操作失败，事务回滚！", e);
            appendLog("操作失败，事务回滚！");
        } finally {
            // 终止事务
            dbHelper.getDB().endTransaction();
        }
    }

    private void testSuccess() {
        Log.i(TAG, "----- 事务执行成功 -----");
        appendLog("\n----- 事务执行成功 -----");

        // 开启事务
        dbHelper.getDB().beginTransaction();
        try {
            ContentValues values1 = new ContentValues();
            values1.put("book_count", 11);
            dbHelper.getDB().update("student_info", values1, "student_id = 1", null);

            ContentValues values2 = new ContentValues();
            values2.put("book_count", 9);
            dbHelper.getDB().update("student_info", values2, "student_id = 2", null);

            // 标记事务已完成
            dbHelper.getDB().setTransactionSuccessful();
            Log.i(TAG, "操作成功！");
            appendLog("操作成功！");
        } catch (Exception e) {
            Log.e(TAG, "操作失败，事务回滚！", e);
            appendLog("操作失败，事务回滚！");
        } finally {
            // 终止事务
            dbHelper.getDB().endTransaction();
        }
    }

    private void testCoroutine() {
        Log.i(TAG, "----- 事务与协程 -----");
        appendLog("\n----- 事务与协程 -----");

        Log.w(TAG, "Java不支持此方式，请到Kotlin实现中查看。");
        appendLog("Java不支持此方式，请到Kotlin实现中查看。\n");
    }

    private void testQuery() {
        Log.i(TAG, "----- 查询所有记录 -----");
        appendLog("\n----- 查询所有记录 -----");

        Cursor cursor = dbHelper.getDB()
                .query("student_info", null, null, null, null, null, null);
        try (cursor) {
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(0);
                    String name = cursor.getString(1);
                    int bookCount = cursor.getInt(2);

                    // 生成Java对象
                    Student student = new Student(id, name, bookCount);
                    // 显示对象信息
                    Log.i(TAG, student.toString());
                    appendLog(student);
                } while (cursor.moveToNext());
            } else {
                Log.e(TAG, "查询结果为空！");
                appendLog("查询结果为空！");
            }
        } catch (Exception e) {
            Log.e(TAG, "查询失败！", e);
            appendLog("查询失败！");
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
