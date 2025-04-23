package com.example.fitnessapp.presentation.screens.muscle_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.domain.repo.MusclesRepository
import com.example.fitnessapp.utils.Resource
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

    fun fetchMuscles() {
        viewModelScope.launch(Dispatchers.IO) {
            muscleRepo.getAllMuscleExercises()
                .collect { resource ->
                    _muscleState.value = MuscleState.Loading

//                    try {
//                        _muscleState.value = MuscleState.Success(resource)
//                    }catch(e: Exception) {
//                        _muscleState.value = MuscleState.Error(e.message.toString())
//                    }

                    when (resource) {
                        is Resource.Loading -> {
                            _muscleState.value = MuscleState.Loading
                        }

                        is Resource.Success -> {
                            _muscleState.value = MuscleState.Success(resource.data)
                        }

                        is Resource.Error -> {
                            Log.e(
                                "MusclesViewModel Al-qiran",
                                "Error fetching muscles: ${resource.message}"
                            )
                            _muscleState.value = MuscleState.Error(resource.message)

                        }
                    }
                }
        }
    }
}