package com.dandykong.bceandroid.state


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CellState(val id: Int, initialValue: CellValue = CellValue.EMPTY) {
    var value by mutableStateOf(initialValue)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CellState
        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}
