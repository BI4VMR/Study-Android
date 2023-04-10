package net.bi4vmr.study.store.db.room.demo01;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Name        : StudentDB
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-15 22:40
 * Description : TODO 添加描述
 */
@Database(entities = Student.class, version = 1, exportSchema = false)
public abstract class StudentDB extends RoomDatabase {

    private static StudentDB instance;

    // 单例模式：获取DB实例。
    public static synchronized StudentDB getInstance(Context context) {
        if (instance == null) {
            Context appCtx = context.getApplicationContext();
            instance = Room.databaseBuilder(appCtx, StudentDB.class, "Test")
                    // Room默认不允许在主线程执行操作，此配置允许在主线程操作，仅适用于调试。
                    .allowMainThreadQueries()
                    // 构建实例
                    .build();
        }
        return instance;
    }

    // 抽象方法，返回StudentDAO实例
    public abstract StudentDAO getStudentDAO();
}
