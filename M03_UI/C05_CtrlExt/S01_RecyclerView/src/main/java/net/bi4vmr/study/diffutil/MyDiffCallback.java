package net.bi4vmr.study.diffutil;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * 自定义DiffUtil。
 * <p>
 * 用于设置表项的比较规则。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyDiffCallback extends DiffUtil.Callback {

    private final List<ItemVO> oldList;
    private final List<ItemVO> newList;

    public MyDiffCallback(List<ItemVO> oldList, List<ItemVO> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    /**
     * 获取旧数据源的大小。
     *
     * @return 旧数据源的大小。
     */
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    /**
     * 获取新数据源的大小。
     *
     * @return 新数据源的大小。
     */
    @Override
    public int getNewListSize() {
        return newList.size();
    }

    /**
     * 判断参数所指定的两个位置对应表项类型是否相同。
     *
     * @param oldItemPosition 旧数据源中的表项索引。
     * @param newItemPosition 新数据源中的表项索引。
     * @return 值为 `false` 时，表示两个表项不同；值为 `true` 时表示两个表项是相同的，但内部数据可能有变化，随后会调用
     * `areContentsTheSame()` 方法判断表项中的数据是否需要更新。
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // 此处只有一种表项类型，视图都可复用，直接返回 `true` 即可。
        return true;
    }

    /**
     * 判断参数所指定的两个位置对应表项内容是否相同。
     * <p>
     * 仅当 `areItemsTheSame()` 返回 `true` 时才会调用本方法。
     *
     * @param oldItemPosition 旧数据源中的表项索引。
     * @param newItemPosition 新数据源中的表项索引。
     * @return 值为 `true` 时表示两个表项内容相同，值为 `false` 表示两个表项内容不同。
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ItemVO oldItem = oldList.get(oldItemPosition);
        ItemVO newItem = newList.get(newItemPosition);
        // 比较二者的内容是否相同
        return oldItem.equals(newItem);
    }

    /**
     * 计算发生变化的属性，使得适配器执行局部刷新。
     * <p>
     * 自定义Callback不覆盖此方法时，系统默认返回 `null` ，此时将刷新整个列表项。
     *
     * @param oldItemPosition 旧数据源中的表项索引。
     * @param newItemPosition 新数据源中的表项索引。
     * @return 任意类型的对象，会传递给适配器 `onBindViewHolder()` 方法的 `payloads` 参数。
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ItemVO oldItem = oldList.get(oldItemPosition);
        ItemVO newItem = newList.get(newItemPosition);

        int flags = 0;
        if (!oldItem.getTitle().equals(newItem.getTitle())) {
            flags |= UpdateFlags.FLAG_TITLE;
        }
        if (!oldItem.getInfo().equals(newItem.getInfo())) {
            flags |= UpdateFlags.FLAG_INFO;
        }

        return flags;
    }
}
