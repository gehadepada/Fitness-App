package com.example.fitnessapp.ui.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.screens.food_screen.FoodScreen
import com.example.fitnessapp.ui.screens.gender_screen.GenderScreen
import com.example.fitnessapp.ui.screens.height_select.NumberPickerDemo
import com.example.fitnessapp.ui.screens.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.ui.screens.signup_screen.SignUpScreen
import com.example.fitnessapp.ui.screens.splash_screen.SplashScreen

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Screens.SignUpScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = true }
                }
            }
        }

        composable(Screens.SignUpScreen.route) {
            SignUpScreen(
                onSignUp = { username, email, password ->
                    navController.navigate(Screens.GenderScreen.route) {
                        popUpTo(Screens.SignUpScreen.route) { inclusive = true }
                    }
                },
                goToLogin = {
                    navController.navigate(Screens.LogInScreen.route) {
                        popUpTo(Screens.SignUpScreen.route)
                    }
                }
            )
        }

        composable(Screens.LevelScreen.route) {
            PhysicalActivityLevel(
                onPersonLevel = { personLevel ->
                    navController.navigate(Screens.FoodScreen.route) {
                        popUpTo(Screens.LevelScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screens.GenderScreen.route) {
            GenderScreen {gender ->
                navController.navigate(Screens.HeightScreen.route)
            }
        }

        composable(Screens.FoodScreen.route) {
            FoodScreen()
        }

        composable(Screens.LogInScreen.route) {
            LoginScreen(
                onLogin = {
                    navController.navigate(Screens.FoodScreen.route) {
                        popUpTo(0)
                    }
                },
                goToSignUp = {
                    navController.navigate(Screens.SignUpScreen.route)
                }
            )
        }

        composable(Screens.HeightScreen.route) {
            NumberPickerDemo(
                onHeight = {
                    navController.navigate(Screens.LevelScreen.route)
                }
            )
        }


    }
}


