package net.bi4vmr.study.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 学生信息数据库工具类。
 */
public class StudentDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "TestApp-" + StudentDBHelper.class.getSimpleName();

    private static final String DB_NAME = "student";
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

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
         * 初始化逻辑。
         *
         * 如果数据库文件不存在，该回调方法将会被触发。
         */
        Log.i(TAG, "OnCreate.");

        final String createTableSQL = "CREATE TABLE student_info(\"student_id\" INTEGER PRIMARY KEY, \"student_name\" TEXT, \"age\" INTEGER)";
        // 执行SQL语句，创建学生信息表。
        db.execSQL(createTableSQL);

        // 插入初始数据
        final String SQL1 = "INSERT INTO student_info VALUES(1, '张三', 20)";
        db.execSQL(SQL1);
        final String SQL2 = "INSERT INTO student_info VALUES(2, '李四', 22)";
        db.execSQL(SQL2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级逻辑，当数据结构版本有变化时，该回调方法将会被触发。
    }
}
