package com.example.fitnessapp.data.datasources.firestore.repository

import com.example.fitnessapp.data.datasources.firestore.FirestoreDataSource
import com.example.fitnessapp.data.datasources.firestore.model.FoodCaloriesModel
import com.example.fitnessapp.data.datasources.firestore.model.Muscles
import com.example.fitnessapp.domain.repo.FirebaseRepository
import com.example.fitnessapp.presentation.screens.healthy_recipes_screen.model.RecipesModel
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

    override suspend fun getAllRecipes(): List<RecipesModel> {
        return firestoreDataSource.getAllRecipes()
    }

    override suspend fun getAllFoodsWithCalories(): List<FoodCaloriesModel> {
        return firestoreDataSource.getAllFoodsWithCalories()
    }
}