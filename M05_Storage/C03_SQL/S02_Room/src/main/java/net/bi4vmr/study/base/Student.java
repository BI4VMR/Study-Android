package net.bi4vmr.study.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 实体类：学生。
 * <p>
 * 此处需要添加 `@Entity` 注解，使得Room能够识别到该实体类。
 */
@Entity(tableName = "student_info")
public class Student {

    /**
     * ID
     * <p>
     * "@PrimaryKey"注解表示该属性为主键。
     * <p>
     * "@ColumnInfo"注解表示该属性对应的列名。
     */
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    private long id;

    // 姓名
    @ColumnInfo(name = "student_name")
    private String name;

    // 年龄
    private int age;

    // 构造方法
    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
