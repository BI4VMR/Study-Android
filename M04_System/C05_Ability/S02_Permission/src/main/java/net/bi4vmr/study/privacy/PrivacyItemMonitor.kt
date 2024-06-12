package net.bi4vmr.study.privacy

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author 詹屹罡。
 */
interface PrivacyItemMonitor {
    fun startListening(callback: Callback)
    fun stopListening()
    fun getActivePrivacyItems(): List<PrivacyItem>

    interface Callback {
        fun onPrivacyItemsChanged()
    }
}
