package net.bi4vmr.study.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 数据库：学生信息。
 * <p>
 * 此处需要添加"@Database"注解，使得Room能够识别到该数据库类。
 * <p>
 * "entities"属性表示实体类的Class。
 * <p>
 * 存在多个实体类时，使用逗号分隔，格式为"entities = {A.class, B.class, ...}"。
 * <p>
 * "version"属性表示数据库的版本号。
 */
@Database(entities = Student.class, version = 1, exportSchema = false)
public abstract class StudentDB extends RoomDatabase {

    private volatile static StudentDB instance = null;

    // 获取数据库实例的方法
    public static StudentDB getInstance(Context context) {
        if (instance == null) {
            synchronized (StudentDB.class) {
                if (instance == null) {
                    /*
                     * 构造实例并进行配置
                     * "databaseBuilder()"的参数分别为：
                     * "context": 上下文。
                     * "dbClass": 数据库类的Class。
                     * "name": 数据库文件的名称。
                     */
                    instance = Room.databaseBuilder(context.getApplicationContext(), StudentDB.class, "student.db")
                            // Room默认不允许在主线程执行操作，此配置允许在主线程操作，仅适用于调试。
                            .allowMainThreadQueries()
                            // 设置日志模式
                            .setJournalMode(JournalMode.TRUNCATE)
                            // 如果检测到降级安装则清空数据（便于在做完升级实验后自动重置）
                            .fallbackToDestructiveMigrationOnDowngrade()
                            // 构建实例
                            .build();
                }
            }
        }
        return instance;
    }

    // 抽象方法，返回StudentDAO实例。
    public abstract StudentDAO getStudentDAO();
}
