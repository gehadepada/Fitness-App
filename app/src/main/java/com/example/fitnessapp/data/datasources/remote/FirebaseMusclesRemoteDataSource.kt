package com.example.fitnessapp.data.datasources.remote

import com.example.fitnessapp.data.datasources.remote.model.Muscles
import com.example.fitnessapp.utils.Resource
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class FirebaseMusclesRemoteDataSource @Inject constructor(private val muscleRef: DatabaseReference) {

    fun getAllMuscles(): Flow<Resource<List<Muscles>>> = flow {
        emit(Resource.Loading())

        try {
            val snapshot = muscleRef.get().await()

            if (!snapshot.exists()) {
                emit(Resource.Error("No data available"))
                return@flow
            }
            val muscles = snapshot.getValue<List<Muscles>>() ?: emptyList()
            emit(Resource.Success(muscles))
        } catch (e: Exception) {
            if (e is IOException || e is DatabaseException) {
                emit(Resource.Error("Please turn on your internet connection"))
            } else {
                emit(Resource.Error("Error: ${e.message}"))
            }
        }
    }
}