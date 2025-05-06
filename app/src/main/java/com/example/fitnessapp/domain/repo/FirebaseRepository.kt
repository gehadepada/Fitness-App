package com.example.fitnessapp.domain.repo

import com.example.fitnessapp.data.datasources.firestore.model.Muscles
import com.example.fitnessapp.presentation.screens.healthy_recipes_screen.model.RecipesModel
import com.google.firebase.firestore.DocumentSnapshot

interface FirebaseRepository {

    suspend fun getAllMuscleExercises(): List<Muscles>

    suspend fun saveUserData(userData: Map<String, Any>)

    suspend fun getUserData(onSuccess: (DocumentSnapshot) -> Unit, onFailure: (Exception) -> Unit = {})

    suspend fun getAllRecipes(): List<RecipesModel>
}