package net.bi4vmr.study.diffutil;

import androidx.annotation.NonNull;

/**
 * Name        : ItemBean
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-04-04 15:38
 * <p>
 * Description : 列表项的实体类。
 */
public class ItemVO {

    private int id;
    private String title;
    private String info;

    public ItemVO() {
    }

    public ItemVO(int id, String title) {
        this.id = id;
        this.title = title;
        this.info = "-";
    }

    public ItemVO(int id, String title, String info) {
        this.id = id;
        this.title = title;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
