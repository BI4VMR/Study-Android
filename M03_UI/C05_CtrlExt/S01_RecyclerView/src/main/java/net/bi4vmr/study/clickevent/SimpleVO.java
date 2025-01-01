package net.bi4vmr.study.clickevent;

import androidx.annotation.NonNull;

/**
 * 列表项的实体类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class SimpleVO {

    private String title;

    public SimpleVO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemVO{" +
                ", title='" + title + '\'' +
                '}';
    }
}
