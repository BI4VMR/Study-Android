package net.bi4vmr.study.updateitem;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private final List<ItemVO> dataSource;

    /**
     * 构造方法。
     *
     * @param dataSource 初始数据源。
     */
    public MyAdapter(List<ItemVO> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("TestApp", "OnCreateViewHolder. ViewType:[" + viewType + "]");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_type1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("TestApp", "OnBindViewHolder. Position:[" + position + "]");
        ItemVO vo = dataSource.get(position);
        holder.bindData(vo);
    }

    /**
     * RecyclerView将数据与ViewHolder绑定的回调方法。
     * <p>
     * 当Adapter的 `notifyItemChanged(int position, Object payload)` 方法被调用时，该方法将被触发，第三参数 `payloads` 对应调用
     * 者传入的 `payload`。
     *
     * @param holder   ViewHolder实例。
     * @param position 表项在列表中的位置索引。
     * @param payloads Payload列表，其中的元素类型可以根据需要自行定义。
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.i("TestApp", "OnBindViewHolder. Position:[" + position + "] PayloadsNum:[" + payloads.size() + "]");
        // 如果Payload列表内容为空，则执行普通的 `onBindViewHolder()` 方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
            return;
        }

        // 短时间内多次更新同一表项时，Payload列表中可能有多个项，可以根据需要选择其中的一项。
        Object data = payloads.get(payloads.size() - 1);
        // 如果Payload不能被解析为Flags，则忽略。
        if (!(data instanceof Integer)) {
            Log.d("TestApp", "Payload type is unknown.");
            return;
        }

        int flags = (Integer) data;
        Log.d("TestApp", "Payload flags:[" + flags + "]");
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

    // 获取数据源
    public List<ItemVO> getDataSource() {
        return dataSource;
    }

    /**
     * 更新指定的表项。
     * <p>
     * ItemVO中非空的元素将被刷新到界面上。
     *
     * @param position 待更新的位置。
     * @param data     新的数据项。
     * @param flags    需要更新的内容。
     */
    public void updateItem(int position, ItemVO data, int flags) {
        // 更新数据源
        dataSource.set(position, data);
        // 通知RecyclerView指定表项被更改，刷新控件显示。
        notifyItemChanged(position, flags);
    }

    /**
     * 更新RecyclerView中的所有表项。
     *
     * @param newDatas 新的数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    public void reloadItems(List<ItemVO> newDatas) {
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
