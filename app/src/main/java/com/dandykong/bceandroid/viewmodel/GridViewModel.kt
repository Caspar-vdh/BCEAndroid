package com.dandykong.bceandroid.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.dandykong.bceandroid.state.CellState
import com.dandykong.bceandroid.state.CellValue

class GridViewModel : ViewModel() {
    private val _cells = getCellStates().toMutableStateList()
    val cells: List<CellState>
        get() = _cells

    fun updateCellState(cellState: CellState) {
        val index = _cells.indexOf(cellState)
        if (index != -1) {
            cellState.value = CellValue.CROSS
        }
    }

    private fun getCellStates(): List<CellState> {
        return List(9) { index -> CellState(id = index, initialValue = CellValue.EMPTY) }
    }

    companion object {
        private const val N_ROWS = 3
        private const val N_COLUMNS = 3

        fun rowAndColumnToIndex(row: Int, column: Int): Int {
            require(row in 0..<N_ROWS) { "Row index out of range: $row" }
            require(column in 0..<N_COLUMNS) { "Column index out of range: $column" }

            return row * N_ROWS + column
        }
    }
}
