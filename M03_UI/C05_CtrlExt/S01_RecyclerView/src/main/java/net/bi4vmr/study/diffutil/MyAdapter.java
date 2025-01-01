package net.bi4vmr.study.diffutil;

import android.annotation.SuppressLint;
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
 * RecyclerView的适配器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final String TAG = "TestApp-" + MyAdapter.class.getSimpleName();

    /**
     * 数据源。
     */
    private final List<ItemVO> dataSource;

    /**
     * 构造方法。
     *
     * @param dataSource 初始数据源。
     */
    public MyAdapter(List<ItemVO> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.i(TAG, "OnAttachedToRecyclerView.");
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "OnCreateViewHolder. ViewType:[" + viewType + "]");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_type1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "OnBindViewHolder. Position:[" + position + "]");
        ItemVO vo = dataSource.get(position);
        holder.bindData(vo);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.i(TAG, "OnBindViewHolder. Position:[" + position + "] PayloadsNum:[" + payloads.size() + "]");
        // 如果Payload列表内容为空，则执行普通的 `onBindViewHolder()` 方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
            return;
        }

        // 短时间内多次更新同一表项时，Payload列表中可能有多个项，可以根据需要选择其中的一项。
        Object data = payloads.get(payloads.size() - 1);
        // 如果Payload不能被解析为Flags，则忽略。
        if (!(data instanceof Integer)) {
            Log.i(TAG, "Payload type is unknown.");
            return;
        }

        int flags = (Integer) data;
        Log.i(TAG, "Payload flags:[" + flags + "]");
        ItemVO vo = dataSource.get(holder.getAdapterPosition());
        if ((flags & UpdateFlagsKT.FLAG_TITLE) != 0) {
            holder.updateTitle(vo);
        }
        if ((flags & UpdateFlagsKT.FLAG_INFO) != 0) {
            holder.updateInfo(vo);
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * 获取数据源的一份副本。
     * <p>
     * 因为数据源的更改会影响表项，有些操作不能直接在数据源上进行，可以在其副本上进行操作。
     *
     * @return 内含ItemVO的列表，是当前数据源的副本。
     */
    public List<ItemVO> getCopyOfDataSource() {
        List<ItemVO> newList = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            // 创建新的对象，并复制原对象的属性。
            ItemVO item = dataSource.get(i);
            String title = item.getTitle();
            String comment = item.getInfo();
            newList.add(new ItemVO(title, comment));
        }
        return newList;
    }

    /**
     * 使用DiffUtil更新列表。
     *
     * @param newDatas 数据源。
     */
    public void updateData(List<ItemVO> newDatas) {
        // 复制一份旧数据源以便 `getChangePayload()` 回调能够对比内容差异。
        List<ItemVO> oldDatas = new ArrayList<>(dataSource);
        // 对比新旧列表的差异
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldDatas, newDatas));
        // 更新数据源
        dataSource.clear();
        dataSource.addAll(newDatas);
        // 更新视图
        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * 重新加载列表。
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

    /* 表项的ViewHolder类 */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }

        public void bindData(ItemVO vo) {
            updateTitle(vo);
            updateInfo(vo);
        }

        public void updateTitle(ItemVO vo) {
            tvTitle.setText(vo.getTitle());
        }

        public void updateInfo(ItemVO vo) {
            tvInfo.setText(vo.getInfo());
        }
    }
}
