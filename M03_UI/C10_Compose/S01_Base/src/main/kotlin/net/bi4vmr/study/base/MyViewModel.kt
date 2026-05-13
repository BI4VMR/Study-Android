package net.bi4vmr.study.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyViewModel : ViewModel() {

    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)

    val state: StateFlow<Int> = _state

    fun add(){
        _state.value++
    }
}
