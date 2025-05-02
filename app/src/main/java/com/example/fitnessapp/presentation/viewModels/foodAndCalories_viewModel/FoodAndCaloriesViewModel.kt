package com.example.fitnessapp.presentation.viewModels.foodAndCalories_viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.datasources.local.FoodAndCalories
import com.example.fitnessapp.data.datasources.local.FoodAndCaloriesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodAndCaloriesViewModel @Inject constructor(private val dao: FoodAndCaloriesDao): ViewModel() {

    private val _foodAndCaloriesState = MutableStateFlow<FoodAndCaloriesState>(FoodAndCaloriesState.None)
    val foodAndCaloriesState = _foodAndCaloriesState.asStateFlow()

    fun insertFoodAndCalories(foodAndCalories: FoodAndCalories) {
        viewModelScope.launch(Dispatchers.IO) {
            _foodAndCaloriesState.value = FoodAndCaloriesState.Loading
            try {
                dao.insertFoodAndCalories(foodAndCalories)
                _foodAndCaloriesState.value = FoodAndCaloriesState.Success
            } catch(e: Exception) {
                _foodAndCaloriesState.value = FoodAndCaloriesState.Error(e.suppressedExceptions.toString())
            }
        }
    }


}