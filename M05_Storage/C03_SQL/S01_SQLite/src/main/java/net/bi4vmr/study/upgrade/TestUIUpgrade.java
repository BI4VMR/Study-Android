package net.bi4vmr.study.upgrade;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiUpgradeBinding;

public class TestUIUpgrade extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIUpgrade.class.getSimpleName();

    private TestuiUpgradeBinding binding;

    private StudentDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiUpgradeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 创建学生信息数据库工具类的实例。
        dbHelper = new StudentDBHelper(getApplicationContext());

        binding.btnInsert.setOnClickListener(v -> testInsert());
        binding.btnUpdate.setOnClickListener(v -> testUpdate());
        binding.btnDelete.setOnClickListener(v -> testDelete());
        binding.btnQueryAll.setOnClickListener(v -> testQuery());
    }

    // 插入记录
    private void testInsert() {
        Log.i(TAG, "----- 插入记录 -----");
        appendLog("\n----- 插入记录 -----");

        try {
            // 获取待操作的数据项ID。
            String rawText = binding.etID.getText().toString();
            long id = rawText.isEmpty() ? 0 : Long.parseLong(rawText);
            String name = "田所浩二" + id;

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            ContentValues values = new ContentValues();
            values.put("student_id", id);
            values.put("student_name", name);
            values.put("birthday", "2024-01-01");

            // 执行插入操作
            dbHelper.getDB().insert("student_info", null, values);
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    // 更新记录
    private void testUpdate() {
        Log.i(TAG, "----- 更新记录 -----");
        appendLog("\n----- 更新记录 -----");

        try {
            // 获取待操作的数据项ID
            long id = Long.parseLong(binding.etID.getText().toString());

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            ContentValues values = new ContentValues();
            values.put("student_name", "德川");
            values.put("birthday", "2025-01-01");

            // 执行更新操作
            dbHelper.getDB().update("student_info", values, "student_id = ?", new String[]{id + ""});
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    // 删除记录
    private void testDelete() {
        Log.i(TAG, "----- 删除记录 -----");
        appendLog("\n----- 删除记录 -----");

        try {
            // 获取待操作的数据项ID
            long id = Long.parseLong(binding.etID.getText().toString());

            // 执行删除操作
            dbHelper.getDB().delete("student_info", "student_id = ?", new String[]{id + ""});
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("\n操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    // 查询所有记录
    private void testQuery() {
        Log.i(TAG, "----- 查询所有记录 -----");
        appendLog("\n----- 查询所有记录 -----");

        Cursor cursor = dbHelper.getDB()
                .query("student_info", null, null, null, null, null, null);
        try (cursor) {
            if (cursor.moveToFirst()) {
                do {
                    // 根据列索引与类型，读取当前行的属性。
                    long id = cursor.getLong(0);
                    String name = cursor.getString(1);
                    String birthday = cursor.getString(2);

                    // 生成Java对象
                    StudentV2 student = new StudentV2(id, name, birthday);
                    // 显示对象信息
                    appendLog("\n" + student);
                    Log.i(TAG, student.toString());
                } while (cursor.moveToNext());
            } else {
                Log.e(TAG, "查询结果为空！");
                appendLog("\n查询结果为空！");
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
