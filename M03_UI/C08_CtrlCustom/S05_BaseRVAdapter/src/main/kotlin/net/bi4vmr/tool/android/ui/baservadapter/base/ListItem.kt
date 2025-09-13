package net.bi4vmr.tool.android.ui.baservadapter.base

/**
 * 列表项通用接口。
 *
 * 所有列表项都需要实现此接口。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
interface ListItem {

    /**
     * 获取当前表项的类型。
     *
     * 与Adapter的ViewType对应，默认返回数值 `0` ，如果表项只有一个类型可以不重写本方法。
     *
     * @return 当前表项的类型代号。
     */
    fun getViewType(): Int {
        return 0
    }
}
