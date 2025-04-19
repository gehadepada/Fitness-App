package com.example.fitnessapp.presentation.screens.muscle_screen

import android.net.Uri
import android.util.Log
import com.example.fitnessapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fitnessapp.presentation.screens.muscle_screen.viewModel.ExercisesViewModel
import com.google.gson.Gson

var musclesData = emptyList<Muscles>()

@Composable
fun ExercisesScreen(navController: NavHostController) {

    val muscleViewModel = viewModel<ExercisesViewModel>()
    val muscles by muscleViewModel.muscles.collectAsStateWithLifecycle()

    LaunchedEffect(muscles) {
        if (muscles.isNotEmpty()) {
            musclesData = muscles
            Log.d("Exercise", "${musclesData[1]}")

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f)) // Pushes content down

        val exercises = listOf(
            "Chest", "Shoulders", "Biceps",
            "Triceps", "Back",
            "Forearm", "Legs", "Abdominal"
        )

        val imageIds = listOf(
            R.drawable.chest, R.drawable.shoulders, R.drawable.biceps,
            R.drawable.triceps, R.drawable.upper_back, R.drawable.forearm,
            R.drawable.hamstrings, R.drawable.abdominal
        )

        GridItems(muscles, navController)

        Spacer(modifier = Modifier.weight(1f)) // Adds spacing below content
    }
}

@Composable
fun GridItems(
    muscles: List<Muscles>,
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        // Display 3 items per row
        for (row in muscles.chunked(3)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { (id, muscleName, exerciseImage, exercises) ->
                    ExerciseItem(
                        id = id,
                        name = muscleName,
                        imageUrl = exerciseImage,
                        exercises = exercises,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(
    id: Int,
    name: String,
    imageUrl: String,
    exercises: List<Exercises>,
    navController: NavHostController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("exerciseDetails/$id")
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
            placeholder = painterResource(id = R.drawable.ex_exercise),
            error = painterResource(id = R.drawable.baseline_notifications_24)
        )
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

data class Muscles(
    val id: Int = 0,
    val muscle: String = "",
    val exerciseImage: String = "",
    val exercises: List<Exercises> = emptyList()
)

data class Exercises(
    val name: String = "",
    val description: String = "",
    val reps: String = "",
    val sets: String = "",
    val image1: String = "",
    val image2: String = "",
)