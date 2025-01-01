package net.bi4vmr.study.useinadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.ListItemSimpleBinding;

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
        this.mContext = context.getApplicationContext();
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        // 获取Item布局文件对应的视图绑定类实例
        ListItemSimpleBinding itemBinding = ListItemSimpleBinding.inflate(inflater, parent, false);
        // 创建ViewHolder实例，并传入视图绑定对象。
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        // 只需要保存视图绑定对象即可，不需要再保存每个控件的引用。
        private final ListItemSimpleBinding binding;

        public MyViewHolder(@NonNull ListItemSimpleBinding binding) {
            super(binding.getRoot());
            // 保存视图绑定对象
            this.binding = binding;
        }

        public void bindData() {
            // 获取当前项的数据
            SimpleVO vo = dataSource.get(getAdapterPosition());
            // 将数据设置到控件中
            binding.tvTitle.setText(vo.getTitle());
        }
    }
}
