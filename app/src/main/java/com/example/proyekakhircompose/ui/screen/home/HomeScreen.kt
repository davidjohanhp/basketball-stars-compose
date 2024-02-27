package com.example.proyekakhircompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.proyekakhircompose.data.PlayerRepository
import com.example.proyekakhircompose.ui.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyekakhircompose.model.Player
import com.example.proyekakhircompose.ui.common.UiState
import com.example.proyekakhircompose.ui.components.PlayerItem

@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlayerRepository())),
    navigateToDetail: (String) -> Unit,
    ) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPlayers()
            }
            is UiState.Success -> {
                HomeContent(listPlayers = uiState.data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    listPlayers: List<Player>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Box(modifier = modifier) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState
        ) {
            items(listPlayers, key = { it.id }) { player ->
                PlayerItem(
                    name = player.name,
                    photoUrl = player.photo,
                    modifier = Modifier.clickable {
                        navigateToDetail(player.id)
                    }
                )
            }
        }
    }
}