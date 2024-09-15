package net.bi4vmr.study.upgrade;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
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

        // 创建学生信息数据库工具类的实例。
        dbHelper = new StudentDBHelper(getApplicationContext());

        binding.btnInsert.setOnClickListener(v -> testInsert());
        binding.btnUpdate.setOnClickListener(v -> testUpdate());
        binding.btnDelete.setOnClickListener(v -> testDelete());
        binding.btnQueryAll.setOnClickListener(v -> testQuery());
    }

    // 插入记录
    private void testInsert() {
        Log.i(TAG, "--- 插入记录 ---");
        binding.tvLog.append("\n--- 插入记录 ---\n");

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
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    // 更新记录
    private void testUpdate() {
        Log.i(TAG, "--- 更新记录 ---");
        binding.tvLog.append("\n--- 更新记录 ---\n");

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
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    // 删除记录
    private void testDelete() {
        Log.i(TAG, "--- 删除记录 ---");
        binding.tvLog.append("\n--- 删除记录 ---\n");

        try {
            // 获取待操作的数据项ID
            long id = Long.parseLong(binding.etID.getText().toString());

            // 执行删除操作
            dbHelper.getDB().delete("student_info", "student_id = ?", new String[]{id + ""});
        } catch (Exception e) {
            binding.tvLog.append("\n操作失败！请检查是否已输入ID或ID冲突。");
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。");
            e.printStackTrace();
        }
    }

    // 查询所有记录
    private void testQuery() {
        Log.i(TAG, "--- 查询所有记录 ---");
        binding.tvLog.append("\n--- 查询所有记录 ---\n");

        Cursor cursor = dbHelper.getDB()
                .query("student_info", null, null, null, null, null, null);
        try (cursor) {
            if (cursor.moveToFirst()) {
                do {
                    // 根据列索引与类型，读取当前行的属性。
                    long id = cursor.getLong(0);
                    String name = cursor.getString(1);
                    String birthday = cursor.getString(2);

                    // 生成Java对象。
                    StudentV2 student = new StudentV2(id, name, birthday);
                    // 显示对象信息。
                    binding.tvLog.append("\n" + student);
                    Log.i(TAG, student.toString());
                } while (cursor.moveToNext());
            } else {
                binding.tvLog.append("\n查询结果为空！");
                Log.e(TAG, "查询结果为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
