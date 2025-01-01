package net.bi4vmr.study.upgrade

import android.database.Cursor
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import net.bi4vmr.study.base.StudentKT

/**
 * 数据库版本迁移工具（v1至v2）。
 */
class MigrationV1ToV2KT : Migration(1, 2) {

    companion object {
        private val TAG: String = "TestApp-${MigrationV1ToV2KT::class.java.simpleName}"
    }

    override fun migrate(db: SupportSQLiteDatabase) {
        Log.i(TAG, "Migrate.")
        // 修改旧表的名称
        db.execSQL("ALTER TABLE student_info RENAME TO student_info_temp;")

        // 以新的数据结构创建学生信息表
        val createTableSQL: String = """
            CREATE TABLE student_info
            (
            student_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            student_name TEXT NOT NULL,
            birthday TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableSQL)

        // 读取旧表中的数据
        val oldDatas: MutableList<StudentKT> = mutableListOf()
        val cursor: Cursor = db.query("SELECT * FROM student_info_temp")
        cursor.use {
            if (cursor.moveToFirst()) {
                do {
                    // 根据列索引与类型，读取当前行的属性。
                    val id: Long = it.getLong(0)
                    val name: String = it.getString(1)
                    val age: Int = it.getInt(2)

                    val student = StudentKT(id, name, age)
                    oldDatas.add(student)
                } while (it.moveToNext())
            }
        }

        // 迁移数据至新的类型，并写入新表。
        oldDatas.forEach {
            val id: Long = it.id
            val name: String = it.name ?: ""
            val birthday = "2024-01-01"

            val sql = "INSERT INTO student_info VALUES($id, '$name', '$birthday');"
            db.execSQL(sql)
        }

        // 删除旧表
        db.execSQL("DROP TABLE student_info_temp")
    }
}
