package net.bi4vmr.study.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiLifecycleBinding
import net.bi4vmr.study.gotopage.TestActivityKT

class TestUILifeCycleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUILifeCycleKT::class.java.simpleName
    }

    private val binding: TestuiLifecycleBinding by lazy {
        TestuiLifecycleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "OnCreate.")
        setContentView(binding.root)

        // 打开新的Activity
        binding.btnGoToNewPage.setOnClickListener {
            val intent = Intent(this, TestActivityKT::class.java)
            startActivity(intent)
        }

        // 关闭当前Activity
        binding.btnClose.setOnClickListener {
            Log.i(TAG, "Finish function called.")
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "OnStart.")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "OnResume.")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "OnPause.")
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
