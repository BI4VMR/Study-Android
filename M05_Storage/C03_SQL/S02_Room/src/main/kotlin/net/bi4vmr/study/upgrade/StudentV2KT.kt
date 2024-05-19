package net.bi4vmr.study.upgrade

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 实体类：学生（数据结构版本2）。
 */
@Entity(tableName = "student_info")
data class StudentV2KT(

    // ID
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    var id: Long,

    // 姓名
    @ColumnInfo(name = "student_name")
    var name: String,

    // 出生日期
    var birthday: String
)
