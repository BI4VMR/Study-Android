package net.bi4vmr.study.lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.bi4vmr.study.databinding.FragmentTestBinding

class TestFragmentKT private constructor() : Fragment() {

    companion object {
        private val TAG: String = "TestApp-${TestFragmentKT::class.java.simpleName}"

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Log.i(TAG, "OnAttach.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_TEXT, "")
        }
        Log.i(TAG, "$text OnCreate.")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "$text OnCreateView.")
        val binding: FragmentTestBinding = FragmentTestBinding.inflate(inflater, container, false)
        with(binding) {
            tvContent.text = text
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "$text OnViewCreated.")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "$text OnStart.")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "$text OnResume.")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "$text OnPause.")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "$text OnStop.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "$text OnDestroy.")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "$text OnDetach.")
    }
}
