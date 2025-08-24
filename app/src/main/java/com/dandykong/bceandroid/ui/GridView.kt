package com.dandykong.bceandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dandykong.bceandroid.state.CellState
import com.dandykong.bceandroid.viewmodel.GridViewModel

val cellSize = 100.dp
val spacerSize = cellSize / 8
val halfSpacerSize = spacerSize / 2

@Composable
fun GridView(
    cells: List<CellState>,
    onCellClick: (CellState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .background(color = Color.LightGray)
    ) {
        Spacer(modifier = Modifier.size(halfSpacerSize))
        Row(
            modifier = modifier
                .wrapContentSize()
        ) {
            Spacer(modifier = Modifier.size(halfSpacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(0, 0)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(0, 0)]) }
            )
            Spacer(modifier = Modifier.size(spacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(0, 1)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(0, 1)]) }
            )
            Spacer(modifier = Modifier.size(spacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(0, 2)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(0, 2)]) }
            )
            Spacer(modifier = Modifier.size(halfSpacerSize))
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Row(
            modifier = modifier
                .wrapContentSize()
        ) {
            Spacer(modifier = Modifier.size(halfSpacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(1, 0)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(1, 0)]) }
            )
            Spacer(modifier = Modifier.size(spacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(1, 1)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(1, 1)]) }
            )
            Spacer(modifier = Modifier.size(spacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(1, 2)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(1, 2)]) }
            )
            Spacer(modifier = Modifier.size(halfSpacerSize))
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Row(
            modifier = modifier
                .wrapContentSize()
        ) {
            Spacer(modifier = Modifier.size(halfSpacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(2, 0)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(2, 0)]) }
            )
            Spacer(modifier = Modifier.size(spacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(2, 1)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(2, 1)]) }
            )
            Spacer(modifier = Modifier.size(spacerSize))
            Cell(
                modifier = Modifier.size(cellSize),
                cells[GridViewModel.rowAndColumnToIndex(2, 2)].value,
                onClick = { onCellClick(cells[GridViewModel.rowAndColumnToIndex(2, 2)]) }
            )
            Spacer(modifier = Modifier.size(halfSpacerSize))
        }
        Spacer(modifier = Modifier.size(halfSpacerSize))
    }

}
