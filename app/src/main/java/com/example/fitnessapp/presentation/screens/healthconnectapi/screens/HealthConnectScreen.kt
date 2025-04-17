package com.example.fitnessapp.presentation.screens.healthconnectapi.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.core.net.toUri
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import com.example.fitnessapp.presentation.screens.healthconnectapi.utils.HealthConnectUtils
import kotlinx.coroutines.launch


fun loadDataFromPreferences(context: Context): Map<String, String> {
    val sharedPreferences = context.getSharedPreferences("HealthConnectPrefs", Context.MODE_PRIVATE)
    return mapOf(
        "steps" to (sharedPreferences.getString("steps", "0") ?: "0"),
        "active_minutes" to (sharedPreferences.getString("active_minutes", "0") ?: "0"),
        "distance" to (sharedPreferences.getString("distance", "0") ?: "0"),
        "sleep_duration" to (sharedPreferences.getString("sleep_duration", "00:00") ?: "00:00")
    )
}

fun saveDataToPreferences(context: Context, steps: String, mins: String, distance: String, sleepDuration: String) {
    val sharedPreferences = context.getSharedPreferences("HealthConnectPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit {
        putString("steps", steps)
        putString("active_minutes", mins)
        putString("distance", distance)
        putString("sleep_duration", sleepDuration)
    }
}

@Composable
fun HealthConnectScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var steps by remember { mutableStateOf("0") }
    var mins by remember { mutableStateOf("0") }
    var distance by remember { mutableStateOf("0") }
    var sleepDuration by remember { mutableStateOf("00:00") }

    var showHealthConnectInstallPopup by remember { mutableStateOf(false) }

    // Load saved data once
    LaunchedEffect(true) {
        val savedData = loadDataFromPreferences(context)
        steps = savedData["steps"] ?: "0"
        mins = savedData["active_minutes"] ?: "0"
        distance = savedData["distance"] ?: "0"
        sleepDuration = savedData["sleep_duration"] ?: "00:00"
    }

    val requestPermissions = rememberLauncherForActivityResult(PermissionController.createRequestPermissionResultContract()) { granted ->
        if (granted.containsAll(HealthConnectUtils.PERMISSIONS)) {
            scope.launch {
                fetchAndSaveHealthData(context) { s, m, d, sd ->
                    steps = s; mins = m; distance = d; sleepDuration = sd
                }
            }
        } else {
            Toast.makeText(context, "Permissions are rejected", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(true) {
        when (HealthConnectUtils.checkForHealthConnectInstalled(context)) {
            HealthConnectClient.SDK_UNAVAILABLE -> Toast.makeText(context, "Health Connect is not available on this device", Toast.LENGTH_SHORT).show()
            HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED -> showHealthConnectInstallPopup = true
            HealthConnectClient.SDK_AVAILABLE -> {
                if (HealthConnectUtils.checkPermissions()) {
                    fetchAndSaveHealthData(context) { s, m, d, sd ->
                        steps = s; mins = m; distance = d; sleepDuration = sd
                    }
                } else {
                    requestPermissions.launch(HealthConnectUtils.PERMISSIONS)
                }
            }
        }
    }

    Scaffold {
        Surface(modifier = Modifier.fillMaxSize().padding(it)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DataItem(label = "Steps", value = steps, duration = "Today")
                        DataItem(label = "Active minutes", value = mins, duration = "Today")
                        DataItem(label = "Distance", value = distance, duration = "Today")
                        DataItem(label = "Sleep", value = sleepDuration, duration = "Last session")
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                    ) {
                        Card(
                            modifier = Modifier.padding(8.dp).fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),  // Rounded corners for a sleek look
                            elevation = CardDefaults.cardElevation(10.dp)  // Added shadow effect for depth
                        ) {
                            Text(
                                text = "Sync Data",
                                fontSize = 18.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF6200EE), RoundedCornerShape(12.dp)) // Purple theme for action button
                                    .padding(14.dp)
                                    .clickable {
                                        scope.launch {
                                            fetchAndSaveHealthData(context) { s, m, d, sd ->
                                                steps = s; mins = m; distance = d; sleepDuration = sd
                                            }
                                            // Show a toast after data sync
                                            Toast.makeText(context, "Latest steps: $steps", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            )
                        }
                    }

                    if (showHealthConnectInstallPopup) {
                        AlertDialog(
                            onDismissRequest = { showHealthConnectInstallPopup = false },
                            confirmButton = {
                                ClickableText(
                                    text = AnnotatedString("Install"),
                                    onClick = {
                                        showHealthConnectInstallPopup = false
                                        val uriString = "market://details?id=com.google.android.apps.healthdata"
                                        context.startActivity(Intent(Intent.ACTION_VIEW, uriString.toUri()))
                                    }
                                )
                            },
                            title = { Text(text = "Alert") },
                            text = { Text(text = "Health Connect is not installed") }
                        )
                    }
                }
            }
        }
    }
}

suspend fun fetchAndSaveHealthData(context: Context, onComplete: (String, String, String, String) -> Unit) {
    val mins = HealthConnectUtils.readMinsForInterval(7).last().metricValue
    val steps = HealthConnectUtils.readStepsForInterval(7).last().metricValue
    val distance = HealthConnectUtils.readDistanceForInterval(7).last().metricValue
    val sleepDuration = HealthConnectUtils.readSleepSessionsForInterval(7).last().metricValue

    saveDataToPreferences(context, steps, mins, distance, sleepDuration)
    onComplete(steps, mins, distance, sleepDuration)
}

@Composable
fun DataItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    duration: String,
) {
    Card(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(12.dp)  // Rounded corners for consistency
    ) {
        Column(
            modifier = Modifier
//                .background(Color.White)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = label, color = Color.Black, fontSize = 16.sp)
            Divider(color = Color.Black, thickness = 1.dp)
            Text(text = duration, fontSize = 12.sp, color = Color.Gray)
            Text(text = value, fontSize = 22.sp, maxLines = 1, modifier = Modifier.align(Alignment.Start))
        }
    }
}