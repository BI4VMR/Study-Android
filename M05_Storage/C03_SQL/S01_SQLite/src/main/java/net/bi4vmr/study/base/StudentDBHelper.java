package net.bi4vmr.study.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 学生信息数据库工具类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class StudentDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "TestApp-" + StudentDBHelper.class.getSimpleName();

    private static final String DB_NAME = "student.db";
    private static final int DB_VERSION = 1;

    // 构造方法
    public StudentDBHelper(@Nullable Context context) {
        /*
         * SQLiteOpenHelper类的构造方法。
         *
         * @param context 上下文环境。
         * @param name 数据库文件名称（无需添加".db"后缀）。
         * @param factory 自定义Cursor工厂类。
         * @param version 数据结构版本号。
         */
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 回调方法：初始化。
     * <p>
     * 如果数据库文件不存在，该回调方法将会被触发，此处可以创建表结构与写入初始数据。
     *
     * @param db 数据库实例。
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "OnCreate.");

        // 执行SQL语句，创建学生信息表。
        final String createTableSQL = "CREATE TABLE student_info" +
                "(" +
                "student_id INTEGER PRIMARY KEY," +
                "student_name TEXT," +
                "age INTEGER" +
                ")";
        db.execSQL(createTableSQL);
    }

    /**
     * 回调方法：升级。
     * <p>
     * 当数据结构版本有变化时，该回调方法将会被触发，此处可以实现版本迁移操作。
     *
     * @param db         数据库实例。
     * @param oldVersion 旧的数据结构版本号。
     * @param newVersion 新的数据结构版本号。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "OnUpgrade.");
        // 暂不使用。
    }

    // 获取数据库实例
    public SQLiteDatabase getDB() {
        /*
         * SQLiteOpenHelper的"getWritableDatabase()"方法将会尝试以“读写”模式打开数据库，如果数据文件所在分
         * 区已满，将会导致"SQLiteException"异常。
         * SQLiteOpenHelper的"getReadableDatabase()"方法将会尝试以“读写”模式打开数据库，如果数据文件所在分
         * 区已满，将会以“只读”模式打开数据库，不会因此导致异常。
         */
        return getWritableDatabase();
    }
}
