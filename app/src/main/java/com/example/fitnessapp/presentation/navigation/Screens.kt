package com.example.fitnessapp.presentation.navigation


sealed class Screens(val route: String) {

    // splash Screen
    data object SplashScreen: Screens("splash")

    // Login and Signup
    data object SignUpScreen: Screens("sign_up")
    data object LogInScreen: Screens("login")

    // User Info
    data object GenderScreen: Screens("gender")
    data object AgeScreen: Screens("age")
    data object HeightScreen: Screens("height")
    data object WeightScreen: Screens("weight")
    data object LevelScreen: Screens("level")
    data object SetGoalsScreen: Screens("set_goals")

    // Dashboard
    data object DashBoardScreen: Screens("dashBoard")



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

    data object FoodHistoryScreen: Screens("food_history")

    data object HealthConnectScreen: Screens("health_connect")

    data object ScanFoodScreen: Screens("scan_food")


    data object ProfileScreen: Screens("profile")

    data object UserProfileScreen : Screens("user_profile")
    data object AppPermissionsScreen : Screens("app_permissions")
    data object AboutAppScreen : Screens("about_app")


    data object RecipesScreen: Screens("recipes")
    data object RecipesDetailsScreen: Screens("recipes_details/{id}") {
        fun passId(id: Int): String {
            return "recipes_details/$id"
        }
    }
}