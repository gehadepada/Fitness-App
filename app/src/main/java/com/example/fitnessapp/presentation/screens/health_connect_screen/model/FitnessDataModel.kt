package com.example.fitnessapp.presentation.screens.health_connect_screen.model

data class DataRecord(
    val metricValue: String,
    val dataType :  DataType,
    val toDatetime: String,
    val fromDatetime: String
)

enum class DataType{
    STEPS,MINS,DISTANCE,SLEEP
}