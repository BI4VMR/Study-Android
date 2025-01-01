package net.bi4vmr.study.upgrade

/**
 * 实体类：学生（数据结构版本2）。
 */
data class StudentV2KT(
    // ID
    var id: Long,
    // 姓名
    var name: String = "",
    // 生日
    var birthday: String = "2024-01-01",
)
