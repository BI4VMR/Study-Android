package net.bi4vmr.study.base;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Name        : Book
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-03-07 16:35
 * <p>
 * Description : 书本Bean类。
 */
public class Book implements Parcelable {

    // ID
    private int id;
    // 名称
    private String name;
    // 分类
    private List<String> type;

    // Parcel构造器静态变量
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    // 构造方法
    public Book(int id, String name, List<String> type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Parcel构造方法
    protected Book(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.createStringArrayList();
    }

    /**
     * Name        : 内容描述
     * <p>
     * Description : 描述被打包数据的类型，"0"表示普通Bean对象。
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Name        : 向Parcel容器写入数据
     * <p>
     * Description : 将属性写入Parcel容器进行打包。
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeStringList(type);
    }

    /**
     * Name        : 从Parcel容器中读取数据。
     * <p>
     * Description : 从Parcel容器中读取各属性的数据。
     */
    public void readFromParcel(Parcel src) {
        // 读取基本数据类型的数据
        id = src.readInt();
        name = src.readString();
        // 读取引用数据类型的数据
        src.readStringList(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
