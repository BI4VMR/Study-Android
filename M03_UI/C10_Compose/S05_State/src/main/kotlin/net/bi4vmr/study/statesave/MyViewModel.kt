package net.bi4vmr.study.statesave

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel：计数器示例。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyViewModel : ViewModel() {

    // 可变 StateFlow ，仅内部使用。
    private val _count: MutableStateFlow<Int> = MutableStateFlow(0)

    // 不可变 StateFlow ，与可变实例保持同步，供外部观察。
    val count: StateFlow<Int> = _count


    // 业务接口：数值增加。
    fun add() {
        _count.value++
    }

    // 业务接口：数值减少。
    fun reduce() {
        _count.value--
    }
}
