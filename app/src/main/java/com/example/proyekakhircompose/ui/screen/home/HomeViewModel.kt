package com.example.proyekakhircompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyekakhircompose.data.PlayerRepository
import com.example.proyekakhircompose.model.Player
import com.example.proyekakhircompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Player>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Player>>>
        get() = _uiState

    fun getAllPlayers() {
        viewModelScope.launch {
            repository.getPlayers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}