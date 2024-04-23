package net.bi4vmr.study.xmlattrs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiXmlattrsBinding;

public class TestUIXMLAttrs extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIXMLAttrs.class.getSimpleName();

    private TestuiXmlattrsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiXmlattrsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
