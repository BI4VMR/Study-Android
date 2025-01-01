package net.bi4vmr.study.lifecycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import net.bi4vmr.study.databinding.FragmentTestBinding

/**
 * 测试Fragment。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestFragmentKT : Fragment() {

    companion object {
        private val TAG: String = "TestApp-${TestFragmentKT::class.java.simpleName}"

        @JvmStatic
        fun newInstance(): TestFragmentKT {
            return TestFragmentKT()
        }
    }

    private val binding: FragmentTestBinding by lazy {
        FragmentTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "OnCreate.")

        // 获取宿主Activity的MyViewModel2实例
        val vmInActivity: MyViewModel2KT = ViewModelProvider(requireActivity())[MyViewModel2KT::class.java]
        Log.i(TAG, "Get VM in parent Activity. ID:[${vmInActivity.id}]")

        // 获取当前Fragment的ViewModel2实例
        val vmInFragment: MyViewModel2KT = ViewModelProvider(this)[MyViewModel2KT::class.java]
        Log.i(TAG, "Get VM in Fragment. ID:[${vmInFragment.id}]")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "OnDestroy.")
    }
}
