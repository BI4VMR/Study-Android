package net.bi4vmr.study.base;

import androidx.annotation.NonNull;

/**
 * 实体类：学生。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class Student {

    // ID
    private long id;
    // 姓名
    private String name;
    // 年龄
    private int age;

    // 具有1个参数的构造方法
    public Student(long id) {
        this.id = id;
    }

    // 具有3个参数的构造方法
    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
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
