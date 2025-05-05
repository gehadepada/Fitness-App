package com.example.fitnessapp.presentation.screens.auth.user_data_package.weight

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnessapp.presentation.components.BackButton
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.components.FailedLoadingScreen
import com.example.fitnessapp.presentation.viewModels.userData_viewModel.UserDataState
import com.example.fitnessapp.presentation.viewModels.userData_viewModel.UserDataViewModel
import com.example.fitnessapp.theme.FitnessAppTheme
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WeightScreen(
    onWeight: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var weight by remember { mutableFloatStateOf(70f) }

    var loadTrigger by remember { mutableStateOf(false) }

    val userDataViewModel = hiltViewModel<UserDataViewModel>()
    val userDataState = userDataViewModel.userDataState.collectAsStateWithLifecycle()

    if (loadTrigger) {
        LaunchedEffect(Unit) {
            userDataViewModel.saveDataToFirestore(
                mapOf("weight" to weight.toInt())
            )
            loadTrigger = false
        }
    }

    when (userDataState.value) {
        is UserDataState.Error -> {
            FailedLoadingScreen()
        }

        UserDataState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        UserDataState.Success -> {
            LaunchedEffect(Unit) {
                onWeight()
                userDataViewModel.resetUserDataState()
            }
        }

        else -> Unit
    }

    // Main UI wrapped in scrollable column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp), // general padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "What is your weight?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top=20.dp,bottom = 20.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                text = "${weight.toInt()}",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = "Kg",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .padding(vertical = 16.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircularDial(20, 180)
                drawPointer(weight, 20, 180)
            }
        }

        Slider(
            value = weight,
            onValueChange = { weight = it },
            valueRange = 20f..180f,
            steps = 160,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        DefaultButton(onClick = { loadTrigger = true })
        BackButton(onclick = onBack)
    }
}

fun DrawScope.drawCircularDial(minWeight: Int, maxWeight: Int) {
    drawCircle(
        color = Color.Green,
        style = Stroke(width = 8f)
    )

    val radius = size.minDimension / 2
    val totalSteps = maxWeight - minWeight
    val stepAngle = 360f / totalSteps

    for (i in 0..totalSteps step 10) {
        val weightValue = minWeight + i
        val angle = i * stepAngle - 90f
        val x = (radius * cos(toRadians(angle.toDouble()))).toFloat() + center.x
        val y = (radius * sin(toRadians(angle.toDouble()))).toFloat() + center.y

        drawContext.canvas.nativeCanvas.drawText(
            weightValue.toString(),
            x,
            y,
            Paint().apply {
                textSize = 40f
                textAlign = Paint.Align.CENTER
            }
        )
    }
}

fun DrawScope.drawPointer(weight: Float, minWeight: Int, maxWeight: Int) {
    val totalSteps = maxWeight - minWeight
    val stepAngle = 360f / totalSteps
    val angle = (weight - minWeight) * stepAngle - 90f

    val radius = size.minDimension / 2
    val pointerLength = radius * 0.6f
    val x = (pointerLength * cos(toRadians(angle.toDouble()))).toFloat() + center.x
    val y = (pointerLength * sin(toRadians(angle.toDouble()))).toFloat() + center.y

    drawLine(
        color = Color.Green,
        start = center,
        end = Offset(x, y),
        strokeWidth = 8f
    )
}



@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        WeightScreen()
    }
}