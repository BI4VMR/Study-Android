package net.bi4vmr.study.diffutil;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * 列表项的实体类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class ItemVO {

    private String title;
    private String info;

    public ItemVO(String title) {
        this.title = title;
        info = "-";
    }

    public ItemVO(String title, String info) {
        this.title = title;
        this.info = info;
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
        return "SimpleVO{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemVO itemVO = (ItemVO) o;
        return Objects.equals(title, itemVO.title) && Objects.equals(info, itemVO.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, info);
    }
}
