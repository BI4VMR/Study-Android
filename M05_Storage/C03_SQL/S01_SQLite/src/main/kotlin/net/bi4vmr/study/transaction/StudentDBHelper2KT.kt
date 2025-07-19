package net.bi4vmr.study.transaction

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * 学生信息数据库工具类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class StudentDBHelper2KT(
    context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val TAG: String = "TestApp-${StudentDBHelper2KT::class.java.simpleName}"

        private const val DB_NAME: String = "student2.db"
        private const val DB_VERSION: Int = 1
    }

    /**
     * 回调方法：初始化。
     *
     * 如果数据库文件不存在，该回调方法将会被触发，此处可以创建表结构与写入初始数据。
     *
     * @param[db] 数据库实例。
     */
    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "OnCreate.")
        // 执行SQL语句，创建学生信息表。
        val createTableSQL: String = """
            CREATE TABLE student_info
            (
            student_id INTEGER PRIMARY KEY,
            student_name TEXT,
            book_count INTEGER
            );
        """.trimIndent()
        db.execSQL(createTableSQL)

        // 设置初始数据
        val initData1SQL = "INSERT INTO student_info VALUES" +
                "(1, '田所浩二', 10)"
        db.execSQL(initData1SQL)
        val initData2SQL = "INSERT INTO student_info VALUES" +
                "(2, '德川', 10)"
        db.execSQL(initData2SQL)
    }

    /**
     * 回调方法：升级。
     *
     * 当数据结构版本有变化时，该回调方法将会被触发，此处可以实现版本迁移操作。
     *
     * @param[db]         数据库实例。
     * @param[oldVersion] 旧的数据结构版本号。
     * @param[newVersion] 新的数据结构版本号。
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "OnUpgrade.")
        // 暂不使用。
    }

    // 获取数据库实例。
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
