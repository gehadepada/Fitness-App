package com.example.fitnessapp.presentation.navigation

import com.example.fitnessapp.presentation.viewModels.themeView.ThemeViewModel
import com.example.fitnessapp.presentation.screens.profile_screen_package.user_info.UserProfileInfoScreen
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.presentation.components.CustomBottomBar
import com.example.fitnessapp.presentation.components.TopBar
import com.example.fitnessapp.presentation.components.TopBarWithLogo
import com.example.fitnessapp.presentation.screens.dashboared.DashboardScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.height_select.NumberPickerDemo
import com.example.fitnessapp.presentation.screens.auth.user_data_package.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.presentation.screens.auth.login_screen.LoginScreen
import com.example.fitnessapp.presentation.screens.auth.signup_screen.SignUpScreen
import com.example.fitnessapp.presentation.screens.dashboared.components.ProfileTopBar
import com.example.fitnessapp.presentation.screens.add_food_package.add_food_way_screen.AddFoodSelectOption
import com.example.fitnessapp.presentation.screens.add_food_package.scan_meal_screen.ScanFood
import com.example.fitnessapp.presentation.screens.muscle_screen.ExerciseDetailScreen
import com.example.fitnessapp.presentation.screens.muscle_screen.ExercisesScreen
import com.example.fitnessapp.presentation.screens.profile_screen_package.AppPermissionsScreen
import com.example.fitnessapp.presentation.screens.profile_screen_package.ProfileScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.set_goals_screen.SetGoalsScreen
import com.example.fitnessapp.presentation.screens.splash_screen.SplashScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.weight.WeightScreen
import com.example.fitnessapp.presentation.screens.add_food_package.search_food_and_add_screens.FoodSelectedItem
import com.example.fitnessapp.presentation.screens.add_food_package.search_food_and_add_screens.SearchFoodScreen
import com.example.fitnessapp.presentation.screens.auth.user_data_package.age_screen.AgeSelectScreen
import com.example.fitnessapp.presentation.screens.healthy_recipes_screen.RecipeDetailScreen
import com.example.fitnessapp.presentation.screens.healthy_recipes_screen.RecipesScreen
import com.example.fitnessapp.presentation.screens.food_history_screen.FoodHistoryScreen
import com.example.fitnessapp.presentation.screens.profile_screen_package.AboutAppScreen
import com.example.fitnessapp.presentation.screens.profile_screen_package.HealthPermissionStatusScreen
import com.example.fitnessapp.presentation.screens.waterScreen.WaterTrackerScreen
import com.example.fitnessapp.presentation.viewModels.auth_viewModel.AuthViewModel


@Composable
fun MyAppNavigation(context: Context, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    val topBar = remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(-1) }


    val authViewModel = hiltViewModel<AuthViewModel>()
    val themeViewModel = hiltViewModel<ThemeViewModel>()
    val currentUser = authViewModel.currentUser

    Scaffold(
        topBar = {
            when (topBar.value) {
                "TopBarWithLogo" -> {
                    TopBarWithLogo()
                }

                "dashboard" -> {
                    ProfileTopBar()
                }

                "profile" -> {
                    TopBar("Profile") { navController.popBackStack() }
                }

                "recipes" -> {
                    TopBar("Recipes") { navController.popBackStack() }
                }

                "muscle_exercises" -> {
                    TopBar("Muscle Exercises") { navController.popBackStack() }
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

                "foodHistory" -> {
                    TopBar("Food History") { navController.popBackStack() }
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

                "third_party" -> {
                    TopBar("Third-party data") { navController.popBackStack() }
                }


                else -> Unit
            }
        },
        bottomBar = {
            if (selectedIndex != -1) {
                CustomBottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = {
                        selectedIndex = it
                        when (selectedIndex) {
                            0 -> navController.navigate(Screens.DashBoardScreen.route)
                            1 -> navController.navigate(Screens.ExerciseScreen.route)
                            2 -> navController.navigate(Screens.FoodSearchScreen.route)
                            3 -> navController.navigate(Screens.FoodHistoryScreen.route)
                            4 -> navController.navigate(Screens.ProfileScreen.route)
                        }
                    },
                )
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.SplashScreen.route,
            modifier = modifier.padding(paddingValues)
        ) {

            // Splash
            composable(Screens.SplashScreen.route) {
                if (selectedIndex != -1) selectedIndex = -1
                SplashScreen {
                    if (currentUser != null) {
                        navController.navigate(Screens.DashBoardScreen.route) {
                            popUpTo(Screens.SplashScreen.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screens.LogInScreen.route) {
                            popUpTo(Screens.SplashScreen.route) { inclusive = true }
                        }
                    }
                }
            }


            // Login and Signup
            composable(Screens.LogInScreen.route) {
                if (selectedIndex != -1) selectedIndex = -1
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
                if (selectedIndex != -1) selectedIndex = -1
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


            // User Info
            composable(Screens.GenderScreen.route) {
                topBar.value = "TopBarWithLogo"
                selectedIndex = -1
                GenderScreen(onGender = {
                    navController.navigate(Screens.AgeScreen.route)
                })
            }

            composable(Screens.AgeScreen.route) {
                topBar.value = "TopBarWithLogo"
                AgeSelectScreen(
                    onHeight = {
                        navController.navigate(Screens.HeightScreen.route)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screens.HeightScreen.route) {
                topBar.value = "TopBarWithLogo"
                NumberPickerDemo(
                    onHeight = {
                        navController.navigate(Screens.WeightScreen.route)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screens.WeightScreen.route) {
                topBar.value = "TopBarWithLogo"
                WeightScreen(
                    onWeight = {
                        navController.navigate(Screens.LevelScreen.route)
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screens.LevelScreen.route) {
                topBar.value = "TopBarWithLogo"
                PhysicalActivityLevel(
                    onPersonLevel = {
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


            // Dashboard
            composable(Screens.DashBoardScreen.route) {
                topBar.value = "dashboard"
                selectedIndex = 0
                DashboardScreen(
                    onRecipes = { navController.navigate(Screens.RecipesScreen.route) },
                    onWorkouts = { navController.navigate(Screens.ExerciseScreen.route) },
                    onFoodHistory = { navController.navigate(Screens.FoodHistoryScreen.route) },
                    onSearch = { navController.navigate(Screens.SearchBtnScreen.route) },
                    onHealth = { navController.navigate(Screens.HealthConnectScreen.route) },
                    onProfile = { navController.navigate(Screens.ProfileScreen.route) },
                    onWater = { navController.navigate(Screens.WaterScreen.route) },
                )
            }









            composable(Screens.WaterScreen.route) {
                topBar.value = "water"
                WaterTrackerScreen(context = context)
            }

            composable(Screens.ExerciseScreen.route) {
                topBar.value = "exercises"
                selectedIndex = 1
                ExercisesScreen(onExercise = { id ->
                    navController.navigate(Screens.ExercisesDetails.passId(id))
                })
            }
            composable(Screens.ExercisesDetails.route) { backStackEntry ->
                topBar.value = "muscle_exercises"
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 5
                ExerciseDetailScreen(id = id)
            }

            composable(Screens.SearchBtnScreen.route) {
                topBar.value = "addFood"
                AddFoodSelectOption(onAddFood = {
                    navController.navigate(Screens.FoodSearchScreen.route)
                }, onScanFood = {
                    navController.navigate(Screens.ScanFoodScreen.route)
                })
            }

            composable(Screens.HealthScreen.route) {
                topBar.value = "Health"
                HealthConnectScreen(onBack = {
                    navController.popBackStack()
                })
            }


            composable(Screens.ScanFoodScreen.route) {
                topBar.value = "addFood"
                ScanFood()
            }


            // Search and search components
            composable(Screens.FoodSearchScreen.route) {
                selectedIndex = 2
                SearchFoodScreen(onSearchFood = { foodName, calories ->
                    navController.navigate(
                        Screens.FoodSelectItemScreen.passFoodNameAndCalories(
                            foodName,
                            calories
                        )
                    )
                })
            }
            composable(Screens.FoodSelectItemScreen.route) { backStackEntry ->

                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                val calories = backStackEntry.arguments?.getString("calories") ?: ""
                FoodSelectedItem(foodName = foodName, calories = calories)
            }

            // health connect
            composable(Screens.HealthConnectScreen.route) {
                HealthConnectScreen(onBack = {
                    navController.popBackStack()
                })
            }


            // profile and profile components
            composable(Screens.ProfileScreen.route) {
                topBar.value = "profile"
                selectedIndex = 4
                ProfileScreen(
                    themeViewModel = themeViewModel,
                    onUser = { navController.navigate(Screens.UserProfileScreen.route) },
                    onPermissions = { navController.navigate(Screens.AppPermissionsScreen.route) },
                    onAbout = { navController.navigate(Screens.AboutAppScreen.route) },
                    onLogout = {
                        authViewModel.signOut()
                        navController.navigate(Screens.LogInScreen.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onThirdParty = { navController.navigate(Screens.ThirdPartyScreen.route) })
            }

            composable(Screens.UserProfileScreen.route) {
                topBar.value = "userProfile"
                UserProfileInfoScreen(
                    onEditProfileClick = {
                        navController.navigate(Screens.GenderScreen.route) {
                            popUpTo(0) { inclusive = true }
                        }
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


            // For Recipes
            composable(Screens.RecipesScreen.route) {
                topBar.value = "recipes"
                RecipesScreen(onClick = { id ->
                    navController.navigate(Screens.RecipesDetailsScreen.passId(id))
                })
            }
            composable(Screens.RecipesDetailsScreen.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                RecipeDetailScreen(id)
            }



            composable(Screens.FoodHistoryScreen.route) {
                topBar.value = "foodHistory"
                selectedIndex = 3
                FoodHistoryScreen()
            }

            composable(Screens.ThirdPartyScreen.route) {
                topBar.value = "third_party"
                HealthPermissionStatusScreen()
            }


        }
    }
}