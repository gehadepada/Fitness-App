package com.example.fitnessapp.utils.firestore_utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
object FirestoreUtils {
    private val firestore = FirebaseFirestore.getInstance()

    fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    fun saveUserData(fieldName: String, value: Any, onSuccess: () -> Unit = {}, onFailure: (Exception) -> Unit = {}) {
        val userId = getCurrentUserId() ?: return

        firestore.collection("Users").document(userId)
            .set(mapOf(fieldName to value), SetOptions.merge())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getUserData(onSuccess: (DocumentSnapshot) -> Unit, onFailure: (Exception) -> Unit = {}) {
        val userId = getCurrentUserId() ?: return

        firestore.collection("Users").document(userId)
            .get()
            .addOnSuccessListener { onSuccess(it) }
            .addOnFailureListener { onFailure(it) }
    }
}