package com.example.fitnessapp.domain.repo

import com.example.fitnessapp.data.datasources.remote.model.Muscles
import com.example.fitnessapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MusclesRepository {
    fun getAllMuscleExercises(): Flow<Resource<List<Muscles>>>
}