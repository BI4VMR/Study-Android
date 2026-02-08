package net.bi4vmr.bookkeeper.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import net.bi4vmr.bookkeeper.base.entity.Account
import net.bi4vmr.bookkeeper.databinding.CommonMainActivityBinding
import net.bi4vmr.bookkeeper.ui.account.AccountVO
import net.bi4vmr.bookkeeper.ui.chart.ChartFragment
import net.bi4vmr.bookkeeper.ui.overview.OverviewFragment
import net.bi4vmr.bookkeeper.ui.setting.SettingFragment
import net.bi4vmr.bookkeeper.ui.transaction.TransactionAddFragment
import net.bi4vmr.bookkeeper.ui.transaction.TransactionManageFragment
import net.bi4vmr.tool.android.common.logcat.LogUtil
import net.bi4vmr.tool.android.ui.universal.base.BaseActivity

/**
 * Activity：主界面。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: CommonMainActivityBinding by lazy {
        CommonMainActivityBinding.inflate(layoutInflater)
    }

    private val fragments: MutableMap<Class<Fragment>, Fragment> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        /* 配置底部导航栏按钮 */
        tvOverview.setOnClickListener {
            title.tvTitle.text = "概览"
            switchPage<OverviewFragment> { OverviewFragment.newInstance() }
        }
        tvBilling.setOnClickListener {
            title.tvTitle.text = "账单"
            switchPage<TransactionManageFragment> { TransactionManageFragment.newInstance() }
        }
        tvBookkeeping.setOnClickListener {
            title.tvTitle.text = "记账"
            switchPage<TransactionAddFragment> { TransactionAddFragment.newInstance() }
        }
        tvChart.setOnClickListener {
            title.tvTitle.text = "报表"
            switchPage<ChartFragment> { ChartFragment.newInstance() }
        }
        tvSetting.setOnClickListener {
            title.tvTitle.text = "设置"
            switchPage<SettingFragment> { SettingFragment.newInstance() }
        }

        // 默认加载概览页面
        title.tvTitle.text = "概览"
        switchPage<OverviewFragment> { OverviewFragment.newInstance() }

        // TODO
        tvSetting.setOnClickListener {
            title.tvTitle.text = "设置"

            val a = TestAdatper()
            a.submit(
                listOf(
                    AccountVO(
                        data = Account(
                            "1L",
                            "AAAAAAAAAAA"
                        )
                    ),
                    AccountVO(
                        data = Account(
                            "2L",
                            "BBBBBBBBBBBBBBBB"
                        )
                    ),
                )
            )

            val spA = TestRVSpinnerAdapter(a)
            sp.setAdapter(spA)
            sp.setSelectedIndex(1)

            // 添加调试信息
            sp.addOnLayoutChangeListener { _, _, top, _, bottom, _, _, _, _ ->
                val height = bottom - top
                LogUtil.d("RVSpinner height changed: $height")
            }
        }
    }

    private inline fun <reified T> switchPage(newInstance: () -> Fragment) {
        // 如果当前已经打开了目标页面，则不进行任何操作。
        val current = supportFragmentManager.findFragmentById(binding.content.id) as? T
        if (current != null) {
            LogUtil.i("Target page is showing, ignored!")
            return
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.content.id, newInstance.invoke())
            .commit()
    }
}
