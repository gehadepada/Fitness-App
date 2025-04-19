package com.example.fitnessapp.presentation.screens.muscle_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.presentation.screens.muscle_screen.Muscles
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.reflect.InvocationTargetException

class ExercisesViewModel: ViewModel() {
    private val _muscles = MutableStateFlow<List<Muscles>>(emptyList())
    val muscles = _muscles.asStateFlow()

    private val database = Firebase.database("https://fitness-d7ae0-default-rtdb.firebaseio.com/")

    init {
        getMuscles()
    }

    private fun getMuscles() {
        val tempMuscles = mutableListOf<Muscles>()
        database.getReference("Muscles")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        task.result.getValue<List<Muscles>>()?.let {
                            Log.d("Firebase", it.toString())
                            tempMuscles.addAll(it)
                        }
                    } catch (e: Exception) {
                        // Handle parsing error
                        Log.e("Firebase", "Error parsing data: ${e.message}")
                        e.printStackTrace()
                    }
                } else {
                    // Handle task failure
                    val exception = task.exception ?: return@addOnCompleteListener
                    Log.e("Firebase", "Error getting data: ${exception.message}")

                    // Check if it's an InvocationTargetException
                    if (exception is InvocationTargetException) {
                        val rootCause = exception.cause
                        Log.e("Firebase", "Root cause: ${rootCause?.message}")
                        rootCause?.printStackTrace()
                    } else {
                        exception.printStackTrace()
                    }
                }
                _muscles.value = tempMuscles
            }
    }
}