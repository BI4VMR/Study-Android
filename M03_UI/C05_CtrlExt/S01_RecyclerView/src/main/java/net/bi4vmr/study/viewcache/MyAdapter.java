package net.bi4vmr.study.viewcache;

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
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 数据源。
     */
    private final List<ListItem> dataSource;

    /**
     * 构造方法。
     *
     * @param dataSource 初始数据源。
     */
    public MyAdapter(List<ListItem> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        RecyclerView.ViewHolder vh;
        // 根据ViewType参数创建对应的视图实例与ViewHolder
        switch (viewType) {
            case 1: {
                View view = inflater.inflate(R.layout.list_item_type1, parent, false);
                vh = new Type1VH(view);
            }
            break;
            case 2: {
                View view = inflater.inflate(R.layout.list_item_type2, parent, false);
                vh = new Type2VH(view);
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown view type [" + viewType + "]!");
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Type1VH) {
            ((Type1VH) holder).bindData();
        } else if (holder instanceof Type2VH) {
            ((Type2VH) holder).bindData();
        } else {
            Log.w("Test", "Unknown ViewHolder!");
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 我们约定所有列表项都实现ListItem接口，因此可以调用其中的方法获取ViewType。
        return dataSource.get(position).getViewType();
    }

    /**
     * 第一种表项的ViewHolder。
     */
    class Type1VH extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;

        public Type1VH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }

        public void bindData() {
            Type1VO vo = (Type1VO) dataSource.get(getAdapterPosition());
            tvTitle.setText(vo.getTitle());
            tvInfo.setText(vo.getInfo());
        }
    }

    /**
     * 第二种表项的ViewHolder。
     */
    class Type2VH extends RecyclerView.ViewHolder {

        TextView tvInfo;

        public Type2VH(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }

        public void bindData() {
            Type2VO vo = (Type2VO) dataSource.get(getAdapterPosition());
            tvInfo.setText(vo.getInfo());
        }
    }
}
