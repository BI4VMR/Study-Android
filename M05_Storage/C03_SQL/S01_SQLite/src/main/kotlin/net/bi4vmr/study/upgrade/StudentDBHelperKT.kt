package net.bi4vmr.study.upgrade

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import net.bi4vmr.study.base.StudentKT

/**
 * 学生信息数据库工具类。
 */
class StudentDBHelperKT(
    context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val TAG: String = "TestApp-${StudentDBHelperKT::class.java.simpleName}"

        private const val DB_NAME: String = "student.db"
        private const val DB_VERSION: Int = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "OnCreate.")
        // 执行SQL语句，创建学生信息表。
        val createTableSQL: String = """
            CREATE TABLE student_info
            (
            student_id INTEGER PRIMARY KEY AUTOINCREMENT,
            student_name TEXT,
            birthday TEXT
            );
        """.trimIndent()
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "OnUpgrade. OldVersion:[$oldVersion] NewVersion:[$newVersion]")

        // 根据版本号执行对应的升级流程。
        if (oldVersion == 1 && newVersion == 2) {
            migrateV1ToV2(db)
        }
    }

    // 版本1至版本2的数据结构升级逻辑。
    private fun migrateV1ToV2(db: SQLiteDatabase) {
        // 修改旧表的名称
        db.execSQL("ALTER TABLE student_info RENAME TO student_info_temp;")

        // 以新的数据结构创建学生信息表
        val createTableSQL: String = """
            CREATE TABLE student_info
            (
            student_id INTEGER PRIMARY KEY AUTOINCREMENT,
            student_name TEXT,
            birthday TEXT
            );
        """.trimIndent()
        db.execSQL(createTableSQL)

        // 读取旧表中的数据
        val oldDatas: MutableList<StudentKT> = mutableListOf()
        val cursor: Cursor = db.rawQuery("SELECT * FROM student_info_temp", null)
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
            val name: String = it.name
            val birthday = "2024-01-01"

            val sql = "INSERT INTO student_info VALUES($id, '$name', '$birthday');"
            db.execSQL(sql)
        }

        // 删除旧表
        db.execSQL("DROP TABLE student_info_temp")
    }

    // 获取数据库实例。
    fun getDB(): SQLiteDatabase {
        return writableDatabase
    }
}
