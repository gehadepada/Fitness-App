package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fitnessapp.ui.navigation.MyAppNavigation
import com.example.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppTheme {
<<<<<<< HEAD
                MyAppNavigation()
=======
                Scaffold(
//                    topBar = { TopBar(title = "Hello") }
                ) {
                    MyAppNavigation(Modifier.padding(it))
                }
>>>>>>> cc38a0b74d3d62590d421b3909a495ae4f9f6142
            }
        }
    }
}