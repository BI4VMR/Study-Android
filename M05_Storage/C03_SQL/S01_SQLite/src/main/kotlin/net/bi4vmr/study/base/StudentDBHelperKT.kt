package net.bi4vmr.study.base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * 学生信息数据库工具类。
 *
 * SQLiteOpenHelper类的构造方法：
 *
 * param context 上下文环境。
 * param name 数据库文件名称（无需添加".db"后缀）。
 * param factory 自定义Cursor工厂类。
 * param version 数据结构版本号。
 */
class StudentDBHelperKT(
    context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val TAG: String = "TestApp-${StudentDBHelperKT::class.java.simpleName}"

        private const val DB_NAME: String = "student"
        private const val DB_VERSION: Int = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        /*
         * 初始化逻辑。
         *
         * 如果数据库文件不存在，该回调方法将会被触发。
         */
        Log.i(TAG, "OnCreate.")
        // 执行SQL语句，创建学生信息表。
        val createTableSQL: String = """
            CREATE TABLE "student_info"
            (
            "student_id" INTEGER PRIMARY KEY,
            "student_name" TEXT,
            "age" INTEGER
            );
        """.trimIndent()
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 升级逻辑，当数据结构版本有变化时，该回调方法将会被触发。
    }

    // 获取数据库实例
    fun getDB(): SQLiteDatabase {
        /*
         * SQLiteOpenHelper的"getWritableDatabase()"方法将会尝试以“读写”模式打开数据库，如果数据文件所在分
         * 区已满，将会导致"SQLiteException"异常。
         * SQLiteOpenHelper的"getReadableDatabase()"方法将会尝试以“读写”模式打开数据库，如果数据文件所在分
         * 区已满，将会以“只读”模式打开数据库，不会因此导致异常。
         */
        return writableDatabase
    }
}
