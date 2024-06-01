package net.bi4vmr.study.loop2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.bi4vmr.study.databinding.FragmentTestBinding

class TestFragmentKT private constructor() : Fragment() {

    companion object {
        private val TAG: String = TestFragmentKT::class.java.simpleName

        private const val ARG_TEXT: String = "TEXT"

        @JvmStatic
        fun newInstance(text: String): TestFragmentKT {
            val fragment = TestFragmentKT()
            val args = Bundle()
            args.putString(ARG_TEXT, text)
            fragment.arguments = args
            return fragment
        }
    }

    private var text: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_TEXT, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTestBinding = FragmentTestBinding.inflate(inflater, container, false)
        with(binding) {
            tvContent.text = text
            root.setBackgroundColor(Color.LTGRAY)
            return root
        }
    }
}
