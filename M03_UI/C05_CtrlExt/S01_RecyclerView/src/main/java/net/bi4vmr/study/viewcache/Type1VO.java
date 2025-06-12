package net.bi4vmr.study.viewcache;

import androidx.annotation.NonNull;

/**
 * 列表项的实体类（类型I）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class Type1VO implements ListItem {

    private String title;
    private String info;

    public Type1VO(String title) {
        this.title = title;
        info = "-";
    }

    public Type1VO(String title, String info) {
        this.title = title;
        this.info = info;
    }

    @Override
    public int getViewType() {
        return 1;
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
        return "Type1VO{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
