package net.bi4vmr.study.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Name        : DBHelper
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-12-13 23:49
 * <p>
 * Description : 测试数据库。
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql11 = "CREATE TABLE student(student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_name VARCHAR(20), age INTEGER)";
        db.execSQL(sql11);
        String sql12 = "INSERT INTO student VALUES(1, 'Alice', 18)";
        db.execSQL(sql12);
        String sql13 = "INSERT INTO student VALUES(2, 'Bob', 20)";
        db.execSQL(sql13);
        String sql14 = "INSERT INTO student VALUES(3, 'Charlie', 21)";
        db.execSQL(sql14);

        String sql21 = "CREATE TABLE course(course_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name VARCHAR(20), credits INTEGER)";
        db.execSQL(sql21);
        String sql22 = "INSERT INTO course VALUES(1, '计算机基础', 2)";
        db.execSQL(sql22);
        String sql23 = "INSERT INTO course VALUES(2, 'C语言程序设计', 4)";
        db.execSQL(sql23);
        String sql24 = "INSERT INTO course VALUES(3, 'Python程序设计', 4)";
        db.execSQL(sql24);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("未支持升级，请卸载后重新安装！");
    }
}
