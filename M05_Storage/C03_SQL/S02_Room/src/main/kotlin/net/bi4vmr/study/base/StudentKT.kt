package net.bi4vmr.study.base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 实体类：学生。
 *
 * 此处需要添加"@Entity"注解，使得Room能够识别到该实体类。
 */
@Entity(tableName = "student_info")
data class StudentKT(

    /**
     * ID
     *
     * "@PrimaryKey"注解表示该属性为主键。
     *
     * "@ColumnInfo"注解表示该属性对应的列名。
     */
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    var id: Long,

    // 姓名
    @ColumnInfo(name = "student_name")
    var name: String?,

    // 年龄
    var age: Int
)
