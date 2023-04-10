package net.bi4vmr.study.media_demo_musicplayer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.media_demo_musicplayer.databinding.MusicListitemBinding;
import net.bi4vmr.study.media_demo_musicplayer.model.MusicVO;

import java.util.List;

/**
 * Name          : MusicListAdapter
 * Author        : BI4VMR
 * Date          : 2022-04-18 09:48
 * Description   : 音乐列表适配器
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicVH> {

    private final List<MusicVO> dataSource;
    private final Context mContext;

    private OnItemClickListener onItemClickListener;

    public MusicListAdapter(@NonNull List<MusicVO> datas, @NonNull Context context) {
        dataSource = datas;
        mContext = context;
    }

    @NonNull
    @Override
    public MusicVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        MusicListitemBinding binding = MusicListitemBinding.inflate(inflater, parent, false);
        return new MusicVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicVH holder, int position) {
        MusicVO music = dataSource.get(position);

        holder.mBinding.tvTitle.setText(music.getTitle());
        holder.mBinding.tvArtist.setText(music.getArtist());
        holder.mBinding.ivCover.setImageBitmap(music.getCoverBMP());

        // 如果外部传入了表项点击监听器，则执行回调方法。
        if (onItemClickListener != null) {
            holder.mBinding.getRoot().setOnClickListener(v -> {
                // 执行外部传入的Item点击监听器-点击方法
                onItemClickListener.onItemClick(position, music);
            });
            holder.mBinding.getRoot().setOnLongClickListener(v -> {
                // 执行外部传入的Item点击监听器-长按方法
                onItemClickListener.onItemLongClick(position, music);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * Name        重新加载列表
     * Author      BI4VMR
     * Date        2022-4-10 22:48
     * Description 使用新的数据源替换RecyclerView中的现有数据。
     *
     * @param newDatas 新的数据源List
     */
    @SuppressLint("NotifyDataSetChanged")
    public void reloadItem(List<MusicVO> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }

    // 设置表项的点击事件
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // 表项点击事件监听器接口
    public interface OnItemClickListener {

        void onItemClick(int position, MusicVO vo);

        void onItemLongClick(int position, MusicVO vo);
    }

    /**
     * 音乐ViewHolder
     */
    static class MusicVH extends RecyclerView.ViewHolder {

        MusicListitemBinding mBinding;

        public MusicVH(@NonNull MusicListitemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}