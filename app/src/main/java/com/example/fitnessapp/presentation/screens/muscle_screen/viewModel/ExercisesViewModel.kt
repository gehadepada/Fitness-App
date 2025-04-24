package com.example.fitnessapp.presentation.screens.muscle_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.domain.repo.MusclesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val muscleRepo: MusclesRepository) :
    ViewModel() {

    private val _muscleState = MutableStateFlow<MuscleState>(MuscleState.Entered)
    val muscleState = _muscleState.asStateFlow()

    fun loadMuscles() {
        viewModelScope.launch(Dispatchers.IO) {
            _muscleState.value = MuscleState.Loading
            try {
                _muscleState.value = MuscleState.Success(muscleRepo.getAllMuscleExercises())
                Log.d("Al-qiran", "Data Loaded Successfully")
            } catch (e: Exception) {
                _muscleState.value = MuscleState.Error(e.message ?: "Unknown error")
                Log.d("Al-qiran", "Error: ${e.message}")
            }

        }
    }
}