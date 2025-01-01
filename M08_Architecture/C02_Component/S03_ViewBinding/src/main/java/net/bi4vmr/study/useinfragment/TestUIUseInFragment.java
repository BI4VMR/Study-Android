package net.bi4vmr.study.useinfragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUIUseInFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_useinfragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, TestFragment.newInstance())
                .commit();
    }
}
