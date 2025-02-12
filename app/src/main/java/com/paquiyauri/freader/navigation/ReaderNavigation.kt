package com.paquiyauri.freader.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paquiyauri.freader.screens.ReaderSplashScreen
import com.paquiyauri.freader.screens.home.Home
import com.paquiyauri.freader.screens.login.ReaderLoginScreen
import com.paquiyauri.freader.screens.search.ReaderSearchScreen
import com.paquiyauri.freader.screens.stats.ReaderStatsScreen
import com.paquiyauri.freader.screens.update.ReaderUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name){
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }
        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }
        composable(ReaderScreens.SearchScreen.name) {
            ReaderSearchScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }
        composable(ReaderScreens.UpdateScreen.name) {
            ReaderUpdateScreen(navController = navController)
        }
    }
}