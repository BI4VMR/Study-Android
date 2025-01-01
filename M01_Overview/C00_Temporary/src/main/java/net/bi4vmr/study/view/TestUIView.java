package net.bi4vmr.study.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiViewBinding;

public class TestUIView extends AppCompatActivity {

    private TestuiViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiViewBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // View view1 = findViewById(R.id.view1);
        // view1.setScaleX(0.5F);
        // view1.setScaleY(0.5F);
        //
        // view1.setAlpha(0.5F);
        //
        // binding.getRoot().setScaleX(5);
        // binding.view1
    }
}
