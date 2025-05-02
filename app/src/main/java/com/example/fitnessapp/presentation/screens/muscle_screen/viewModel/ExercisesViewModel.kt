package com.example.fitnessapp.presentation.screens.muscle_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.domain.repo.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val muscleRepo: FirebaseRepository) :
    ViewModel() {

    private val _muscleState = MutableStateFlow<MuscleState>(MuscleState.Loading)
    val muscleState = _muscleState.asStateFlow()

    init {
        loadMuscles()
    }

    fun loadMuscles() {

        _muscleState.value = MuscleState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _muscleState.value = MuscleState.Success(muscleRepo.getAllMuscleExercises())
            } catch (e: Exception) {
                _muscleState.value = MuscleState.Error(e.message ?: "Unknown error")
            }

        }
    }
}