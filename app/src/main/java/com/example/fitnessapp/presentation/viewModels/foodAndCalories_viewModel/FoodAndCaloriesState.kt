package com.example.fitnessapp.presentation.viewModels.foodAndCalories_viewModel

sealed class FoodAndCaloriesState {
    data object None: FoodAndCaloriesState()
    data object Success: FoodAndCaloriesState()
    data object Loading: FoodAndCaloriesState()
    data class Error(val error: String): FoodAndCaloriesState()
}