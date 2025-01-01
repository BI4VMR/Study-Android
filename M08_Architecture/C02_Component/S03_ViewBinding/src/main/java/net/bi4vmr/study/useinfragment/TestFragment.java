package net.bi4vmr.study.useinfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import net.bi4vmr.study.databinding.LoginFragmentBinding;

public class TestFragment extends Fragment {

    private LoginFragmentBinding binding;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 使用生命周期方法提供的"inflater"和父容器获取视图绑定对象
        binding = LoginFragmentBinding.inflate(inflater, container, false);

        /* 初始化控件 */
        binding.tvTitle.setText("请输入登录信息");
        binding.btnLogin.setOnClickListener(v -> {
            // 访问用户名与密码输入控件，获取当前内容。
            String name = binding.etUsername.getText().toString();
            String pwd = binding.etPassword.getText().toString();
            Toast.makeText(requireContext(), "名称: " + name + " ;密码: " + pwd, Toast.LENGTH_SHORT)
                    .show();
        });

        // 返回View给系统
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 将视图绑定对象置空，防止内存泄漏。
        binding = null;
    }
}
