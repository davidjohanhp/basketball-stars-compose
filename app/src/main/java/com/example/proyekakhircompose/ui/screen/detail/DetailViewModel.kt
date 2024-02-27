package com.example.proyekakhircompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyekakhircompose.data.PlayerRepository
import com.example.proyekakhircompose.model.Player
import com.example.proyekakhircompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Player>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<Player>>
        get() = _uiState

    fun getPlayerById(playerId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPlayerById(playerId))
        }
    }
}