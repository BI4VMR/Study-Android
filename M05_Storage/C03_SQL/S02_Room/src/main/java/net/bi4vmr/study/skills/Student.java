package net.bi4vmr.study.skills;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 实体类：学生。
 * <p>
 * 此处需要添加"@Entity"注解，使得Room能够识别到该实体类。
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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    private long id;

    // 姓名
    @ColumnInfo(name = "student_name")
    @NonNull
    private String name = "";

    // 年龄
    @ColumnInfo(name = "age")
    private int age;

    // 是否在UI中隐藏
    @Ignore
    private boolean hide;

    /*
     * 具有1个参数的构造方法
     *
     * Room只能识别具有完整属性的构造方法，因此其他构造方法需要添加"@Ignore"注解，使Room忽略它们。
     */
    @Ignore
    public Student(long id) {
        this.id = id;
    }

    // 具有3个参数的构造方法
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

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
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
