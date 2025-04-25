package com.example.fitnessapp.data.datasources

import android.util.Log
import com.example.fitnessapp.data.datasources.model.Muscles
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreMusclesDataSource @Inject constructor(private val firestore: FirebaseFirestore) {

    suspend fun getAllMuscles(): List<Muscles> {
        return try {
            val cacheSnapshot = firestore.collection("Muscles")
                .orderBy("id")
                .get(Source.CACHE)
                .await()
            if (!cacheSnapshot.isEmpty) {
                cacheSnapshot.map { it.toObject(Muscles::class.java) }
            }

            val snapshot = firestore.collection("Muscles")
                .orderBy("id")
                .get()
                .await()

            if (!snapshot.isEmpty) {
                snapshot.map { it.toObject(Muscles::class.java) }
            } else {
                throw NoSuchElementException("No muscle data found in cache or server.")
            }
        } catch (e: FirebaseFirestoreException) {
            throw e
        }
    }
}