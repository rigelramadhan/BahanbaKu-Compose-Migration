package com.bahanbaku.app.ui.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Home: Screen("home")
}