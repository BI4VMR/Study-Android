package net.bi4vmr.study.themeattrsinternal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiThemeAttrsInternalBinding;

public class TestUIThemeAttrsInternal extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIThemeAttrsInternal.class.getSimpleName();

    private TestuiThemeAttrsInternalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiThemeAttrsInternalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
