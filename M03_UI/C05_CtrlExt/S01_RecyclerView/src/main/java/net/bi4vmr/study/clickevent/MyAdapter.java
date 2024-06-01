package net.bi4vmr.study.clickevent;

import android.content.Context;
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

    // 点击事件监听器的实现
    private ItemClickListener listener;

    public MyAdapter(Context context, List<SimpleVO> dataSource) {
        this.mContext = context;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_simple, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /* 点击监听器 */
    public interface ItemClickListener {
        void onClick(int position, SimpleVO item);
    }

    // 设置表项点击监听器
    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    /* 表项的ViewHolder类 */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;
        ImageView ivIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

        public void bindData() {
            // 获取当前项的数据
            SimpleVO item = dataSource.get(getAdapterPosition());
            // 将数据设置到当前项的控件中
            tvTitle.setText(item.getTitle());

            // 设置整个表项View的点击事件
            itemView.setOnClickListener(v -> {
                // 如果外部设置了点击事件监听器，则通知它事件触发。
                if (listener != null) {
                    listener.onClick(getAdapterPosition(), item);
                }
            });
        }
    }
}
