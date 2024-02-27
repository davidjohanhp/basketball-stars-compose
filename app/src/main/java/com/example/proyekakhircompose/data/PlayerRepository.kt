package com.example.proyekakhircompose.data

import com.example.proyekakhircompose.model.Player
import com.example.proyekakhircompose.model.PlayerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PlayerRepository {
    private val players = mutableListOf<Player>()

    init {
        if (players.isEmpty()) {
            PlayerData.players.forEach {
                players.add(Player(it.id, it.name, it.description, it.awards, it.photo))
            }
        }
    }

    fun getPlayers(): Flow<List<Player>> {
        return flowOf(PlayerData.players)
    }

    fun getPlayerById(playerId: String): Player {
        return PlayerData.players.first {
            it.id == playerId
        }
    }

    fun searchPlayer(query: String): List<Player>{
        return PlayerData.players.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: PlayerRepository? = null

        fun getInstance(): PlayerRepository =
            instance ?: synchronized(this) {
                PlayerRepository().apply {
                    instance = this
                }
            }
    }
}