package net.bi4vmr.study.viewtype;

import androidx.annotation.NonNull;

/**
 * 列表项的实体类（类型II）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class Type2VO implements ListItem {

    private String info;

    public Type2VO(String info) {
        this.info = info;
    }

    @Override
    public int getViewType() {
        return 2;
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
        return "Type2VO{" +
                "info='" + info + '\'' +
                '}';
    }
}
