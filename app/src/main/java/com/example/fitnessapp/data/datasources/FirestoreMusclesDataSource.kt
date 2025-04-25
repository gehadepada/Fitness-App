package com.example.fitnessapp.data.datasources

import com.example.fitnessapp.data.datasources.model.Muscles
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreMusclesDataSource @Inject constructor(private val firestore: FirebaseFirestore) {

    suspend fun getAllMuscles(): List<Muscles> {
        return try {
            firestore.collection("Muscles")
                .get()
                .await()
                .map { it.toObject(Muscles::class.java) }
        } catch (e: Exception) {
            throw e
        }
    }
}