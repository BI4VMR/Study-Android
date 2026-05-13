package net.bi4vmr.study.layout.grid.types;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.List;
import java.util.Random;

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

    /**
     * RecyclerView创建ViewHolder的回调方法。
     * <p>
     * 当RecyclerView创建新的ViewHolder时，将会回调此方法。我们应当在此处创建表项对应的View，并封装进ViewHolder返回给RecyclerView。
     *
     * @param parent   当前表项将要被放入的视图容器。
     * @param viewType 待创建的View类型。
     * @return ViewHolder实例。
     */
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
                vh = new GridVH(view);
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown view type [" + viewType + "]!");
        }

        return vh;
    }

    /**
     * RecyclerView将数据与ViewHolder绑定的回调方法。
     * <p>
     * 当RecyclerView将View显示到屏幕上之前，将会回调此方法。我们需要从数据源中根据位置索引找到对应的数据项，然后通过ViewHolder设置各个
     * 控件，实现View与数据的同步。
     *
     * @param holder   ViewHolder实例。
     * @param position 表项在列表中的位置索引。
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Type1VH) {
            ((Type1VH) holder).bindData();
        } else if (holder instanceof GridVH) {
            ((GridVH) holder).bindData();
        } else {
            Log.w("Test", "Unknown ViewHolder!");
        }
    }

    /**
     * RecyclerView获取表项总数的回调方法。
     *
     * @return 表项总数。
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * RecyclerView获取表项类型的回调方法。
     *
     * @param position 表项在列表中的位置。
     */
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
            TitleVO vo = (TitleVO) dataSource.get(getAdapterPosition());
            tvTitle.setText(vo.getTitle());
            tvInfo.setText(vo.getInfo());
        }
    }

    /**
     * 第二种表项的ViewHolder。
     */
    class GridVH extends RecyclerView.ViewHolder {

        View rootView;

        TextView tvInfo;

        public GridVH(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }

        public void bindData() {
            GridVO vo = (GridVO) dataSource.get(getAdapterPosition());
            tvInfo.setText(vo.getInfo());

            rootView.setBackgroundColor(Color.parseColor(getRandomColor()));
        }

        /**
         * 获取随机颜色代码。
         *
         * @return 十六进制颜色代码，例如"#5A6677"。
         */
        private String getRandomColor() {
            String R, G, B;
            Random random = new Random();
            R = Integer.toHexString(random.nextInt(256)).toUpperCase();
            G = Integer.toHexString(random.nextInt(256)).toUpperCase();
            B = Integer.toHexString(random.nextInt(256)).toUpperCase();

            R = R.length() == 1 ? "0" + R : R;
            G = G.length() == 1 ? "0" + G : G;
            B = B.length() == 1 ? "0" + B : B;

            return "#" + R + G + B;
        }
    }
}
