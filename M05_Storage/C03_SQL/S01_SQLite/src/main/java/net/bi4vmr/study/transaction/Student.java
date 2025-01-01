package net.bi4vmr.study.transaction;

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
    // 书籍数量
    private int bookCount;

    // 具有1个参数的构造方法
    public Student(long id) {
        this.id = id;
    }

    // 具有3个参数的构造方法
    public Student(long id, String name, int bookCount) {
        this.id = id;
        this.name = name;
        this.bookCount = bookCount;
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

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int age) {
        this.bookCount = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }
}
