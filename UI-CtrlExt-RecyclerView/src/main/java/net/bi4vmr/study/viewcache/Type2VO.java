package net.bi4vmr.study.viewcache;

import androidx.annotation.NonNull;

/**
 * Name        : Type2VO
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-04-04 15:38
 * <p>
 * Description : 列表项的实体类（类型II）。
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
