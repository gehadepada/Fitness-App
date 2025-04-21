package com.example.fitnessapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp.R

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun TopBar(title: String, navController: NavHostController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        modifier = Modifier.padding(horizontal = 16.dp),

        title = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },

        navigationIcon = {
            Image(
                modifier = Modifier.size(27.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "back",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            )
        },

        actions = {
            Image(
                modifier = Modifier
                    .size(46.dp)
                    .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clickable {},
                painter = painterResource(id = R.drawable.ic_face),
                contentDescription = "user image",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),

                )
        }

    )
}

