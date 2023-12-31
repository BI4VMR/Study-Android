package net.bi4vmr.study.manage;

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

public class TestFragment extends Fragment {

    private static final String PARAM_TEXTINFO = "TEXTINFO";

    private String textInfo;

    /**
     * Name        : 获取Fragment实例
     * <p>
     * Description : 创建一个Fragment实例并返回。
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
        // 从Fragment中获取Bundle对象
        Bundle args = getArguments();
        if (args != null) {
            // 从Bundle中取出参数
            textInfo = args.getString(PARAM_TEXTINFO);
        }
        Log.i("myapp", "Fragment(" + textInfo + "): OnAttach.");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myapp", "Fragment(" + textInfo + "): OnCreate.");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("myapp", "Fragment(" + textInfo + "): OnCreateView.");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv = view.findViewById(R.id.tvContent);
        tv.setText(textInfo);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("myapp", "Fragment(" + textInfo + "): OnViewCreated.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("myapp", "Fragment(" + textInfo + "): OnStart.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("myapp", "Fragment(" + textInfo + "): OnResume.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("myapp", "Fragment(" + textInfo + "): OnPause.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("myapp", "Fragment(" + textInfo + "): OnStop.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("myapp", "Fragment(" + textInfo + "): OnDestroyView.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "Fragment(" + textInfo + "): OnDestroy.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("myapp", "Fragment(" + textInfo + "): OnDetach.");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("myapp", "Fragment(" + textInfo + "): OnHiddenChanged. IsHide:" + hidden);
    }
}
