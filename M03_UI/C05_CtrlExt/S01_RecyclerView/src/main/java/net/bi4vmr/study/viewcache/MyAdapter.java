package net.bi4vmr.study.viewcache;

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
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 上下文环境
    private final Context mContext;
    // 数据源
    private final List<ListItem> dataSource;

    public MyAdapter(Context context, List<ListItem> dataSource) {
        this.mContext = context;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("myapp", "onCreateViewHolder() 表项类型:" + viewType);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder vh;
        // 根据ViewType参数创建对应的视图实例与ViewHolder
        switch (viewType) {
            case 1: {
                View view = inflater.inflate(R.layout.list_item_type1, parent, false);
                vh = new MyAdapter.Type1VH(view);
            }
            break;
            case 2: {
                View view = inflater.inflate(R.layout.list_item_type2, parent, false);
                vh = new MyAdapter.Type2VH(view);
            }
            break;
            default:
                throw new IllegalArgumentException();
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("myapp", "onBindViewHolder() 表项位置:" + (position + 1));
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1: {
                Type1VH vh = (Type1VH) holder;
                vh.bindData();
            }
            break;
            case 2: {
                Type2VH vh = (Type2VH) holder;
                vh.bindData();
            }
            break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.get(position).getViewType();
    }

    /* 第一种表项的ViewHolder */
    class Type1VH extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;
        ImageView ivIcon;

        public Type1VH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

        public void bindData() {
            Type1VO vo = (Type1VO) dataSource.get(getAdapterPosition());
            tvTitle.setText(vo.getTitle());
            tvInfo.setText(vo.getInfo());
        }
    }

    /* 第二种表项的ViewHolder */
    class Type2VH extends RecyclerView.ViewHolder {

        TextView tvInfo;

        public Type2VH(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }

        private void bindData() {
            Type2VO item = (Type2VO) dataSource.get(getAdapterPosition());
            tvInfo.setText(item.getInfo());
        }
    }
}
