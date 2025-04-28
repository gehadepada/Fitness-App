package com.example.fitnessapp.presentation.navigation

import com.example.fitnessapp.presentation.screens.user_data_package.gender_screen.GenderScreen
import com.example.fitnessapp.presentation.screens.health_connect_screen.HealthConnectScreen
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.presentation.components.TopBar
import com.example.fitnessapp.presentation.components.TopBarWithLogo
import com.example.fitnessapp.presentation.screens.dashboared.ProfileScreen
import com.example.fitnessapp.presentation.screens.food_screen.FoodScreen
import com.example.fitnessapp.presentation.screens.user_data_package.height_select.NumberPickerDemo
import com.example.fitnessapp.presentation.screens.user_data_package.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.presentation.screens.auth.login_screen.LoginScreen
import com.example.fitnessapp.presentation.screens.auth.signup_screen.SignUpScreen
import com.example.fitnessapp.presentation.screens.dashboared.components.ProfileTopBar
import com.example.fitnessapp.presentation.screens.food_calories.Navigation
import com.example.fitnessapp.presentation.screens.food_calories.SearchView
import com.example.fitnessapp.presentation.screens.scan_meal_screen.ScanFood
import com.example.fitnessapp.presentation.screens.muscle_screen.ExerciseDetailScreen
import com.example.fitnessapp.presentation.screens.muscle_screen.ExercisesScreen
import com.example.fitnessapp.presentation.screens.profile_screen.UserProfile
import com.example.fitnessapp.presentation.screens.user_data_package.set_goals_screen.SetGoalsScreen
import com.example.fitnessapp.presentation.screens.splash_screen.SplashScreen
import com.example.fitnessapp.presentation.screens.user_data_package.weight.WeightScreen
import com.example.fitnessapp.presentation.screens.waterScreen.WaterTrackerScreen
import com.google.firebase.auth.FirebaseAuth

/**
 * the Navigation Graph
 * SignUp -> Gender -> Height -> Level -> Weight -> SetGoal -> DashBoard
 * Login -> DashBoard
 */

@Composable
fun MyAppNavigation(context: Context, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    val topBar = remember { mutableStateOf("") }

    val currentUser = FirebaseAuth.getInstance().currentUser

    Scaffold(
        topBar = {
            when (topBar.value) {
                "TopBarWithLogo" -> {
                    TopBarWithLogo()
                }

                "profile" -> {
                    ProfileTopBar()
                }

                "food" -> {
                    TopBar("Food", navController = navController)
                }

                "exercises" -> {
                    TopBar("Exercises", navController = navController)
                }

                "water" -> {
                    TopBar("Add Water", navController = navController)
                }

                "addFood" -> {
                    TopBar("Add Food", navController = navController)
                }

                else -> Unit
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = if (currentUser != null) Screens.DashBoardScreen.route else Screens.SplashScreen.route,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(Screens.SplashScreen.route) {
                SplashScreen {
                    navController.navigate(Screens.LogInScreen.route) {
                        popUpTo(Screens.SplashScreen.route) { inclusive = true }
                    }
                }
            }

            composable(Screens.LogInScreen.route) {
                topBar.value = "TopBarWithLogo"

                LoginScreen(
                    onLogin = {
                        navController.navigate(Screens.DashBoardScreen.route) {
                            popUpTo(0)
                        }
                    },
                    goToSignUp = {
                        navController.navigate(Screens.SignUpScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }

            composable(Screens.SignUpScreen.route) {
                topBar.value = "TopBarWithLogo"

                SignUpScreen(
                    onSignUp = { username, email, password ->
                        navController.navigate(Screens.GenderScreen.route) {
                            popUpTo(0)
                        }
                    },
                    goToLogin = {
                        navController.navigate(Screens.LogInScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }

            composable(Screens.DashBoardScreen.route) {
                topBar.value = "profile"
                ProfileScreen(
                    navController
                )
            }

            composable(Screens.LevelScreen.route) {
                topBar.value = "TopBarWithLogo"

                PhysicalActivityLevel(
                    onPersonLevel = { personLevel ->
                        navController.navigate(Screens.WeightScreen.route)
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screens.GenderScreen.route) {
                topBar.value = "TopBarWithLogo"

                GenderScreen { gender ->
                    navController.navigate(Screens.HeightScreen.route)
                }
            }

            composable(Screens.WeightScreen.route) {
                topBar.value = "TopBarWithLogo"

                WeightScreen(
                    onWeight = {
                        navController.navigate(Screens.SetGoalsScreen.route)
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screens.SetGoalsScreen.route) {
                topBar.value = "TopBarWithLogo"

                SetGoalsScreen(
                    onSetGoals = {
                        navController.navigate(Screens.DashBoardScreen.route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screens.FoodScreen.route) {
                topBar.value = "food"
                FoodScreen()
            }



            composable(Screens.HeightScreen.route) {
                topBar.value = "TopBarWithLogo"
                NumberPickerDemo(
                    onHeight = {
                        navController.navigate(Screens.LevelScreen.route)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screens.WaterScreen.route) {
                topBar.value = "water"
                WaterTrackerScreen(context = context)
            }

            composable(Screens.ExerciseScreen.route) {
                topBar.value = "exercises"
                ExercisesScreen(onExercise = { id ->
                    navController.navigate(Screens.ExercisesDetails.passId(id))
                })
            }

            composable(Screens.ExercisesDetails.route) { backStackEntry ->
                topBar.value = ""
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 5
                ExerciseDetailScreen(id = id)
            }

            composable(Screens.SearchBtnScreen.route) {
                topBar.value = "addFood"
                SearchView(onAddFood = {
                    navController.navigate(Screens.FoodSearchScreen.route)
                }, onScanFood = {
                    navController.navigate(Screens.ScanFoodScreen.route)
                })
            }

            composable(Screens.HealthConnectScreen.route) {
                HealthConnectScreen(onBack = {
                    navController.popBackStack()
                })
            }


            composable(Screens.FoodSearchScreen.route) { Navigation() }


            composable(Screens.HealthScreen.route) {
                topBar.value = "Health"
                HealthConnectScreen()
            }

            composable(Screens.ProfileScreen.route) {
                topBar.value = "profile"
                UserProfile()
            }

            composable(Screens.ScanFoodScreen.route) {
                topBar.value = "addFood"
                ScanFood()
            }
        }
    }
}