package net.bi4vmr.study.store.db.room.demo01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Name        : StudentDAO
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-15 22:37
 * Description : StudentDAO。
 */
@Dao
public interface StudentDAO {

    @Query("SELECT * FROM Student")
    List<Student> getStudent();

    @Insert
    void addStudent(Student student);

    @Delete
    void delStudent(Student student);

    @Update
    void updateStudent(Student student);
}
