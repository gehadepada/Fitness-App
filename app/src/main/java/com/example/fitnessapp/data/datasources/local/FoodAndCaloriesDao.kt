package com.example.fitnessapp.data.datasources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodAndCaloriesDao {
    @Insert
    suspend fun insertFoodAndCalories(foodAndCalories: FoodAndCalories)

    @Delete
    suspend fun deleteFoodAndCalories(foodAndCalories: FoodAndCalories)

    @Query("SELECT * FROM food_and_calories WHERE substr(date, 1, 10) = :date")
    suspend fun getFoodAndCaloriesByDate(date: String): List<FoodAndCalories>


}