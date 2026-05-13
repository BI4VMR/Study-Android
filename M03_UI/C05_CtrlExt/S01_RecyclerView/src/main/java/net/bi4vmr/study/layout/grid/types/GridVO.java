package net.bi4vmr.study.layout.grid.types;

import androidx.annotation.NonNull;

/**
 * 列表项的实体类（Grid类型）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class GridVO implements ListItem {

    private String info;

    public GridVO(String info) {
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
