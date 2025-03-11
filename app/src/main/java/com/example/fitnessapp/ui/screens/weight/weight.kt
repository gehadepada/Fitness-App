package com.example.fitnessapp.ui.screens.weight


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessApp()
        }
    }
}

@Composable
fun FitnessApp() {
    var weight by remember { mutableStateOf(56f) } // Initial weight
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        Text("What is your weight?", fontSize = 29.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        Text("${weight.toInt()} kg", fontSize = 32.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        CircularDial(weight = weight)
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
            value = weight,
            onValueChange = { weight = it },
            valueRange = 55f..58f,
            steps = 2,
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Handle button click */ },

            ) {
            Text("Let's go", color = Color.Black, fontSize = 18.sp)
        }
    }
}

@Composable
fun CircularDial(weight: Float) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircularDial()
            drawPointer(weight)
        }
    }
}

fun DrawScope.drawCircularDial() {
    drawCircle(
        color = Color.Green,
        style = Stroke(width = 8f)
    )
    val radius = size.minDimension / 2
    val steps = listOf(55, 56, 57, 58)
    steps.forEachIndexed { index, step ->
        val angle = (index - 1) * 90f // 90-degree intervals
        val x = (radius * cos(Math.toRadians(angle.toDouble()))).toFloat() + center.x
        val y = (radius * sin(Math.toRadians(angle.toDouble()))).toFloat() + center.y
        drawContext.canvas.nativeCanvas.drawText(
            step.toString(),
            x,
            y,
            android.graphics.Paint().apply {

                textSize = 40f
                textAlign = android.graphics.Paint.Align.CENTER
            }
        )
    }
}

fun DrawScope.drawPointer(weight: Float) {
    val angle = (weight - 55) * 90f - 90f // Calculate angle based on weight
    val radius = size.minDimension / 2
    val pointerLength = radius * 0.6f
    val x = (pointerLength * cos(Math.toRadians(angle.toDouble()))).toFloat() + center.x
    val y = (pointerLength * sin(Math.toRadians(angle.toDouble()))).toFloat() + center.y
    drawLine(
        color = Color.Green,
        start = center,
        end = Offset(x, y),
        strokeWidth = 8f
    )
}
