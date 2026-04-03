package net.bi4vmr.study.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

/**
 * 测试界面：手动渲染布局文件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILayoutInflater extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILayoutInflater.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * 获取LayoutInflater的三种方法：
         */

        // 通过Context获取LayoutInflater
        LayoutInflater layoutInflater1 = this.getSystemService(LayoutInflater.class);
        // 通过Context获取LayoutInflater（简化方法）
        LayoutInflater layoutInflater2 = LayoutInflater.from(this);
        // 通过Activity获取LayoutInflater
        LayoutInflater layoutInflater3 = this.getLayoutInflater();

        // 通过LayoutInflater将布局文件解析为View实例，并设置到窗口中。
        ViewGroup viewOfWindow = (ViewGroup) getWindow().getDecorView();
        layoutInflater3.inflate(R.layout.testui_layout, viewOfWindow, true);
    }
}
