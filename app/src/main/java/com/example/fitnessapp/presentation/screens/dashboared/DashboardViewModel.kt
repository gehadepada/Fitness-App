package com.example.fitnessapp.presentation.screens.dashboared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.datasources.remote.model.UserInfoDataModel
import com.example.fitnessapp.domain.repo.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _userInfoState = MutableStateFlow<UserInfoDataModel?>(null)
    val userInfoState: StateFlow<UserInfoDataModel?> = _userInfoState

    private val _maintenanceCalories = MutableStateFlow(0)
    val maintenanceCalories: StateFlow<Int> = _maintenanceCalories

    private val _goalCalories = MutableStateFlow(0)
    val goalCalories: StateFlow<Int> = _goalCalories

    private val _consumedCalories = MutableStateFlow(0)
    val consumedCalories: StateFlow<Int> = _consumedCalories

    private val _exerciseCalories = MutableStateFlow(0)
    val exerciseCalories: StateFlow<Int> = _exerciseCalories

    fun getUserData() {
        viewModelScope.launch {
            try {
                val userData = firebaseRepository.getUserData()
                _userInfoState.value = userData

                // Calculate maintenance calories based on user data
                userData?.let { userInfo ->
                    val maintenance = calculateMaintenanceCalories(
                        weight = userInfo.weight.toInt() ?: 0,
                        height = userInfo.height.toInt() ?: 0,
                        age = userInfo.age.toInt(),
                        gender = userInfo.gender ?: "male",
                        activityLevel = userInfo.level ?: "medium"
                    )

                    _maintenanceCalories.value = maintenance

                    // Calculate goal calories based on maintenance and user's goal
                    // This is where you would adjust for weight loss/gain goals
                    // For example, if the user wants to lose weight, multiply by 0.8
                    val goal = when (userInfo.goal?.lowercase()) {
                        "lose" -> (maintenance * 0.8).toInt()
                        "gain" -> (maintenance * 1.15).toInt()
                        else -> maintenance // maintain weight
                    }

                    _goalCalories.value = goal

                    // Initialize consumed calories and exercise calories
                    // In a real app, these would come from daily tracking
                    _consumedCalories.value =
                        0 // Default value, should be fetched from daily tracking
                    _exerciseCalories.value =
                        0  // Default value, should be fetched from daily tracking
                }
            } catch (e: Exception) {
                // Handle error
                println("Error fetching user data: ${e.message}")
            }
        }
    }

    /**
     * Calculates maintenance calories based on the user's details
     */
    private fun calculateMaintenanceCalories(
        weight: Int,
        height: Int,
        age: Int,
        gender: String,
        activityLevel: String
    ): Int {
        // Base formula for BMR (Basal Metabolic Rate)
        val bmr = if (gender.equals("female", ignoreCase = true)) {
            // For women: BMR = 10 * weight + 6.25 * height - 5 * age - 161
            (10 * weight + 6.25 * height - 5 * age - 161)
        } else {
            // For men: BMR = 10 * weight + 6.25 * height - 5 * age + 5
            (10 * weight + 6.25 * height - 5 * age + 5)
        }

        // Apply activity multiplier
        val activityMultiplier = when (activityLevel.lowercase()) {
            "low" -> 1.2
            "medium" -> 1.55
            "high" -> 1.9
            else -> 1.55 // Default to medium activity
        }

        return (bmr * activityMultiplier).toInt()
    }
}