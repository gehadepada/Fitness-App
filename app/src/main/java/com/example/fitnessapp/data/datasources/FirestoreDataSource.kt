package com.example.fitnessapp.data.datasources

import android.util.Log
import com.example.fitnessapp.data.datasources.model.Muscles
import com.example.fitnessapp.utils.firestore_utils.FirestoreUtils.getCurrentUserId
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(private val firestore: FirebaseFirestore) {

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

    fun saveUserDataFirestore(
        userData: Map<String, Any>
    ) {
        val userId = getCurrentUserId() ?: return
        try {
            firestore.collection("Users").document(userId)
                .set(userData, SetOptions.merge())
                .addOnFailureListener {
                    throw NoSuchElementException("Sorry we can't add the User")
                }
                .addOnSuccessListener {
                    Log.d("Al-qiran", "Success from Firestore DataSource")
                }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getUserDataFires(
        onSuccess: (DocumentSnapshot) -> Unit,
        onFailure: (Exception) -> Unit = {}
    ) {
        val userId = getCurrentUserId() ?: return

        firestore.collection("Users").document(userId)
            .get()
            .addOnSuccessListener { onSuccess(it) }
            .addOnFailureListener { onFailure(it) }
    }
}