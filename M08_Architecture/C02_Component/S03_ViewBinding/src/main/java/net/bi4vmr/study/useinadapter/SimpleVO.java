package net.bi4vmr.study.useinadapter;

import androidx.annotation.NonNull;

/**
 * Name        : SimpleVO
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-04-04 15:38
 * <p>
 * Description : 列表项的实体类。
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
