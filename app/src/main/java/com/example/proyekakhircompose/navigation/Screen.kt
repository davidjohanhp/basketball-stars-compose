package com.example.proyekakhircompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailPlayer : Screen("home/{playerId}") {
        fun createRoute(playerId: String) = "home/$playerId"
    }
}