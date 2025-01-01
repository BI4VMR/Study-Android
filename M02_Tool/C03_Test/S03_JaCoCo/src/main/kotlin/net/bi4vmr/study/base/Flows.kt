package net.bi4vmr.study.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object Flows{
    lateinit var s :String
    val flow = MutableStateFlow(1)

    fun main1() {
        s = "aa"
        // CoroutineScope(Dispatchers.Default).launch {
        //     flow.collect {
        //         println("$it")
        //     }
        // }
        println(s.length.toFloat())
    }
}
