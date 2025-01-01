package net.bi4vmr.study.transaction

/**
 * 实体类：学生。
 */
data class StudentKT(
    // ID
    var id: Long,
    // 姓名
    var name: String = "",
    // 书籍数量
    var bookCount: Int = -1,
)
