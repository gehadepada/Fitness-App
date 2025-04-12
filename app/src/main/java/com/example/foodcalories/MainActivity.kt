package com.example.foodcalories
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "SearchBtn") {
                composable("SearchBtn") { SearchView(navController) }
                composable("foodSearch") { Navigation(navController) }  // Make sure 'Navigation' is the composable you want
            }
        }
    }
}