package net.bi4vmr.study.upgrade;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 实体类：学生（数据结构版本2）。
 */
@Entity(tableName = "student_info")
public class StudentV2 {

    // ID
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    private long id;

    // 姓名
    @ColumnInfo(name = "student_name")
    @NonNull
    private String name;

    // 生日
    @NonNull
    private String birthday;

    public StudentV2(long id, @NonNull String name, @NonNull String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(@NonNull String birthday) {
        this.birthday = birthday;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
