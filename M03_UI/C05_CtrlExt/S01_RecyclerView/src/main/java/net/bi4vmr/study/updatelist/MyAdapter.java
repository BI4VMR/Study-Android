package net.bi4vmr.study.updatelist;

import android.annotation.SuppressLint;
import android.content.Context;
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
 * Name        : MyAdapter
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-04-04 15:38
 * <p>
 * Description : RecyclerView的适配器。
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // 上下文环境
    private final Context mContext;
    // 数据源
    private final List<SimpleVO> dataSource;

    public MyAdapter(Context context, List<SimpleVO> dataSource) {
        this.mContext = context;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("myapp", "OnCreateViewHolder-ViewType:" + viewType);
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_simple, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myapp", "OnBindViewHolder-Position:" + position);
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * Name        : 更新指定的表项
     * <p>
     * Description : 更新指定的表项，不影响列表中的其它表项。
     *
     * @param position 待更新的位置
     * @param item     新的表项
     */
    public void updateItem(int position, SimpleVO item) {
        // 更新数据源
        dataSource.remove(position);
        dataSource.add(position, item);
        // 通知RecyclerView指定表项被更改，刷新控件显示。
        notifyItemChanged(position);
    }

    /**
     * Name        : 向指定位置插入表项
     * <p>
     * Description : 将新的表项插入到指定位置，若该位置已存在表项，则将其本身以及后继表项都后移一位。
     *
     * @param position 待插入的位置
     * @param item     新的表项
     */
    public void addItem(int position, SimpleVO item) {
        // 更新数据源
        dataSource.add(position, item);
        // 通知RecyclerView新的表项被插入，刷新控件显示。
        notifyItemInserted(position);
    }

    /**
     * Name        : 移除指定的表项
     * <p>
     * Description : 移除指定的表项，若该位置之后存在表项，则将这些表项都前移一位。
     *
     * @param position 待移除的位置
     */
    public void removeItem(int position) {
        // 更新数据源
        dataSource.remove(position);
        // 通知RecyclerView指定的表项被移除，刷新控件显示。
        notifyItemRemoved(position);
    }

    /**
     * Name        : 将指定的表项移动至新位置
     * <p>
     * Description : 将指定的表项移动到新位置。
     *
     * @param srcPosition 源位置
     * @param dstPosition 目标位置
     */
    public void moveItem(int srcPosition, int dstPosition) {
        // 如果源位置与目标位置相同，直接退出当前方法。
        if (srcPosition == dstPosition) {
            return;
        }

        // 更新数据源
        Collections.swap(dataSource, srcPosition, dstPosition);
        // 通知RecyclerView表项被移动，刷新控件显示。
        notifyItemMoved(srcPosition, dstPosition);
    }

    /**
     * Name        : 重新加载列表
     * <p>
     * Description : 更新RecyclerView中的所有表项。
     *
     * @param newDatas 新的数据源
     */
    @SuppressLint("NotifyDataSetChanged")
    public void reloadItem(List<SimpleVO> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }

    /* 表项的ViewHolder类 */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

        public void bindData() {
            // 获取当前项的数据
            SimpleVO item = dataSource.get(getAdapterPosition());
            // 将数据设置到当前项的控件中
            tvTitle.setText(item.getTitle());

            // 设置整个表项的监听器
            itemView.setOnClickListener(v -> {
                // 日志输出表项的位置
                Log.i("myapp", "ClickEvent-Item " + (getAdapterPosition() + 1) + " Clicked.");
            });
        }
    }
}
