package com.example.fitnessapp.presentation.screens.food_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.presentation.screens.food_screen.components.FoodBody
import com.example.fitnessapp.theme.FitnessAppTheme

@Composable
fun FoodScreen() {
    FoodBody()
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        FoodScreen()
    }
}