package net.bi4vmr.study

/**
 * 测试界面：动态更新表项。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
// class TestUIUpdateListKT : AppCompatActivity() {
//
//             // 1) 为 Popup 新建 RecyclerView（不要复用页面里的 binding.rvContent）
//             val rv = RecyclerView(this).apply {
//                 layoutManager = LinearLayoutManager(this@TestUIUpdateListKT)
//                 addItemDecoration(
//                     DividerItemDecoration(
//                         this@TestUIUpdateListKT,
//                         DividerItemDecoration.VERTICAL
//                     )
//                 )
//                 this.adapter = MyAdapterKT(getTestDatas())
//                 overScrollMode = View.OVER_SCROLL_NEVER
//             }
//
//             // 2) 用容器承载，便于设置宽高
//             val content = LinearLayout(this).apply {
//                 orientation = LinearLayout.VERTICAL
//                 addView(
//                     rv,
//                     ViewGroup.LayoutParams(
//                         resources.displayMetrics.widthPixels * 3 / 5,
//                         WRAP_CONTENT
//                     )
//                 )
//             }
//
//             val pw = PopupWindow(content, WRAP_CONTENT, WRAP_CONTENT, true).apply {
//                 isOutsideTouchable = true
//                 elevation = 12f
//                 setBackgroundDrawable(Color.WHITE.toDrawable()) // 需要背景时自行打开
//             }
//
//             // 3) 动态计算宽高 + 自动向上/向下弹出（对任意 contentView 生效）
//             pw.showAutoSizeAndDirection(
//                 anchor = binding.btAdd,
//                 contentView = content,
//                 preferredWidthPx = resources.displayMetrics.widthPixels * 3 / 5,
//                 maxHeightPx = null,         // 你也可以传 dp(320) 之类做上限
//                 marginPx = dp(8)            // 给边缘留点余量
//             )
//
//
//     private fun PopupWindow.showAutoSizeAndDirection(
//         anchor: View,
//         contentView: View,
//         preferredWidthPx: Int? = null,
//         maxHeightPx: Int? = null,
//         marginPx: Int = 0
//     ) {
//         // 可见窗口区域（考虑状态栏/刘海/IME）
//         val visible = Rect()
//         anchor.getWindowVisibleDisplayFrame(visible)
//
//         val loc = IntArray(2)
//         anchor.getLocationOnScreen(loc)
//         val anchorX = loc[0]
//         val anchorY = loc[1]
//         val anchorW = anchor.width
//         val anchorH = anchor.height
//
//         val spaceBelow = (visible.bottom - (anchorY + anchorH) - marginPx).coerceAtLeast(0)
//         val spaceAbove = (anchorY - visible.top - marginPx).coerceAtLeast(0)
//
//         // 先确定宽度：preferred > 以锚点宽为基准 > 兜底  (同时裁剪到可见区域)
//         val maxWidthInScreen = (visible.width() - marginPx * 2).coerceAtLeast(0)
//         val desiredW = (preferredWidthPx ?: anchorW).coerceAtMost(maxWidthInScreen)
//
//         // 测量内容期望高度（宽度按 desiredW 约束）
//         val wSpec = View.MeasureSpec.makeMeasureSpec(desiredW, View.MeasureSpec.AT_MOST)
//         val hSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//         contentView.measure(wSpec, hSpec)
//         val desiredH = contentView.measuredHeight
//
//         // 选择向下/向上，并计算允许高度
//         val showDown = spaceBelow >= spaceAbove
//         val availableH = (if (showDown) spaceBelow else spaceAbove).coerceAtLeast(0)
//         val cappedAvailableH = maxHeightPx?.let { min(it, availableH) } ?: availableH
//
//         // 设置最终宽高：高按可用空间裁剪；宽按可见区域裁剪
//         width = desiredW
//         height = min(desiredH, cappedAvailableH)
//
//         // 计算 X：尽量对齐锚点左侧，同时保证不越界
//         val minX = visible.left + marginPx
//         val maxX = (visible.right - marginPx - width).coerceAtLeast(minX)
//         val x = anchorX.coerceIn(minX, maxX)
//
//         if (showDown) {
//             // showAsDropDown 的 xoff 是相对 anchor 左侧
//             val xOff = x - anchorX
//             showAsDropDown(anchor, xOff, marginPx)
//         } else {
//             val y = (anchorY - height - marginPx).coerceAtLeast(visible.top + marginPx)
//             showAtLocation(anchor, Gravity.NO_GRAVITY, x, y)
//         }
//     }
//
//     private fun dp(value: Int): Int =
//         (value * resources.displayMetrics.density + 0.5f).toInt()
// }
