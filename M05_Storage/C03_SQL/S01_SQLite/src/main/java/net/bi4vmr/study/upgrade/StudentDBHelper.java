package net.bi4vmr.study.upgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息数据库工具类。
 */
public class StudentDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "TestApp-" + StudentDBHelper.class.getSimpleName();

    private static final String DB_NAME = "student.db";
    // 数据库表结构版本
    private static final int DB_VERSION = 2;

    // 构造方法
    public StudentDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "OnCreate.");

        // 执行SQL语句，创建学生信息表。
        final String createTableSQL = "CREATE TABLE student_info" +
                "(" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_name TEXT," +
                "birthday TEXT" +
                ")";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "OnUpgrade. OldVersion:[" + oldVersion + "] NewVersion:[" + newVersion + "]");
        // 根据版本号执行对应的升级流程。
        if (oldVersion == 1 && newVersion == 2) {
            migrateV1ToV2(db);
        }
    }

    // 版本1至版本2的数据结构升级逻辑。
    private void migrateV1ToV2(SQLiteDatabase db) {
        // 修改旧表的名称
        db.execSQL("ALTER TABLE student_info RENAME TO student_info_temp;");

        // 以新的数据结构创建学生信息表
        final String createTableSQL = "CREATE TABLE student_info" +
                "(" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_name TEXT," +
                "birthday TEXT" +
                ")";
        db.execSQL(createTableSQL);

        // 读取旧表中的数据
        List<Student> oldDatas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM student_info_temp", null);
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
            long id = student.getID();
            String name = student.getName();
            String birthday = "2024-01-01";

            String sql = "INSERT INTO student_info VALUES(" + id + ", '" + name + "', '" + birthday + "');";
            db.execSQL(sql);
        }

        // 删除旧表
        db.execSQL("DROP TABLE student_info_temp");
    }

    // 获取数据库实例。
    public SQLiteDatabase getDB() {
        return getWritableDatabase();
    }
}
