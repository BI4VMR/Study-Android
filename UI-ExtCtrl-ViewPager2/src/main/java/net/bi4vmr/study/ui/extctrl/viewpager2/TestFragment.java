package net.bi4vmr.study.ui.extctrl.viewpager2;

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

public class TestFragment extends Fragment {

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
        Log.i("myapp", text + "-onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
        Log.i("myapp", text + "-onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("myapp", text + "-onCreateView()");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tvText = view.findViewById(R.id.tvContent);
        tvText.setText(text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("myapp", text + "-onViewCreated()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("myapp", text + "-onViewStateRestored()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("myapp", text + "-onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("myapp", text + "-onResume()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("myapp", text + "-onHiddenChanged()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("myapp", text + "-onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("myapp", text + "-onStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("myapp", text + "-onSaveInstanceState()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("myapp", text + "-onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("myapp", text + "-onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", text + "-onDestroy()");
    }
}