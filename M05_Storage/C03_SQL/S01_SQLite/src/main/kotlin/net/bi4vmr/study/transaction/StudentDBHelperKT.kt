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
class StudentDBHelperKT(
    context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val TAG: String = "TestApp-${StudentDBHelperKT::class.java.simpleName}"

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
                "(2, '德川裕太', 10)"
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
        // 暂不使用
    }

    /**
     * 回调方法：数据库就绪。
     *
     * 当数据库创建/升级/降级完毕后，处于可用状态时，该方法将会被触发。
     *
     * @param[db] 数据库实例。
     */
    override fun onOpen(db: SQLiteDatabase) {
        Log.i(TAG, "OnOpen.")
        // 启用WAL日志
        db.enableWriteAheadLogging()
    }

    // 获取数据库实例
    fun getDB(): SQLiteDatabase {
        return writableDatabase
    }
}
