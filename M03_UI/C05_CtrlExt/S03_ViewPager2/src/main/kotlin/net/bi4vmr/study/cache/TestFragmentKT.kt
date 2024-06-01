package net.bi4vmr.study.cache

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.bi4vmr.study.databinding.FragmentTestBinding
import kotlin.random.Random

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

    private val bgColor: Int
    private var text: String = ""

    init {
        bgColor = Color.parseColor(getRandomColor())
    }

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
            root.setBackgroundColor(bgColor)
            return root
        }
    }

    /**
     * 获取随机颜色代码。
     *
     * @return 十六进制颜色代码，例如"#5A6677"。
     */
    private fun getRandomColor(): String {
        var r = Random.nextInt(256).toString(16).uppercase()
        var g = Random.nextInt(256).toString(16).uppercase()
        var b = Random.nextInt(256).toString(16).uppercase()

        fun addPrefix(code: String): String {
            return if (code.length == 1) {
                "0$code"
            } else {
                code
            }
        }

        r = addPrefix(r)
        g = addPrefix(g)
        b = addPrefix(b)

        return "#$r$g$b"
    }
}
