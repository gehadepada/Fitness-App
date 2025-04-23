package com.example.fitnessapp.presentation.screens.dashboared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.navigation.Screens

@Composable
fun AddWaterSection(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 15.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(21.dp))
            .clickable {
                navController.navigate(Screens.WaterScreen.route)
            }
            .padding(16.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_add_circle_24),
            contentDescription = "Header Image",
            modifier = Modifier
                .height(70.dp)
                .width(70.dp)

        )
        Text(
            text = "Add Water",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}