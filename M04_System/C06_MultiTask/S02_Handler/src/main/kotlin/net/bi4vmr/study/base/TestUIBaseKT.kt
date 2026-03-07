package net.bi4vmr.study.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding
import kotlin.concurrent.thread

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"

        // 声明常量表示不同的消息
        private const val MSG_TEST_01: Int = 0x01
        private const val MSG_TEST_02: Int = 0x02
    }

    // 创建Handler对象，使用主线程的事件循环。
    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {

        // 回调方法：当有新消息需要处理时被触发
        override fun handleMessage(msg: Message) {
            // 根据"what"属性区分消息
            val code: Int = msg.what
            when (code) {
                0x01 -> {
                    Log.d(TAG, "HandleMessage 1.")
                    binding.tvLog.append("\nHandleMessage 1.")
                    // 在此处编写收到1号消息后需要执行的逻辑...
                }

                0x02 -> {
                    Log.d(TAG, "HandleMessage 2. Arg1:[${msg.arg1}] Arg2:[${msg.arg2}]")
                    binding.tvLog.append("\nHandleMessage 2. Arg1:[${msg.arg1}] Arg2:[${msg.arg2}]")
                    // 在此处编写收到2号消息后需要执行的逻辑...
                }
            }
        }
    }

    // 子线程的Handler
    private lateinit var mSubHandler: Handler

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    init {
        // 在子线程中初始化Handler
        thread {
            // 创建本线程的Looper对象
            Looper.prepare()
            // 使用当前线程的Looper创建Handler对象
            Looper.myLooper()?.let {
                mSubHandler = Handler(it)
            }
            // 开始事件循环
            Looper.loop()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnSendMsg1.setOnClickListener { testSendMessage1() }
            btnSendMsg2.setOnClickListener { testSendMessage2() }
            btnSendMsgDelay.setOnClickListener { testSendMessageDelay() }
            btnPostCallback.setOnClickListener { testPostCallback() }
            btnCancelMsg.setOnClickListener { testCancelMessage() }
            btnWrap.setOnClickListener { testWrap() }
            btnUseSubThreadHandler.setOnClickListener { testUseSubThreadHandler() }
        }
    }

    // 向队列中发送消息1
    private fun testSendMessage1() {
        Log.i(TAG, "--- 向队列中发送消息1 ---")
        binding.tvLog.append("\n--- 向队列中发送消息1 ---\n")

        // 发送消息
        mHandler.sendEmptyMessage(MSG_TEST_01)
    }

    // 向队列中发送消息2
    private fun testSendMessage2() {
        Log.i(TAG, "--- 向队列中发送消息2 ---")
        binding.tvLog.append("\n--- 向队列中发送消息2 ---\n")

        // 从系统申请一个Message对象
        val msg: Message = Message.obtain()
        msg.what = MSG_TEST_02
        // 设置参数
        msg.arg1 = 114514
        msg.arg2 = 1919810

        // 其他形式的参数
        // msg.obj = "Hello World!"
        // msg.data = Bundle()

        // 发送消息
        mHandler.sendMessage(msg)
    }

    // 向队列中发送消息（延时执行）
    private fun testSendMessageDelay() {
        Log.i(TAG, "--- 向队列中发送消息（延时执行） ---")
        binding.tvLog.append("\n--- 向队列中发送消息（延时执行） ---\n")

        // 向队列发送消息1，延时4秒后执行。
        mHandler.sendEmptyMessageDelayed(MSG_TEST_01, 4000L)

        // 向队列发送消息2，延时8秒后执行。
        val msg: Message = Message.obtain()
        msg.what = MSG_TEST_02
        msg.arg1 = -100
        mHandler.sendMessageDelayed(msg, 8000L)
    }

    // 向队列中提交回调方法
    private fun testPostCallback() {
        Log.i(TAG, "--- 向队列中提交回调方法 ---")
        binding.tvLog.append("\n--- 向队列中提交回调方法 ---\n")

        // 向队列提交回调方法，立刻执行。
        mHandler.post(object : Runnable {

            override fun run() {
                Log.i(TAG, "HandleCallback A")
            }
        })

        // 向队列提交回调方法，延时4秒后执行。
        mHandler.postDelayed({
            Log.i(TAG, "HandleCallback B")
        }, 4000L)
    }

    // 移除队列中尚未执行的消息1
    private fun testCancelMessage() {
        Log.i(TAG, "--- 移除队列中尚未执行的消息1 ---")
        binding.tvLog.append("\n--- 移除队列中尚未执行的消息1 ---\n")

        // 移除所有编号为1的消息
        mHandler.removeMessages(MSG_TEST_01)
    }

    // 更新UI的快捷方法
    private fun testWrap() {
        Log.i(TAG, "--- 更新UI的快捷方法 ---")
        binding.tvLog.append("\n--- 更新UI的快捷方法 ---\n")

        // 向View的事件队列中提交回调方法。
        binding.tvLog.post(object : Runnable {

            override fun run() {
                binding.tvLog.append("\nCall View.post()")
            }
        })

        // 向View的事件队列中提交回调方法，延时执行。
        binding.tvLog.postDelayed(object : Runnable {

            override fun run() {
                binding.tvLog.append("\nCall View.postDelayed()")
            }
        }, 5000L)

        // 向Activity的事件队列中提交回调方法。
        runOnUiThread(object : Runnable {

            override fun run() {
                binding.tvLog.append("\nCall Activity.runOnUiThread()")
            }
        })
    }

    // 使用子线程的Handler
    private fun testUseSubThreadHandler() {
        Log.i(TAG, "--- 使用子线程的Handler ---")
        binding.tvLog.append("\n--- 使用子线程的Handler ---\n")

        // 向队列提交回调方法，立刻执行。
        mSubHandler.post { Log.i(TAG, "HandleCallback A") }

        // 向队列提交回调方法，延时4秒后执行。
        mSubHandler.postDelayed({ Log.i(TAG, "HandleCallback B") }, 4000L)
    }
}
