package net.bi4vmr.study.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Name        : StudentDAO
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2024-01-07 16:41
 * <p>
 * Description : 数据访问类：学生信息。
 * <p>
 * 此处需要添加"@Dao"注解，使得Room能够识别到该DAO类。
 */
@Dao
public interface StudentDAO {

    /**
     * Name        : 查询所有学生信息
     * <p>
     * Description : 查询所有学生信息。
     * <p>
     * "@Query"注解表示自定义SQL查询，唯一参数为要执行的SQL语句。
     *
     * @return 查询结果集合
     */
    @Query("SELECT * FROM student_info")
    List<Student> getStudent();

    /**
     * Name        : 新增学生记录
     * <p>
     * Description : 新增学生记录。
     *
     * @param student 学生实体
     */
    @Insert
    void addStudent(Student student);

    /**
     * Name        : 更新学生记录
     * <p>
     * Description : 更新学生记录。
     *
     * @param student 学生实体
     */
    @Update
    void updateStudent(Student student);

    /**
     * Name        : 删除学生记录
     * <p>
     * Description : 删除学生记录。
     *
     * @param student 学生实体
     */
    @Delete
    void delStudent(Student student);
}
