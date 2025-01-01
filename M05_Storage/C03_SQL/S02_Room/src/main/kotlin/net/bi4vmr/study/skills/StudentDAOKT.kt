package net.bi4vmr.study.skills;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * 数据访问类：学生信息。
 * <p>
 * 此处需要添加"@Dao"注解，使得Room能够识别到该DAO类。
 */
@Dao
interface StudentDAOKT {

    /**
     * 插入学生记录。
     *
     * @param[student] 学生。
     * @return 新数据的"RowID"。
     */
    @Insert
    fun insertStudent(student: StudentKT): Long

    /**
     * 插入学生记录。
     *
     * @param[students] 学生（可变参数）。
     * @return 新数据的"RowID"（数组）。
     */
    @Insert
    fun insertStudents(vararg students: StudentKT): Array<Long>

    /**
     * 插入学生记录（可变参数）。
     *
     * @param[students] 学生（列表）。
     * @return 新数据的"RowID"（列表）。
     */
    @Insert
    fun insertStudents(students: List<StudentKT>): List<Long>

    /**
     * 插入学生记录（混合参数）。
     *
     * @param[monitor] 班长。
     * @param[students] 学生（列表）。
     */
    @Insert
    fun insertStudents(monitor: StudentKT, students: List<StudentKT>)

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

    /**
     * 查询所有学生信息。
     *
     * "@Query"注解表示自定义SQL查询，唯一参数为要执行的SQL语句。
     *
     * @return 查询结果集合。
     */
    @Query("SELECT * FROM student_info")
    fun getStudent(): List<StudentKT>
}
