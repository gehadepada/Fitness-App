package com.example.fitnessapp.ui.screens.food_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FoodBody(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()

    ) {

        items(10) {
            FoodItem()
        }

    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        FoodBody()
    }
}