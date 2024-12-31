package com.example.fitnessapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.screens.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.ui.screens.splash_screen.SplashScreen

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen {
                navController.navigate("level") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        composable("level") {
            PhysicalActivityLevel()
        }
    }
}
