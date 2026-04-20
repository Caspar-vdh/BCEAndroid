package com.dandykong.bceandroid.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.dandykong.bceandroid.state.CellState
import com.dandykong.bceandroid.state.CellValue
import com.dandykong.butter.game.GameFacade
import com.dandykong.butter.game.GameGridListener
import com.dandykong.butter.game.GameState
import com.dandykong.butter.game.GameStateListener
import com.dandykong.butter.game.grid.Grid
import com.dandykong.logger.ButterLogger
import com.dandykong.training.player.Player

class GridViewModel : ViewModel() {
    private val _cells = getCellStates().toMutableStateList()
    private val log = object : ButterLogger {
        override fun info(var1: String) {
            TODO("Not yet implemented")
        }

        override fun info(var1: String, var2: Any) {
            TODO("Not yet implemented")
        }

        override fun info(var1: String, var2: Any, var3: Any) {
            TODO("Not yet implemented")
        }

        override fun info(var1: String, vararg var2: Any) {
            TODO("Not yet implemented")
        }

    }
    private val gameFacade = GameFacade(log)
    var expectUserInput = false

    init {
        gameFacade.gameStateListener = GameStateListener { state ->
            expectUserInput = when (state) {
                GameState.WAITING_FOR_PLAYER -> true
                else -> false
            }
        }

        gameFacade.gameGridListener = object : GameGridListener {
            override fun onGridUpdated(grid: Grid, row: Int, column: Int) {
                val cell = grid.getCell(row, column)
                val cellValue = when (cell) {
                    Player.PLAYER_1 -> CellValue.NOUGHT
                    Player.PLAYER_2 -> CellValue.CROSS
                    else -> CellValue.EMPTY
                }
                val cellState = _cells[rowAndColumnToIndex(row, column)]
                cellState.value = cellValue
            }
        }
    }

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

    fun resetGame() {
        _cells.forEach { it.value = CellValue.EMPTY }
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
