package com.dandykong.bceandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dandykong.bceandroid.R
import com.dandykong.bceandroid.viewmodel.GridViewModel

@Composable
fun GameScreenView(
    modifier: Modifier = Modifier,
    gridViewModel: GridViewModel = viewModel()
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillHeight
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Button(
            onClick = { gridViewModel.resetGame() }
        ) {
            Text("New game")
        }
        GridView(
            cells = gridViewModel.cells,
            onCellClick = gridViewModel::updateCellState
        )
    }

}
