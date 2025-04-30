package com.example.fitnessapp.data.datasources.firestore.repository

import com.example.fitnessapp.data.datasources.firestore.FirestoreDataSource
import com.example.fitnessapp.data.datasources.firestore.model.Muscles
import com.example.fitnessapp.domain.repo.FirebaseRepository
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class FirebaseRepoImp @Inject constructor(private val firestoreDataSource: FirestoreDataSource): FirebaseRepository{
    override suspend fun getAllMuscleExercises(): List<Muscles> {
        return firestoreDataSource.getAllMuscles()
    }


    override suspend fun saveUserData(
        userData: Map<String, Any>
    ) {
        return firestoreDataSource.saveUserDataFirestore(userData)
    }

    override suspend fun getUserData(
        onSuccess: (DocumentSnapshot) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        return firestoreDataSource.getUserDataFires(onSuccess, onFailure)
    }
}