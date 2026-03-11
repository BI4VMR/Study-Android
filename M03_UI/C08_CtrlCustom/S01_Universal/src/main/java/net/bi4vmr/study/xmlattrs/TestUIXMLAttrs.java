package net.bi4vmr.study.xmlattrs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiXmlattrsBinding;

/**
 * 测试界面：XML属性。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
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
