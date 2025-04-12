package com.example.foodcalories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment


@Composable
fun SearchView(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.navigate("foodSearch")
            },
            modifier = Modifier
                .wrapContentWidth()  // Fit the button width to the content
                .align(Alignment.Center)  // Center the button
                .shadow(8.dp, clip = false),  // Add shadow
            content = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White // Optional: Set icon color
                )
                Text(
                    text = "Add Food",
                    style = TextStyle(fontSize = 18.sp, color = Color.White),
                    modifier = Modifier
                        .padding(end = 8.dp) // Optional: Adjust text padding
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5)) // Customize button color
        )
    }
}