package net.bi4vmr.study.upgrade;

import androidx.annotation.NonNull;

/**
 * 实体类：学生（数据结构版本2）。
 */
public class StudentV2 {

    // ID
    private long id;
    // 姓名
    private String name;
    // 生日
    private String birthday;

    public StudentV2(long id, String name, String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
