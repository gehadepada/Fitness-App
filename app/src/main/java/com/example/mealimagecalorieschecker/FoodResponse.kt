package com.example.mealimagecalorieschecker

import kotlinx.serialization.Serializable

@Serializable
data class FoodResponse(
    val items: List<String>,
    val count: Int,
    val success: Boolean
)