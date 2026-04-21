package com.dandykong.bceandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.dandykong.bceandroid.R
import com.dandykong.bceandroid.state.CellState
import com.dandykong.bceandroid.state.CellValue
import com.dandykong.butter.game.GameEventListener
import com.dandykong.butter.game.GameFacade
import com.dandykong.butter.game.GameGridListener
import com.dandykong.butter.game.GameState
import com.dandykong.butter.game.GameStateListener
import com.dandykong.butter.game.grid.Grid
import com.dandykong.logger.ButterLogger
import com.dandykong.training.player.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GridViewModel : ViewModel() {

    private val _currentMessage = MutableStateFlow<UserMessage?>(null)
    var currentMessage = _currentMessage.asStateFlow()

    private val _cells = getCellStates().toMutableStateList()
    private val log = object : ButterLogger {

        override fun info(var1: String) {
            Log.i(TAG, var1)
        }

        override fun info(var1: String, var2: Any) {
            Log.i(TAG, "$var1: $var2")
        }

        override fun info(var1: String, var2: Any, var3: Any) {
            Log.i(TAG, "$var1: $var2, $var3")
        }

        override fun info(var1: String, vararg var2: Any) {
            Log.i(TAG, "$var1: ${var2.joinToString()}")
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

        gameFacade.gameEventListener = object : GameEventListener {
            override fun onGameStarted(firstPlayerType: Player.Type) {
                val id = System.currentTimeMillis()
                _currentMessage.value = when (firstPlayerType) {
                    Player.Type.HUMAN_PLAYER -> UserMessage(id, R.string.started_human_player)
                    Player.Type.CPU_PLAYER -> UserMessage(id, R.string.started_cpu_player)
                }
            }

            override fun onGameTerminated(winningPlayerType: Player.Type?) {
                val id = System.currentTimeMillis()
                _currentMessage.value = when (winningPlayerType) {
                    null -> UserMessage(id, R.string.finished_no_winner)
                    Player.Type.HUMAN_PLAYER -> UserMessage(id, R.string.finished_human_player)
                    Player.Type.CPU_PLAYER -> UserMessage(id, R.string.finished_cpu_player)
                }
            }
        }

    }

    val cells: List<CellState>
        get() = _cells

    fun updateCellState(cellState: CellState) {
        val index = _cells.indexOf(cellState)
        val rowAndColumn = indexToRowAndColumn(index)
        gameFacade.processMove(rowAndColumn.first, rowAndColumn.second)
    }

    private fun getCellStates(): List<CellState> {
        return List(9) { index -> CellState(id = index, initialValue = CellValue.EMPTY) }
    }

    fun resetGame() {
        _cells.forEach { it.value = CellValue.EMPTY }
        gameFacade.startGame()
    }

    fun userMessageShown(messageIds: Long) {
        _currentMessage.update { currentMessage ->
            if (currentMessage?.id == messageIds) {
                null
            }
            else {
                currentMessage
            }
        }
    }


    companion object {
        private const val TAG = "GridViewModel"
        private const val N_ROWS = 3
        private const val N_COLUMNS = 3

        fun rowAndColumnToIndex(row: Int, column: Int): Int {
            require(row in 0..<N_ROWS) { "Row index out of range: $row" }
            require(column in 0..<N_COLUMNS) { "Column index out of range: $column" }

            return row * N_ROWS + column
        }

        fun indexToRowAndColumn(index: Int): Pair<Int, Int> {
            require(index in 0..<N_ROWS * N_COLUMNS) { "Index out of range: $index" }

            return Pair(index / N_ROWS, index % N_COLUMNS)
        }
    }
}

data class UserMessage(val id: Long, val messageRes: Int)