package net.bi4vmr.study.skills

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    var id: Long,

    // 姓名
    @ColumnInfo(name = "student_name")
    var name: String,

    // 年龄
    var age: Int
) {

    /*
     * 具有1个参数的构造方法
     *
     * Room只能识别具有完整属性的构造方法，因此其他构造方法需要添加"@Ignore"注解，使Room忽略它们。
     */
    @Ignore
    constructor(id: Long) : this(id, "", 0)

    // 是否在UI中隐藏
    @Ignore
    var hide: Boolean = false
}
