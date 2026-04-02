package net.bi4vmr.study.layout;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiLayoutBinding;

/**
 * 测试界面：布局文件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILayout extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILayout.class.getSimpleName();

    private TestuiLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*
         * 获取LayoutInflater的三种方法：
         */

        // 通过Context获取LayoutInflater
        LayoutInflater layoutInflater1 = this.getSystemService(LayoutInflater.class);
        // 通过Context获取LayoutInflater（简化方法）
        LayoutInflater layoutInflater2 = LayoutInflater.from(this);
        // 通过Activity获取LayoutInflater
        LayoutInflater layoutInflater3 = this.getLayoutInflater();

        // 通过LayoutInflater将布局文件解析为View实例
        // getLayoutInflater().inflate(R.layout.testui_layout, binding.getRoot(), false);
    }
}
