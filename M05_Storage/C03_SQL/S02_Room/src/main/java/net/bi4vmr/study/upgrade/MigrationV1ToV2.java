package net.bi4vmr.study.upgrade;

import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库版本迁移工具（v1至v2）。
 */
public class MigrationV1ToV2 extends Migration {

    private static final String TAG = "TestApp-" + MigrationV1ToV2.class.getSimpleName();

    public MigrationV1ToV2() {
        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
        Log.i(TAG, "Migrate.");
        // 修改旧表的名称
        db.execSQL("ALTER TABLE student_info RENAME TO student_info_temp;");

        // 以新的数据结构创建学生信息表
        final String createTableSQL = "CREATE TABLE student_info" +
                "(" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "student_name TEXT NOT NULL," +
                "birthday TEXT NOT NULL" +
                ")";
        db.execSQL(createTableSQL);

        // 读取旧表中的数据
        List<Student> oldDatas = new ArrayList<>();
        Cursor cursor = db.query("SELECT * FROM student_info_temp");
        try (cursor) {
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);

                    Student student = new Student(id, name, age);
                    oldDatas.add(student);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 迁移数据至新的类型，并写入新表。
        for (Student student : oldDatas) {
            long id = student.getId();
            String name = student.getName();
            String birthday = "2024-01-01";

            String sql = "INSERT INTO student_info VALUES(" + id + ", '" + name + "', '" + birthday + "');";
            db.execSQL(sql);
        }

        // 删除旧表
        db.execSQL("DROP TABLE student_info_temp");
    }
}
