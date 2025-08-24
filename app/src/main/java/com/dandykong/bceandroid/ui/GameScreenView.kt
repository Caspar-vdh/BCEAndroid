package com.dandykong.bceandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dandykong.bceandroid.viewmodel.GridViewModel

@Composable
fun GameScreenView(
    modifier: Modifier = Modifier,
    gridViewModel: GridViewModel = viewModel()
) {
    Column (
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        GridView(
            cells = gridViewModel.cells,
            onCellClick = gridViewModel::updateCellState
        )
    }

}
