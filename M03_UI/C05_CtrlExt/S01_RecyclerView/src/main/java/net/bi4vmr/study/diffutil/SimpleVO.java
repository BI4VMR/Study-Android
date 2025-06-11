package net.bi4vmr.study.diffutil;

import androidx.annotation.NonNull;

/**
 * 列表项的实体类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class SimpleVO {

    // 标题
    private String title;
    // 图标ID
    private Integer iconRes;

    public SimpleVO() {
    }

    public SimpleVO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemVO{" +
                ", title='" + title + '\'' +
                '}';
    }
}
