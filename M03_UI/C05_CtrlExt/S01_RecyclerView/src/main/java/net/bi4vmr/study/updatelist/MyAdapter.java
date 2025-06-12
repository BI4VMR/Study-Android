package net.bi4vmr.study.updatelist;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView的适配器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    /**
     * 数据源。
     */
    private final List<SimpleVO> dataSource;

    /**
     * 构造方法。
     *
     * @param dataSource 初始数据源。
     */
    public MyAdapter(List<SimpleVO> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("TestApp", "OnCreateViewHolder. ViewType:[" + viewType + "]");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_simple, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("TestApp", "OnBindViewHolder. Position:[" + position + "]");
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * 更新指定的表项。
     * <p>
     * 更新指定的表项，不影响列表中的其它表项。
     *
     * @param position 待更新的位置。
     * @param item     新的表项。
     */
    public void updateItem(int position, SimpleVO item) {
        // 更新数据源
        dataSource.set(position, item);
        // 通知RecyclerView指定表项被更改，刷新控件显示。
        notifyItemChanged(position);
    }

    /**
     * 向指定位置插入表项。
     * <p>
     * 将新的表项插入到指定位置，若该位置已存在表项，则将其本身以及后继表项都后移一位。
     *
     * @param position 待插入的位置。
     * @param data     新的表项。
     */
    public void addItem(int position, SimpleVO data) {
        // 更新数据源
        dataSource.add(position, data);
        // 通知RecyclerView新的表项被插入，刷新控件显示。
        notifyItemInserted(position);
    }

    /**
     * 移除指定的表项。
     * <p>
     * 移除指定的表项，若该位置之后存在表项，则将这些表项都前移一位。
     *
     * @param position 待移除的位置。
     */
    public void removeItem(int position) {
        // 更新数据源
        dataSource.remove(position);
        // 通知RecyclerView指定的表项被移除，刷新控件显示。
        notifyItemRemoved(position);
    }

    /**
     * 将指定的表项移动至新位置。
     *
     * @param srcPosition 源位置。
     * @param dstPosition 目标位置。
     */
    public void moveItem(int srcPosition, int dstPosition) {
        // 如果源位置与目标位置相同，则无需移动。
        if (srcPosition == dstPosition) {
            return;
        }

        // 更新数据源
        Collections.swap(dataSource, srcPosition, dstPosition);
        // 通知RecyclerView表项被移动，刷新控件显示。
        notifyItemMoved(srcPosition, dstPosition);
    }

    /**
     * 更新RecyclerView中的所有表项。
     *
     * @param newDatas 新的数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    public void reloadItems(List<SimpleVO> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // 保存控件的引用，以便后续绑定数据。
        TextView tvTitle;
        ImageView ivIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

        public void bindData() {
            // 获取当前表项位置对应的数据项
            SimpleVO vo = dataSource.get(getAdapterPosition());
            // 将数据设置到视图中
            if (tvTitle != null) {
                tvTitle.setText(vo.getTitle());
            }
        }
    }
}
