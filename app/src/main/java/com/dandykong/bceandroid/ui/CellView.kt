package com.dandykong.bceandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dandykong.bceandroid.R
import com.dandykong.bceandroid.state.CellValue

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    state: CellValue = CellValue.NOUGHT,
    onClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .background(color = Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            CellValue.NOUGHT -> Image(
                painter = painterResource(id = R.drawable.nought),
                contentDescription = "o",
                modifier = Modifier.size(90.dp)
            )

            CellValue.CROSS -> Image(
                painter = painterResource(id = R.drawable.cross),
                contentDescription = "x",
                modifier = Modifier.size(90.dp)
            )

            CellValue.EMPTY -> {/* Noop */
            }
        }
    }
}
