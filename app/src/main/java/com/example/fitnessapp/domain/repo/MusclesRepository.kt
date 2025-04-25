package com.example.fitnessapp.domain.repo

import com.example.fitnessapp.data.datasources.model.Muscles

interface MusclesRepository {
    suspend fun getAllMuscleExercises(): List<Muscles>
}