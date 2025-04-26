package com.example.fitnessapp.presentation.screens.health_connect_screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.core.net.toUri
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import com.example.fitnessapp.presentation.screens.health_connect_screen.utils.HealthConnectUtils
import kotlinx.coroutines.launch

fun loadDataFromPreferences(context: Context): Map<String, String> {
    val sharedPreferences = context.getSharedPreferences("HealthConnectPrefs", Context.MODE_PRIVATE)
    return mapOf(
        "steps" to (sharedPreferences.getString("steps", "0") ?: "0"),
        "active_minutes" to (sharedPreferences.getString("active_minutes", "0") ?: "0"),
        "distance" to (sharedPreferences.getString("distance", "0") ?: "0"),
        "sleep_duration" to (sharedPreferences.getString("sleep_duration", "0") ?: "0")
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
fun HealthConnectScreen(onBack: () -> Unit = {}) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var steps by remember { mutableStateOf("50") }
    var mins by remember { mutableStateOf("50") }
    var distance by remember { mutableStateOf("50") }
    var sleepDuration by remember { mutableStateOf("50") }

    var showHealthConnectInstallPopup by remember { mutableStateOf(false) }
    val isAndroidVersionCompatible = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    LaunchedEffect(true) {
        val savedData = loadDataFromPreferences(context)
        steps = savedData["steps"] ?: "50"
        mins = savedData["active_minutes"] ?: "50"
        distance = savedData["distance"] ?: "50"
        sleepDuration = savedData["sleep_duration"] ?: "50"
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


        Surface(modifier = Modifier.fillMaxSize().padding(14.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (isAndroidVersionCompatible) {

                    CompactDataItem(label = "Steps", value = steps)
                    CompactDataItem(label = "Minutes", value = mins)
                    CompactDataItem(label = "Distance", value = distance)
                    CompactDataItem(label = "Sleep", value = sleepDuration)

                    Button(
                        onClick = {
                            scope.launch {
                                fetchAndSaveHealthData(context) { s, m, d, sd ->
                                    steps = s; mins = m; distance = d; sleepDuration = sd
                                }
                                Toast.makeText(context, "Data synced", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Sync Data",style = MaterialTheme.typography.bodyMedium)
                    }

                    if (showHealthConnectInstallPopup) {
                        AlertDialog(
                            onDismissRequest = { showHealthConnectInstallPopup = false },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        showHealthConnectInstallPopup = false
                                        val uriString = "market://details?id=com.google.android.apps.healthdata"
                                        context.startActivity(Intent(Intent.ACTION_VIEW, uriString.toUri()))
                                    }
                                ) {
                                    Text("Install")
                                }
                            },
                            title = { Text(text = "Alert") },
                            text = { Text(text = "Health Connect is not installed") }
                        )
                    }

                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Your Android version is not compatible with Health Connect.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Error Icon",
                            tint = Color.Red,
                            modifier = Modifier.size(55.dp).padding(bottom = 10.dp)
                        )

                        Button(
                            onClick = onBack,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {
                            Text(text = "Go Back")
                        }
                    }
                }
            }
        }



    }
@Composable
fun CompactDataItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

suspend fun fetchAndSaveHealthData(
    context: Context,
    onComplete: (String, String, String, String) -> Unit
) {
    val mins = HealthConnectUtils.readMinsForInterval(7).last().metricValue
    val steps = HealthConnectUtils.readStepsForInterval(7).last().metricValue
    val distance = HealthConnectUtils.readDistanceForInterval(7).last().metricValue
    val sleepDuration = HealthConnectUtils.readSleepSessionsForInterval(7).last().metricValue

    saveDataToPreferences(context, steps, mins, distance, sleepDuration)
    onComplete(steps, mins, distance, sleepDuration)
}