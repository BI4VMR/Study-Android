package net.bi4vmr.study.event

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiEventBinding

/**
 * 测试界面：事件监听器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIEventKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIEventKT::class.java.simpleName
    }

    private val binding: TestuiEventBinding by lazy {
        TestuiEventBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            /* 点击事件 */
            btnClick.setOnClickListener(object : View.OnClickListener {

                // 该方法将在控件被点击时回调
                override fun onClick(v: View) {
                    Log.i(TAG, "按钮被点击了！")
                    appendLog("按钮被点击了！\n")
                }
            })

            /* 长按事件 */
            btnClick.setOnLongClickListener(object : View.OnLongClickListener {

                // 该方法将在控件被按住0.5秒后被回调
                override fun onLongClick(v: View): Boolean {
                    Log.i(TAG, "按钮被长按了！")
                    appendLog("按钮被长按了！\n")

                    /*
                     * 该方法的返回值用于控制用户按住控件0.5秒触发长按回调后，抬起手指时是否触发点击回调。
                     *
                     * 返回 `true` 表示长按回调已经处理完毕触控流程，用户抬手时不会触发点击事件回调方法。
                     * 返回 `false` 表示长按回调没有处理完毕触控流程，用户抬手时仍会触发点击事件回调方法。
                     *
                     * 对于大部分场景都应当返回 `true` 。
                     */
                    return true
                }
            })
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: Any) {
        binding.tvLog.apply {
            post { append("\n$text") }
            post {
                runCatching {
                    val offset = layout.getLineTop(lineCount) - height
                    if (offset > 0) {
                        scrollTo(0, offset)
                    }
                }.onFailure { e ->
                    Log.w(TAG, "TextView scroll failed!", e)
                }
            }
        }
    }
}
