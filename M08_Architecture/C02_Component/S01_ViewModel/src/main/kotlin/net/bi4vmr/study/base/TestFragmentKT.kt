package net.bi4vmr.study.base

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

        // 获取宿主Activity的MyViewModel实例
        val vm: MyViewModelKT = ViewModelProvider(requireActivity())[MyViewModelKT::class.java]
        Log.i(TAG, "Get VM in parent Activity. ID:[${vm.id}]")

        // 从VM实例读取数据
        val data: Int = vm.getNum()
        Log.i(TAG, "Get data in Activity's VM. Value:[$data]")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}
