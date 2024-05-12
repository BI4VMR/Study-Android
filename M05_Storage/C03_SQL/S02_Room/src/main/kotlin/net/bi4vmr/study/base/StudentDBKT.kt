package net.bi4vmr.study.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 数据库：学生信息。
 *
 * 此处需要添加"@Database"注解，使得Room能够识别到该数据库类。
 *
 * "entities"属性表示实体类的Class。
 *
 * 存在多个实体类时，使用逗号分隔，格式为"entities = {A.class, B.class, ...}"。
 *
 * "version"属性表示数据库的版本号。
 */
@Database(entities = [StudentKT::class], version = 1, exportSchema = true)
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
                            "student_kt"
                        )
                            // Room默认不允许在主线程执行操作，此配置允许在主线程操作，仅适用于调试。
                            .allowMainThreadQueries()
                            /*
                             * 设置日志模式。
                             *
                             * "TRUNCATE":
                             * 原生SQLite API的默认模式，当写入数据时会阻塞其他读取操作，降低写入并发。
                             *
                             * "WRITE_AHEAD_LOGGING":
                             * Room框架的默认模式(API Level > 16)，WAL模式使得读与写操作之间不会阻塞，只会阻塞写与写操作，能够
                             * 提高写入并发。
                             *
                             * WAL模式比TRUNCATE模式写入速度更快，但由于读取数据时也需要读取WAL日志验证数据的
                             * 正确性，所以读取数据较慢，我们应当根据实际使用场景进行选择。
                             */
                            .setJournalMode(JournalMode.TRUNCATE)
                            // 构建实例
                            .build()
                    }
                }
            }
            return instance!!
        }
    }

    // 抽象方法，返回StudentDAO实例。
    abstract fun getStudentDAO(): StudentDAOKT
}
