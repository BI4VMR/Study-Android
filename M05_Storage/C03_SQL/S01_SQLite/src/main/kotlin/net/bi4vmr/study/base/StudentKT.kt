package net.bi4vmr.study.base

/**
 * 实体类：学生。
 */
data class StudentKT(
    // ID
    var id: Long,
    // 姓名
    var name: String = "",
    // 年龄
    var age: Int = -1,
)
