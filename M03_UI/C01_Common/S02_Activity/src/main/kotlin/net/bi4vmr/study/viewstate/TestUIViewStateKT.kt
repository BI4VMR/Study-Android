package net.bi4vmr.study.viewstate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiViewstateBinding

class TestUIViewStateKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIViewStateKT::class.java.simpleName
    }

    private val binding: TestuiViewstateBinding by lazy {
        TestuiViewstateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "OnCreate.")
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "OnStart.")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "OnRestoreInstanceState.")

        // 从Bundle对象读取先前保存的数据
        val isChecked: Boolean = savedInstanceState.getBoolean("STATE")
        Log.i(TAG, "已读取数据：$isChecked")
        // 重新设置给控件
        binding.btnTest.isChecked = isChecked
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "OnResume.")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "OnPause.")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "OnSaveInstanceState.")

        // 从控件中读取数据并保存至系统提供的Bundle对象
        val isChecked: Boolean = binding.btnTest.isChecked
        outState.putBoolean("STATE", isChecked)
        Log.i(TAG, "已写入数据：$isChecked")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "OnStop.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "OnDestroy.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "OnRestart.")
    }
}
