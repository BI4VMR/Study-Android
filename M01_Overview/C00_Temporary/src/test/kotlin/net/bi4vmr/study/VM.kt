package net.bi4vmr.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class VM : ViewModel() {

    val flow: MutableStateFlow<Int> = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            flow.collect {
                println("VM flow value: $it")
                delay(1000L)
                println("VM flow value: $it after delay")
            }
        }
    }
}
