package net.bi4vmr.study.upgrade;

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
     * 查询所有学生信息。
     * <p>
     * "@Query"注解表示自定义SQL查询，唯一参数为要执行的SQL语句。
     *
     * @return 查询结果集合。
     */
    @Query("SELECT * FROM student_info")
    List<StudentV2> getStudent();

    /**
     * 新增学生记录。
     *
     * @param student 学生实体。
     */
    @Insert
    void addStudent(StudentV2 student);

    /**
     * 更新学生记录。
     *
     * @param student 学生实体。
     */
    @Update
    void updateStudent(StudentV2 student);

    /**
     * 删除学生记录。
     *
     * @param student 学生实体。
     */
    @Delete
    void delStudent(StudentV2 student);
}
