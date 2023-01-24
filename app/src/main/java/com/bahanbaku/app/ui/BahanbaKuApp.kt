package com.bahanbaku.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bahanbaku.app.ui.navigation.Screen
import com.bahanbaku.app.ui.screen.home.HomeScreen
import com.bahanbaku.app.ui.screen.login.LoginScreen

@Composable
fun BahanbaKuApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = {}, navigateToCategory ={}, navigateToLogin = {
                    LaunchedEffect(Unit) {
                        navController.navigate(Screen.Login.route)
                    }
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(navigateToRegister = {

                }, navigateToHome = {
                    LaunchedEffect(Unit) {
                        navController.navigate(Screen.Home.route)
                    }
                })
            }
        }
    }
}