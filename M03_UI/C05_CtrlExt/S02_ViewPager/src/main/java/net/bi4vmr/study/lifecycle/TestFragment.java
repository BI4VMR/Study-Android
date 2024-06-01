package net.bi4vmr.study.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.bi4vmr.study.databinding.FragmentTestBinding;

/**
 * 测试Fragment
 */
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Log.i(TAG, "OnAttach.");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT, "");
        }
        Log.i(TAG, text + "OnCreate.");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, text + "OnCreateView.");
        FragmentTestBinding binding = FragmentTestBinding.inflate(inflater, container, false);
        binding.tvContent.setText(text);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, text + "OnStart.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, text + "OnResume.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, text + "OnPause.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, text + "OnStop.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, text + "OnDestroyView.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, text + "OnDestroy.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, text + "OnDetach.");
    }
}
