package net.bi4vmr.study.initdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.bi4vmr.study.R;

/**
 * Name        : TestFragment
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-05-30 22:40
 * Description : 测试Fragment
 */
public class TestFragment extends Fragment {

    private static final String PARAM_TEXTINFO = "TEXTINFO";

    private String textInfo;

    public TestFragment() {
        // 此处应当留空
    }

    /**
     * 获取Fragment实例
     *
     * @param textInfo 文本信息
     * @return 当前Fragment的实例
     */
    public static TestFragment newInstance(String textInfo) {
        TestFragment fragment = new TestFragment();
        // 将外部参数封装至Bundle
        Bundle args = new Bundle();
        args.putString(PARAM_TEXTINFO, textInfo);
        // 向Fragment传入Bundle
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("myapp", "OnAttach.");
        // 从Fragment中获取Bundle对象
        Bundle args = getArguments();
        if (args != null) {
            // 从Bundle中取出参数
            textInfo = args.getString(PARAM_TEXTINFO);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("myapp", "OnCreateView.");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv = view.findViewById(R.id.tvContent);
        tv.setText(textInfo);
        return view;
    }
}
