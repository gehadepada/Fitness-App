package com.example.fitnessapp.data.datasources.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "food_and_calories")
data class FoodAndCalories(
    @PrimaryKey(autoGenerate = false)
    val date: String = SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS", Locale.getDefault()).format(Date()),
    val foodName: String,
    val totalAmount: Int,
    val calories: Double
)
