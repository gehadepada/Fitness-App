package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.ui.components.TopBar
import com.example.fitnessapp.ui.navigation.MyAppNavigation
import com.example.fitnessapp.ui.screens.food_screen.FoodScreen
import com.example.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppTheme {
//                Scaffold(
//                    topBar = { TopBar(title = "Hello") }
//                ) {
//
//                    MyAppNavigation(Modifier.padding(it))
//                }
            FoodScreen()
            }

        }
    }
}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        Scaffold(
            topBar = { TopBar(title = "Hello") }
        ) {

            MyAppNavigation(Modifier.padding(it))
        }
    }
}