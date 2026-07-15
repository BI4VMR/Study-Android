package net.bi4vmr.study.appcenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */

@Composable
fun AppCenter() {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            // contentPadding = PaddingValues(230.dp),
            horizontalArrangement = Arrangement.spacedBy(228.dp),
            verticalArrangement = Arrangement.spacedBy(100.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(111) { index ->
                AppCenterItem(index)
            }
        }
    }
}

@Composable
fun AppCenterItem(index: Int) {
    Box(contentAlignment = Alignment.Center) {
        // Icon
        Box(
            modifier = Modifier
                .size(160.dp)
                .background(Color.Red)
        )

        // 名称
        Text(
            text = index.toString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
