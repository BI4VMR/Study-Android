package net.bi4vmr.study.transaction

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

/**
 * 数据库：学生信息。
 *
 * 此处需要添加"@Database"注解，使得Room能够识别到该数据库类。
 *
 * "entities"属性表示实体类的Class。
 *
 * 存在多个实体类时，使用逗号分隔，格式为"entities = [A.class, B.class, ...]"。
 *
 * "version"属性表示数据库的版本号。
 */
@Database(entities = [StudentKT::class], version = 1, exportSchema = false)
abstract class StudentDBKT : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: StudentDBKT? = null

        // 获取数据库实例的方法
        @JvmStatic
        fun getInstance(context: Context): StudentDBKT {
            if (instance == null) {
                synchronized(StudentDBKT::class) {
                    if (instance == null) {
                        /*
                         * 构造实例并进行配置
                         * "databaseBuilder()"的参数分别为：
                         * "context": 上下文。
                         * "dbClass": 数据库类的Class。
                         * "name": 数据库文件的名称。
                         */
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            StudentDBKT::class.java,
                            "student2.db"
                        )
                            // 设置SQL语句回调，便于调试。
                            .setQueryCallback(object : QueryCallback {
                                override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                                    Log.d("StudentDB2KT", "SQL:[$sqlQuery] | $bindArgs")
                                }
                            }, Executors.newSingleThreadExecutor())
                            // Room默认不允许在主线程执行操作，此配置允许在主线程操作，仅适用于调试。
                            .allowMainThreadQueries()
                            // 设置日志模式
                            .setJournalMode(JournalMode.TRUNCATE)
                            // 构建实例
                            .build()

                        // 写入初始数据
                        val dao: StudentDAOKT = requireNotNull(instance).getStudentDAO()
                        if (dao.getStudents().isEmpty()) {
                            dao.addStudent(StudentKT(1, "田所浩二", 10))
                            dao.addStudent(StudentKT(2, "德川裕太", 10))
                        }
                    }
                }
            }
            return requireNotNull(instance)
        }
    }

    // 抽象方法，返回StudentDAO实例。
    abstract fun getStudentDAO(): StudentDAOKT
}
