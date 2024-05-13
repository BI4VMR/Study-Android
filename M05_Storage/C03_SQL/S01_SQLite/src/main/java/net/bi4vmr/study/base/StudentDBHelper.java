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
        Log.i("S", "OnCreate");
        final String sql = "";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
