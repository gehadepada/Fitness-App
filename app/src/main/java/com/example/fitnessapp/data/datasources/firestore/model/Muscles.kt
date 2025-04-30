package com.example.fitnessapp.data.datasources.firestore.model


data class Muscles(
    val id: Int = 0,
    val muscle: String = "",
    val exerciseImage: String = "",
    val exercises: List<Exercises> = emptyList()
)
