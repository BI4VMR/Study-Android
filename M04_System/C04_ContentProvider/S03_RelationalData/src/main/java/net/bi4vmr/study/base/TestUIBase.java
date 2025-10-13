package net.bi4vmr.study.base;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnQueryStudent.setOnClickListener(v -> testQueryStudent());
        binding.btnQueryCourse.setOnClickListener(v -> testQueryCourse());
        binding.btnInsertStudent.setOnClickListener(v -> testInsertStudent());
        binding.btnDeleteStudent.setOnClickListener(v -> testDeleteStudent());
        binding.btnUpdateStudent.setOnClickListener(v -> testUpdateStudent());
    }

    // 查询学生信息
    private void testQueryStudent() {
        binding.tvLog.append("\n--- 查询学生信息 ---\n");
        Log.i(TAG, "--- 查询学生信息 ---");

        // 将URI文本转换为Uri对象
        Uri uri = Uri.parse("content://net.bi4vmr.provider/student");

        // 获取ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        // 查询联系人
        Cursor cursor = contentResolver.query(uri,
                null, null, null, null);

        if (cursor != null) {
            // 如果游标内存在数据，则遍历游标，读取每个信息。
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);

                    binding.tvLog.append("Student ID:" + id + ", Name:" + name + ", Age:" + age + "\n");
                    Log.i(TAG, "Student ID:" + id + ", Name:" + name + ", Age:" + age);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    // 查询课程信息
    private void testQueryCourse() {
        binding.tvLog.append("\n--- 查询课程信息 ---\n");
        Log.i(TAG, "--- 查询课程信息 ---");

        // 将URI文本转换为Uri对象
        Uri uri = Uri.parse("content://net.bi4vmr.provider/course");

        // 获取ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        // 查询联系人
        Cursor cursor = contentResolver.query(uri,
                null, null, null, null);

        if (cursor != null) {
            // 如果游标内存在数据，则遍历游标，读取每个信息。
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int credits = cursor.getInt(2);

                    binding.tvLog.append("Student ID:" + id + ", Name:" + name + ", Credits:" + credits + "\n");
                    Log.i(TAG, "Student ID:" + id + ", Name:" + name + ", Credits:" + credits);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    // 插入学生记录
    private void testInsertStudent() {
        binding.tvLog.append("\n--- 插入学生记录 ---\n");
        Log.i(TAG, "--- 插入学生记录 ---");

        // 封装新的学生数据
        ContentValues values = new ContentValues();
        values.put("student_name", "NewStudent");
        values.put("age", 24);

        // 将URI文本转换为Uri对象
        Uri uri = Uri.parse("content://net.bi4vmr.provider/student");

        // 获取ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        contentResolver.insert(uri, values);
    }

    // 删除学生记录
    private void testDeleteStudent() {
        binding.tvLog.append("\n--- 删除学生记录 ---\n");
        Log.i(TAG, "--- 删除学生记录 ---");

        // 将URI文本转换为Uri对象
        Uri uri = Uri.parse("content://net.bi4vmr.provider/student");

        // 获取ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        contentResolver.delete(uri, "student_name=?", new String[]{"NewStudent"});
    }

    // 更新学生记录
    private void testUpdateStudent() {
        binding.tvLog.append("\n--- 更新学生记录 ---\n");
        Log.i(TAG, "--- 更新学生记录 ---");

        // 封装新的学生数据
        ContentValues values = new ContentValues();
        values.put("student_name", "NewStudent");
        values.put("age", 11);

        // 将URI文本转换为Uri对象
        Uri uri = Uri.parse("content://net.bi4vmr.provider/student");

        // 获取ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        contentResolver.update(uri, values, "student_name=?", new String[]{"NewStudent"});
    }
}
