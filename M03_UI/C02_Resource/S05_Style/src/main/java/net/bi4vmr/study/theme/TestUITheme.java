package net.bi4vmr.study.theme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiThemeBinding;

public class TestUITheme extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUITheme.class.getSimpleName();

    private TestuiThemeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiThemeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
