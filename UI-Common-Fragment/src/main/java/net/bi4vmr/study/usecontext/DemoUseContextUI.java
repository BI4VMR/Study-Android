package net.bi4vmr.study.usecontext;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import net.bi4vmr.study.R;

public class DemoUseContextUI extends AppCompatActivity {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_usecontext);

        manager = getSupportFragmentManager();

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnBack = findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(v ->
                manager.beginTransaction()
                        .add(R.id.container, new TestFragment()).
                        addToBackStack(null)
                        .commit()
        );

        btnBack.setOnClickListener(v -> manager.popBackStack());
    }
}
