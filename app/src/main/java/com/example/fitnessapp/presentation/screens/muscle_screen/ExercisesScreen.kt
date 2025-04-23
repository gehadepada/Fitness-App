package com.example.fitnessapp.presentation.screens.muscle_screen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fitnessapp.data.datasources.remote.model.Exercises
import com.example.fitnessapp.data.datasources.remote.model.Muscles
import com.example.fitnessapp.presentation.screens.muscle_screen.viewModel.ExercisesViewModel
import com.example.fitnessapp.presentation.screens.muscle_screen.viewModel.MuscleState


@Composable
fun ExercisesScreen(onExercise:(id: Int) -> Unit) {

    val muscleViewModel = hiltViewModel<ExercisesViewModel>()
    val musclesState by muscleViewModel.muscleState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        muscleViewModel.fetchMuscles()
    }


    when(musclesState) {
        is MuscleState.Error -> {
            Log.e("Al-qiran", "Error ${(musclesState as MuscleState.Error).message}")
        }
        is MuscleState.Loading -> {
            Log.d("Al-qiran", "Loading Loading Loading")
        }
        is MuscleState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f)) // Pushes content down

                GridItems((musclesState as MuscleState.Success).muscles, onExercise =  onExercise)

                Spacer(modifier = Modifier.weight(1f)) // Adds spacing below content
            }
        }
        else -> {
            Log.d("Al-qiran", "Entered")

        }
    }
}

@Composable
fun GridItems(
    muscles: List<Muscles>,
    onExercise:(id: Int) -> Unit
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
                        onExercise = onExercise
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
    onExercise: (id: Int) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onExercise(id)
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