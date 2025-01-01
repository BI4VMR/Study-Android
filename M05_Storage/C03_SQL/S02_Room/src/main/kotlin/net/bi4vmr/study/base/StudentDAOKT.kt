package net.bi4vmr.study.base;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * 数据访问类：学生信息。
 *
 * 此处需要添加 `@Dao` 注解，使得Room能够识别到该DAO类。
 */
@Dao
interface StudentDAOKT {

    /**
     * 查询所有学生信息。
     *
     * "@Query"注解表示自定义SQL查询，唯一参数为要执行的SQL语句。
     *
     * @return 查询结果集合。
     */
    @Query("SELECT * FROM student_info")
    fun getStudent(): List<StudentKT>

    /**
     * 新增学生记录。
     *
     * @param student 学生实体。
     */
    @Insert
    fun addStudent(student: StudentKT)

    /**
     * 更新学生记录。
     *
     * @param student 学生实体。
     */
    @Update
    fun updateStudent(student: StudentKT)

    /**
     * 删除学生记录。
     *
     * @param student 学生实体。
     */
    @Delete
    fun delStudent(student: StudentKT)
}
