package com.example.fitnessapp.domain.repo

import com.example.fitnessapp.data.datasources.model.Muscles
import com.google.firebase.firestore.DocumentSnapshot

interface FirebaseRepository {

    suspend fun getAllMuscleExercises(): List<Muscles>

    suspend fun saveUserData(userData: Map<String, Any>)

    suspend fun getUserData(onSuccess: (DocumentSnapshot) -> Unit, onFailure: (Exception) -> Unit = {})
}