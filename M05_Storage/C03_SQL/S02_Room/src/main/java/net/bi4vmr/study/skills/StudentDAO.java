package net.bi4vmr.study.skills;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * 数据访问类：学生信息。
 * <p>
 * 此处需要添加"@Dao"注解，使得Room能够识别到该DAO类。
 */
@Dao
public interface StudentDAO {

    /**
     * 插入学生记录。
     *
     * @param student 学生。
     * @return 新数据的"RowID"。
     */
    @Insert
    long insertStudent(Student student);

    /**
     * 插入学生记录（可变参数）。
     *
     * @param students 学生（可变参数）。
     * @return 新数据的"RowID"（数组）。
     */
    @Insert
    long[] insertStudents(Student... students);

    /**
     * 插入学生记录（集合）。
     *
     * @param students 学生（列表）。
     * @return 新数据的"RowID"（列表）。
     */
    @Insert
    List<Long> insertStudents(List<Student> students);

    /**
     * 插入学生记录（混合参数）。
     *
     * @param monitor  班长。
     * @param students 学生（列表）。
     */
    @Insert
    void insertStudents(Student monitor, List<Student> students);

    /**
     * 更新学生记录。
     *
     * @param student 学生实体。
     */
    @Update
    void updateStudent(Student student);

    /**
     * 删除学生记录。
     *
     * @param student 学生实体。
     */
    @Delete
    void delStudent(Student student);

    /**
     * 查询所有学生信息。
     * <p>
     * "@Query"注解表示自定义SQL查询，唯一参数为要执行的SQL语句。
     *
     * @return 查询结果集合。
     */
    @Query("SELECT * FROM student_info")
    List<Student> getStudent();
}
