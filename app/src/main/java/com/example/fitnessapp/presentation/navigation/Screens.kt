package com.example.fitnessapp.presentation.navigation

import android.util.Log

sealed class Screens(val route: String) {

    data object SplashScreen: Screens("splash")

    data object SignUpScreen: Screens("sign_up")

    data object LogInScreen: Screens("login")

    data object LevelScreen: Screens("level")

    data object FoodScreen: Screens("food")

    data object HeightScreen: Screens("height")

    data object GenderScreen: Screens("gender")

    data object DashBoardScreen: Screens("dashBoard")

    data object WeightScreen: Screens("weight")

    data object SetGoalsScreen: Screens("set_goals")

    data object ExerciseScreen: Screens("exercises")

    data object ExercisesDetails: Screens("exercises_details/{id}") {
        fun passId(id: Int): String {
            return "exercises_details/$id"
        }
    }

    data object WaterScreen: Screens("water")

    data object SearchBtnScreen: Screens("search")

    data object FoodSearchScreen: Screens("food_search")
    data object FoodSelectItemScreen: Screens("food_select_item/{foodName}/{calories}") {
        fun passFoodNameAndCalories(foodName: String, calories: String): String {
            return "food_select_item/$foodName/$calories"
        }
    }

    data object HealthScreen: Screens("health")

    data object HealthConnectScreen: Screens("health_connect")

    data object ScanFoodScreen: Screens("scan_food")


    data object ProfileScreen: Screens("profile")

    data object UserProfileScreen : Screens("user_profile")
    data object AppPermissionsScreen : Screens("app_permissions")
    data object AboutAppScreen : Screens("about_app")
}