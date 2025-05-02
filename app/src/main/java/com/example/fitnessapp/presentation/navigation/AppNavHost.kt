package com.example.fitnessapp.presentation.navigation

import UserProfileScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.gender_screen.GenderScreen
import com.example.fitnessapp.presentation.screens.health_connect_screen.HealthConnectScreen
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.presentation.components.CustomBottomBar
import com.example.fitnessapp.presentation.components.TopBar
import com.example.fitnessapp.presentation.components.TopBarWithLogo
import com.example.fitnessapp.presentation.screens.dashboared.DashboardScreen
import com.example.fitnessapp.presentation.screens.food_screen.FoodScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.height_select.NumberPickerDemo
import com.example.fitnessapp.presentation.screens.auth.user_data_package.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.presentation.screens.auth.login_screen.LoginScreen
import com.example.fitnessapp.presentation.screens.auth.signup_screen.SignUpScreen
import com.example.fitnessapp.presentation.screens.dashboared.components.ProfileTopBar
import com.example.fitnessapp.presentation.screens.food_calories.SearchView
import com.example.fitnessapp.presentation.screens.scan_meal_screen.ScanFood
import com.example.fitnessapp.presentation.screens.muscle_screen.ExerciseDetailScreen
import com.example.fitnessapp.presentation.screens.muscle_screen.ExercisesScreen
import com.example.fitnessapp.presentation.screens.profile_screen.AboutAppScreen
import com.example.fitnessapp.presentation.screens.profile_screen.AppPermissionsScreen
import com.example.fitnessapp.presentation.screens.profile_screen.ProfileScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.set_goals_screen.SetGoalsScreen
import com.example.fitnessapp.presentation.screens.splash_screen.SplashScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.weight.WeightScreen
import com.example.fitnessapp.presentation.screens.food_calories.FoodSelectedItem
import com.example.fitnessapp.presentation.screens.food_calories.SearchFoodScreen
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
    var selectedIndex by remember { mutableIntStateOf(-1) }

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
                    TopBar("Food") { navController.popBackStack() }
                }

                "exercises" -> {
                    TopBar("Exercises") { navController.popBackStack() }
                }

                "water" -> {
                    TopBar("Add Water") { navController.popBackStack() }
                }

                "addFood" -> {
                    TopBar("Add Food") { navController.popBackStack() }
                }

                "userProfile" -> {
                    TopBar("User Profile") { navController.popBackStack() }
                }

                "appPermissions" -> {
                    TopBar("App Permissions") { navController.popBackStack() }
                }

                "aboutApp" -> {
                    TopBar("About App") { navController.popBackStack() }
                }

                else -> Unit
            }
        },
        bottomBar = {
            if (selectedIndex != -1) {
                CustomBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = {
                        if (selectedIndex != it) {
                            selectedIndex = it
                            when (selectedIndex) {
                                0 -> navController.navigate(Screens.DashBoardScreen.route)
                                3 -> navController.navigate(Screens.ProfileScreen.route)
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = if (currentUser != null) Screens.DashBoardScreen.route else Screens.SplashScreen.route,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(Screens.SplashScreen.route) {
                if(selectedIndex != -1) selectedIndex = -1
                SplashScreen {
                    navController.navigate(Screens.LogInScreen.route) {
                        popUpTo(Screens.SplashScreen.route) { inclusive = true }
                    }
                }
            }

            composable(Screens.LogInScreen.route) {
                if(selectedIndex != -1) selectedIndex = -1
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
                if(selectedIndex != -1) selectedIndex = -1
                topBar.value = "TopBarWithLogo"

                SignUpScreen(
                    onSignUp = {
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
                if(selectedIndex != 0) selectedIndex = 0
                DashboardScreen(
                    navController
                )
            }

            composable(Screens.LevelScreen.route) {
                topBar.value = "TopBarWithLogo"

                PhysicalActivityLevel(
                    onPersonLevel = {
                        navController.navigate(Screens.WeightScreen.route)
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screens.GenderScreen.route) {
                topBar.value = "TopBarWithLogo"

                GenderScreen {
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



            composable(Screens.HealthScreen.route) {
                topBar.value = "Health"
                HealthConnectScreen()
            }

            composable(Screens.ScanFoodScreen.route) {
                topBar.value = "addFood"
                ScanFood()
            }


            // Search and search components
            composable(Screens.FoodSearchScreen.route) {

                SearchFoodScreen(onSearchFood = { foodName, calories ->
                    navController.navigate(Screens.FoodSelectItemScreen.passFoodNameAndCalories(foodName, calories))
                })
            }
            composable(Screens.FoodSelectItemScreen.route) { backStackEntry ->

                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                val calories = backStackEntry.arguments?.getString("calories") ?: ""
                FoodSelectedItem(foodName = foodName, calories = calories)
            }



            // profile and profile components
            composable(Screens.ProfileScreen.route) {
                topBar.value = "profile"
                selectedIndex = 3
                ProfileScreen(
                    onUser = { navController.navigate(Screens.UserProfileScreen.route) },
                    onPermissions = { navController.navigate(Screens.AppPermissionsScreen.route) },
                    onAbout = { navController.navigate(Screens.AboutAppScreen.route) }
                )
            }

            composable(Screens.UserProfileScreen.route) {
                topBar.value = "userProfile"
                UserProfileScreen(
                    onLogoutClick = {
                        navController.navigate(Screens.LogInScreen.route) {
                            popUpTo(Screens.DashBoardScreen.route)
                        }
                    },
                    onEditProfileClick = {

                    })
            }
            composable(Screens.AppPermissionsScreen.route) {
                topBar.value = "appPermissions"
                AppPermissionsScreen()
            }
            composable(Screens.AboutAppScreen.route) {
                topBar.value = "aboutApp"
                AboutAppScreen()
            }
        }
    }
}