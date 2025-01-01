package net.bi4vmr.study.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    private StudentDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 创建学生信息数据库工具类的实例。
        dbHelper = new StudentDBHelper(getApplicationContext());

        binding.btnInsert.setOnClickListener(v -> testInsert());
        binding.btnUpdate.setOnClickListener(v -> testUpdate());
        binding.btnDelete.setOnClickListener(v -> testDelete());
        binding.btnQueryAll.setOnClickListener(v -> testQuery());
    }

    private void testInsert() {
        Log.i(TAG, "----- 插入记录 -----");
        appendLog("\n----- 插入记录 -----");

        try {
            // 获取待操作的数据项ID
            long id = Long.parseLong(binding.etID.getText().toString());
            String name = "田所浩二" + id;

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            ContentValues values = new ContentValues();
            values.put("student_id", id);
            values.put("student_name", name);
            values.put("age", 24);

            // 执行插入操作
            long rawID = dbHelper.getDB().insert("student_info", null, values);
            // 显示新表项的RowID
            Log.i(TAG, "插入成功。 RawID:[" + rawID + "]");
            appendLog("插入成功。 RawID:[" + rawID + "]");
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
            long id = Long.parseLong(binding.etID.getText().toString());

            // 创建ContentValues实例，组织一条记录的各个字段与值。
            ContentValues values = new ContentValues();
            values.put("student_name", "德川");
            values.put("age", 25);

            // 执行更新操作
            int lines = dbHelper.getDB().update("student_info", values, "student_id = ?", new String[]{id + ""});
            // 显示受影响的行数
            Log.i(TAG, "更新成功。 Lines:[" + lines + "]");
            appendLog("更新成功。 Lines:[" + lines + "]");
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
            long id = Long.parseLong(binding.etID.getText().toString());

            // 执行删除操作
            int lines = dbHelper.getDB().delete("student_info", "student_id = ?", new String[]{id + ""});
            // 显示受影响的行数
            Log.i(TAG, "删除成功。 Lines:[" + lines + "]");
            appendLog("删除成功。 Lines:[" + lines + "]");
        } catch (Exception e) {
            Log.e(TAG, "操作失败！请检查是否已输入ID或ID冲突。", e);
            appendLog("操作失败！请检查是否已输入ID或ID冲突。");
        }
    }

    private void testQuery() {
        Log.i(TAG, "----- 查询所有记录 -----");
        appendLog("\n----- 查询所有记录 -----");

        /*
         * Cursor实例包含查询结果，是一个二维表结构，“游标”指向表中的“行”，我们可以切换游标位置读取各行
         * 的数据。
         */
        Cursor cursor = dbHelper.getDB()
                .query("student_info", null, null, null, null, null, null);
        try (cursor) {
            /*
             * 判断游标实例中是否存在数据项。
             *
             * "moveToFirst()"方法会将游标移动至第一行。如果该行不存在，返回"false"；如果该行存在，则返回
             * "true"。
             */
            if (cursor.moveToFirst()) {
                /*
                 * 遍历游标实例，读取所有数据项。
                 *
                 * "moveToNext()"方法会将游标移动至当前位置的后一行。如果该行不存在，返回"false"；如果该行存
                 * 在，则返回"true"。
                 */
                do {
                    // 根据列索引与类型，读取当前行的属性。
                    long id = cursor.getLong(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);

                    // 生成Java对象
                    Student student = new Student(id, name, age);
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
