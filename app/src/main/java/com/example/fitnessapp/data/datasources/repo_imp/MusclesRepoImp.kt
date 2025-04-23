package com.example.fitnessapp.data.datasources.repo_imp

import com.example.fitnessapp.data.datasources.remote.FirebaseMusclesRemoteDataSource
import com.example.fitnessapp.data.datasources.remote.model.Muscles
import com.example.fitnessapp.domain.repo.MusclesRepository
import com.example.fitnessapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusclesRepoImp @Inject constructor(private val firebaseMusclesRemoteDataSource: FirebaseMusclesRemoteDataSource): MusclesRepository{
    override fun getAllMuscleExercises(): Flow<Resource<List<Muscles>>> {
        return firebaseMusclesRemoteDataSource.getAllMuscles()
    }
}