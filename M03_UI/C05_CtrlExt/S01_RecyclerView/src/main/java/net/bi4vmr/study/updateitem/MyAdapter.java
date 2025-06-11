package net.bi4vmr.study.updateitem;

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
        Log.i("TestApp", "OnCreateViewHolder. ViewType:[" + viewType + "]");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_simple, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("TestApp", "OnBindViewHolder. Position:[" + position + "]");
        holder.bindData();
    }

    /**
     * Name        : onBindViewHolder()
     * <p>
     * Description : 将数据与ViewHolder绑定的回调方法。
     * <p>
     * 我们需要从数据源中根据位置索引找到对应的条目，然后将数据通过ViewHolder设置给各个控件，实现UI与数据的统一。
     *
     * @param holder   ViewHolder实例
     * @param position Item在列表中的位置索引
     * @param payloads 数据载荷列表，其中的类型可以根据需要自行定义。
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.i("myapp", "OnBindViewHolder-Position:" + position + " PayloadsNum:" + payloads.size());
        // 如果载荷List内容为空，则执行普通的"onBindViewHolder()"方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            // 短时间内多次更新同一表项时，"payloads"中可能有多个项，可以根据需要选择其中的一项。
            Object data = payloads.get(0);
            /*
             * 此处放置具体的局部更新逻辑，可以根据ViewType、载荷实际类型等条件进行判断。
             */
            if (data instanceof SimpleVO) {
                // 我们定义规则为：更新值不为空的属性。
                SimpleVO item = (SimpleVO) data;
                if (item.getTitle() != null) {
                    holder.tvTitle.setText(item.getTitle());
                }
                if (item.getIconRes() != null) {
                    holder.ivIcon.setImageResource(item.getIconRes());
                }
            }
        }
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
        notifyItemChanged(position, item);
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

    /* 表项的ViewHolder类 */
    public class MyViewHolder extends RecyclerView.ViewHolder {

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
