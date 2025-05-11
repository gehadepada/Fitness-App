package com.example.fitnessapp.data.datasources.firestore.model

    data class UserInfoDataModel(
    val email: String = "",
    val gender: String = "",
    val goal: String = "",
    val height: String = "",
    val level: String = "",
    val userName: String = "",
    val weight: Int = 0
)