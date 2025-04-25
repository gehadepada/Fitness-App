package com.example.fitnessapp.data.datasources.repository

import com.example.fitnessapp.data.datasources.FirestoreMusclesDataSource
import com.example.fitnessapp.data.datasources.model.Muscles
import com.example.fitnessapp.domain.repo.MusclesRepository
import javax.inject.Inject

class MusclesRepoImp @Inject constructor(private val firestoreMusclesDataSource: FirestoreMusclesDataSource): MusclesRepository{
    override suspend fun getAllMuscleExercises(): List<Muscles> {
        return firestoreMusclesDataSource.getAllMuscles()
    }
}