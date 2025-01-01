package net.bi4vmr.study.viewtype;

/**
 * “列表表项”接口，用于规范表项必须实现的功能。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public interface ListItem {

    /**
     * 获取当前表项的类型。
     *
     * @return 表项类型编号。
     */
    int getViewType();
}
