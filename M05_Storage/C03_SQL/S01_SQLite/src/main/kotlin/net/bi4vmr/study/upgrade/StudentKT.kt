package net.bi4vmr.study.upgrade

/**
 * 实体类：学生（数据结构版本1）。
 */
data class StudentKT(
    // ID
    var id: Long,
    // 姓名
    var name: String = "",
    // 年龄
    var age: Int = -1,
)
