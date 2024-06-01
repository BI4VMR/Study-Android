package net.bi4vmr.study.diffutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.ArrayList;
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
    private final List<ItemVO> dataSource;

    public MyAdapter(Context context, List<ItemVO> dataSource) {
        this.mContext = context;
        this.dataSource = dataSource;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.i("myapp", "OnAttachedToRecyclerView.");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("myapp", "OnCreateViewHolder. ViewType:" + viewType);
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myapp", "OnBindViewHolder. Position:" + position);
        holder.bindData();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.i("myapp", "OnBindViewHolder. Position:" + position + " PayloadsNum:" + payloads.size());
        // 如果载荷List内容为空，则执行普通的onBindViewHolder()方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            // 短时间内多次更新同一表项时，"payloads"中可能有多个项，可以根据需要选择其中的一项。
            Object data = payloads.get(payloads.size() - 1);
            // 此处放置具体的局部更新逻辑，可以根据ViewType、载荷实际类型等条件进行判断。
            if (data instanceof ItemVO) {
                // 获取新数据的属性，并更新不为空的属性。
                ItemVO item = (ItemVO) data;
                if (item.getTitle() != null) {
                    holder.tvTitle.setText(item.getTitle());
                }
                if (item.getInfo() != null) {
                    holder.tvInfo.setText(item.getInfo());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * Name        : 获取数据源的一份副本
     * <p>
     * Description : 因为数据源的更改会影响表项，有些操作不能直接在数据源上进行，可以在其副本上进行操作。
     *
     * @return 内含ItemVO的列表，是当前数据源的副本。
     */
    public List<ItemVO> getCopyOfDataSource() {
        List<ItemVO> newList = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            // 创建新的对象，并复制原对象的属性。
            ItemVO item = dataSource.get(i);
            int id = item.getId();
            String title = item.getTitle();
            String comment = item.getInfo();
            newList.add(new ItemVO(id, title, comment));
        }
        return newList;
    }

    /**
     * Name        : 重新加载列表
     * <p>
     * Description : 更新RecyclerView中的所有表项。
     *
     * @param newDatas 新的数据源
     */
    @SuppressLint("NotifyDataSetChanged")
    public void reloadItem(List<ItemVO> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }

    /**
     * Name        : 更新列表
     * <p>
     * Description : 使用DiffUtil更新列表。
     *
     * @param newDatas 数据源
     */
    public void updateData(List<ItemVO> newDatas) {
        // 对比新旧列表的差异
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(dataSource, newDatas));
        // 更新数据源
        dataSource.clear();
        dataSource.addAll(newDatas);
        // 切换至主线程，更新视图。
        diffResult.dispatchUpdatesTo(this);
    }

    /* 表项的ViewHolder类 */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }

        public void bindData() {
            ItemVO item = dataSource.get(getAdapterPosition());
            tvTitle.setText(item.getTitle());
            tvInfo.setText(item.getInfo());
        }
    }
}
