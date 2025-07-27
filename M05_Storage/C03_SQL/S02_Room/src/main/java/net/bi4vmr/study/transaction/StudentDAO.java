package net.bi4vmr.study.transaction;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

/**
 * 数据访问类：学生信息。
 * <p>
 * 此处需要添加"@Dao"注解，使得Room能够识别到该DAO类。
 */
@Dao
public abstract class StudentDAO {

    /**
     * 查询所有学生信息。
     * <p>
     * `@Query` 注解表示自定义SQL查询，唯一参数为要执行的SQL语句。
     *
     * @return 查询结果集合。
     */
    @Query("SELECT * FROM student_info")
    abstract List<Student> getStudents();

    /**
     * 查询指定学生信息（根据ID）。
     *
     * @param id 学生ID。
     * @return 学生信息，如果记录不存在则为空值。
     */
    @Query("SELECT * FROM student_info WHERE student_id = :id")
    abstract Student getStudent(long id);

    /**
     * 新增学生记录。
     *
     * @param student 学生实体。
     */
    @Insert
    abstract void addStudent(Student student);

    /**
     * 更新学生记录。
     *
     * @param student 学生实体。
     */
    @Update
    abstract void updateStudent(Student student);

    /**
     * 删除学生记录。
     *
     * @param student 学生实体。
     */
    @Delete
    abstract void delStudent(Student student);

    /**
     * 借书。
     * <p>
     * 在事务中操作：编号为 `1` 的学生向编号为 `2` 的学生借阅壹本书。
     * <p>
     * `@Transaction` 注解等价于 `beginTransaction()` 等方法的组合调用，但只能配置在DAO类的方法上，在其他类中配置没有任何作用。
     */
    @Transaction
    void borrowBook() {
        // 将1号学生的书本数量加1
        Student studentA = getStudent(1);
        if (studentA != null) {
            studentA.setBookCount(studentA.getBookCount() + 1);
            updateStudent(studentA);
        }

        // 将2号学生的书本数量减1
        Student studentB = getStudent(2);
        if (studentB != null) {
            studentB.setBookCount(studentB.getBookCount() - 1);
            updateStudent(studentB);
        }
    }
}
