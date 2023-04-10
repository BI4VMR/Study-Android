package net.bi4vmr.study.ui.extctrl.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import net.bi4vmr.study.ui.extctrl.viewpager2.adapter.Adapter02In;

import java.util.ArrayList;
import java.util.List;

public class NestedFragment extends Fragment {


    private static final String ARG_TEXT = "TEXT";

    private String text;

    public static NestedFragment newInstance(String text) {
        NestedFragment fragment = new NestedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nested, container, false);
        // 设置文本框内容
        TextView tvText = view.findViewById(R.id.tvContent);
        tvText.setText(text);
        // 设置内部ViewPager2内容
        List<TestFragment> pages = new ArrayList<>();
        pages.add(TestFragment.newInstance(text + " | 内层页面1"));
        pages.add(TestFragment.newInstance(text + " | 内层页面2"));

        // 为ViewPager设置适配器
        ViewPager2 viewPager2 = view.findViewById(R.id.vp2Inner);
        Adapter02In adapter = new Adapter02In(requireActivity(), pages);
        viewPager2.setAdapter(adapter);
        return view;
    }
}