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

    private StudentDBHelper2 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 创建学生信息数据库工具类的实例。
        dbHelper = new StudentDBHelper2(getApplicationContext());

        binding.btnFailed.setOnClickListener(v -> testFailed());
        binding.btnSuccess.setOnClickListener(v -> testSuccess());
        binding.btnQueryAll.setOnClickListener(v -> testQuery());
    }

    private void testFailed() {
        Log.i(TAG, "--- 事务执行失败 ---");
        appendLog("\n--- 事务执行失败 ---\n");

        // 开启事务
        dbHelper.getDB().beginTransaction();

        try {
            // 将1号学生的书本数量加1
            ContentValues values1 = new ContentValues();
            values1.put("book_count", 11);
            dbHelper.getDB().update("student_info", values1, "student_id = 1", null);

            // 模拟异常，触发事务回滚。
            raiseException();

            // 将2号学生的书本数量减1
            ContentValues values2 = new ContentValues();
            values1.put("book_count", 9);
            dbHelper.getDB().update("student_info", values2, "student_id = 2", null);

            // 标记事务已完成
            dbHelper.getDB().setTransactionSuccessful();
            Log.i(TAG, "操作成功！");
            appendLog("\n操作成功！");
        } catch (Exception e) {
            Log.e(TAG, "操作失败，事务回滚！");
            appendLog("\n操作失败，事务回滚！");
            e.printStackTrace();
        } finally {
            // 终止事务
            dbHelper.getDB().endTransaction();
        }
    }

    private void raiseException() throws Exception {
        throw new Exception("模拟异常");
    }

    private void testSuccess() {
        Log.i(TAG, "--- 事务执行成功 ---");
        appendLog("\n--- 事务执行成功 ---\n");

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
            appendLog("\n操作成功！");
        } catch (Exception e) {
            Log.e(TAG, "操作失败，事务回滚！");
            appendLog("\n操作失败，事务回滚！");
            e.printStackTrace();
        } finally {
            // 终止事务
            dbHelper.getDB().endTransaction();
        }
    }

    // 查询所有记录
    private void testQuery() {
        Log.i(TAG, "--- 查询所有记录 ---");
        appendLog("\n--- 查询所有记录 ---\n");

        Cursor cursor = dbHelper.getDB()
                .query("student_info", null, null, null, null, null, null);
        try (cursor) {
            if (cursor.moveToFirst()) {
                do {
                    // 根据列索引与类型，读取当前行的属性。
                    long id = cursor.getLong(0);
                    String name = cursor.getString(1);
                    int bookCount = cursor.getInt(2);

                    // 生成Java对象
                    Student2 student = new Student2(id, name, bookCount);
                    // 显示对象信息
                    Log.i(TAG, student.toString());
                    appendLog("\n" + student);
                } while (cursor.moveToNext());
            } else {
                Log.e(TAG, "查询结果为空！");
                appendLog("\n查询结果为空！");
            }
        } catch (Exception e) {
            Log.e(TAG, "查询失败！");
            appendLog("\n查询失败！");
            e.printStackTrace();
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(CharSequence text) {
        binding.tvLog.append(text);
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
