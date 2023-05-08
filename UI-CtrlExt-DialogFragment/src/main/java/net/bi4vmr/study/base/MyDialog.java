package net.bi4vmr.study.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import net.bi4vmr.study.R;

/**
 * Name        : MyDialog
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-07 16:27
 * <p>
 * Description : DialogFragment示例
 */
public class MyDialog extends DialogFragment {

    private DismissListener dismissListener = null;

    /**
     * Name        : 获取Fragment实例
     * <p>
     * Description : 创建一个Fragment实例并返回。
     *
     * @return Fragment类的实例
     */
    public static MyDialog newInstance() {
        return new MyDialog();
    }

//    /* 使用AlertDialog构建对话框 */
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        Log.i("myapp", "OnCreateDialog.");
//        // 使用AlertDialog构建界面
//        return new AlertDialog.Builder(requireContext())
//                .setTitle("提示")
//                .setMessage("这是一个提示对话框")
//                .create();
//    }

    /* 使用自定义View构建对话框 */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("myapp", "OnCreateView.");
        View view = inflater.inflate(R.layout.mydialog, container, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("标题");
        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.i("myapp", "OnDismiss.");
        if (dismissListener != null) {
            dismissListener.onClose();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("myapp", "OnDestroyView.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "OnDestroy.");
    }

    /* 对外接口：弹窗消失事件监听器 */
    interface DismissListener {
        void onClose();
    }

    // 设置弹窗关闭监听器
    public void setDismissListener(DismissListener l) {
        dismissListener = l;
    }
}
