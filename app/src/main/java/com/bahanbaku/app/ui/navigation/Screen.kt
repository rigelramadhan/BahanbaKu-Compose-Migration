package com.bahanbaku.app.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
}