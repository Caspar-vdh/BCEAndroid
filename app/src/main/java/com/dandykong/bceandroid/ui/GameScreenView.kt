package com.dandykong.bceandroid.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dandykong.bceandroid.R
import com.dandykong.bceandroid.viewmodel.GridViewModel
import androidx.compose.ui.res.stringResource
import com.dandykong.bceandroid.state.CellState
import com.dandykong.bceandroid.viewmodel.UserMessage

@Composable
fun GameScreenView(
    modifier: Modifier = Modifier,
    gridViewModel: GridViewModel = viewModel()
) {
    // Collect state from ViewModel
    val cells = gridViewModel.cells
    val currentMessage by gridViewModel.currentMessage.collectAsStateWithLifecycle()

    // Pass data down to the stateless version
    GameContent(
        modifier = modifier,
        cells = cells,
        currentMessage = currentMessage,
        onReset = gridViewModel::resetGame,
        onCellClick = gridViewModel::updateCellState,
        onMessageShown = gridViewModel::userMessageShown
    )
}

@Composable
fun GameContent(
    modifier: Modifier = Modifier,
    cells: List<CellState>, // Or whatever your data structure is
    currentMessage: UserMessage?,
    onReset: () -> Unit,
    onCellClick: (CellState) -> Unit,
    onMessageShown: (Long) -> Unit
) {
    Column(
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
            onClick = onReset
        ) {
            Text("New game")
        }
        GridView(
            cells = cells,
            onCellClick = onCellClick
        )
    }

    val context = LocalContext.current

    currentMessage?.let { message ->
        Toast.makeText(context, stringResource(message.messageRes), Toast.LENGTH_SHORT).show()
        onMessageShown
    }
}
