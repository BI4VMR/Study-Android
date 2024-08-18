package net.bi4vmr.study.themeattr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiThemeAttrBinding;

public class TestUIThemeAttr extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIThemeAttr.class.getSimpleName();

    private TestuiThemeAttrBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiThemeAttrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
