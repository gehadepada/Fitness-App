package com.example.fitnessapp.presentation.viewModels.foodAndCalories_viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.datasources.local.FoodAndCalories
import com.example.fitnessapp.data.datasources.local.FoodAndCaloriesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodAndCaloriesViewModel @Inject constructor(private val dao: FoodAndCaloriesDao): ViewModel() {
    fun insertFoodAndCalories(foodAndCalories: FoodAndCalories) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertFoodAndCalories(foodAndCalories)
        }
    }
}