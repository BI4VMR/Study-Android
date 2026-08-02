package net.bi4vmr.study.statesave

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel：计数器示例。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyViewModel2 : ViewModel() {

    // 可变 LiveData ，仅内部使用。
    private val _count: MutableLiveData<Int> = MutableLiveData(0)

    // 不可变 LiveData ，与可变实例保持同步，供外部观察。
    val count: LiveData<Int> = _count


    // 业务接口：数值增加。
    fun add() {
        _count.value = _count.value?.plus(1)
    }

    // 业务接口：数值减少。
    fun reduce() {
        _count.value = _count.value?.minus(1)
    }
}
