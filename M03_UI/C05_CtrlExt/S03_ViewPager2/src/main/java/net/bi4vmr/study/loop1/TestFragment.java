package net.bi4vmr.study.loop1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.bi4vmr.study.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    private static final String TAG = "TestApp-" + TestFragment.class.getSimpleName();

    private static final String ARG_TEXT = "TEXT";

    private String text;

    public static TestFragment newInstance(String text) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT, "");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTestBinding binding = FragmentTestBinding.inflate(inflater, container, false);
        binding.tvContent.setText(text);
        binding.getRoot().setBackgroundColor(Color.LTGRAY);
        return binding.getRoot();
    }
}
