package com.example.fitnessapp.ui.screens.waterScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import com.example.fitnessapp.ui.components.TopBarWithLogo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterTrackerScreen(context: Context) {

    var showDialog by remember { mutableStateOf(false) }
    var customAmount by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var startHour by remember { mutableStateOf(sharedPreferences.getInt("start_hour", 8)) }
    var startMinute by remember { mutableStateOf(sharedPreferences.getInt("start_minute", 0)) }
    var drinkTimes by remember { mutableStateOf(sharedPreferences.getInt("drink_times", 1)) }
    var interval by remember { mutableStateOf(sharedPreferences.getInt("interval_hours", 1)) }
    var totalVolume by remember { mutableStateOf(sharedPreferences.getInt("total_volume", 0)) }


    val waterOptions = listOf(
        Pair(250, R.drawable.small_bottle),
        Pair(500, R.drawable.medium_bottle),
        Pair(1000, R.drawable.large_bottle)
    )
  //  val drinkOptions = (3..6).toList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
        .clickable { focusManager.clearFocus() },
       horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarWithLogo()
        Text(
            text = "Set Daily Water Habit.",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { showTimePicker = true }) {
            Text("Start Time: ${"%02d:%02d".format(startHour, startMinute)}", fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Interval: $interval hours",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp
        )
        Slider(
            value = interval.toFloat(),
            onValueChange = { interval = it.toInt() },
            valueRange = 1f..9f,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))


        Box {
            Button(onClick = { expanded = true }) {
                Text("Drinks per Day: $drinkTimes", fontSize = 12.sp)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                (1..10).forEach { number ->
                    DropdownMenuItem(
                        text = { Text(text = "$number") },
                        onClick = {
                            drinkTimes = number
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        val savedGoal = sharedPreferences.getInt("daily_goal",2000)
        var dailyGoal by remember { mutableStateOf(savedGoal) }

        OutlinedTextField(
            value = dailyGoal.toString(),
            onValueChange = {
                dailyGoal = it.toIntOrNull() ?: dailyGoal
                            sharedPreferences.edit().putInt("daily_goal",dailyGoal).apply()
                            },
            label = { Text("Daily Goal (ml)", fontSize = 12.sp) },
            modifier = Modifier
                .height(75.dp).width(230.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.height(75.dp).width(230.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(12.dp)
        ) {
          Row(
                modifier = Modifier,

              horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        text = "Total Volume",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Text( modifier = Modifier.padding(start = 8.dp),
                        text = "$totalVolume ml",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.width(22.dp))
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End

                ) {
                    IconButton(onClick = {
                        totalVolume = 0
                        sharedPreferences.edit().putInt("total_volume", totalVolume).apply()
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Clear", tint = Color.White)
                    }

                }
            }}
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.padding(start = 20.dp).fillMaxWidth().height(90.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                waterOptions.forEach { (amount, imageRes) ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clickable { totalVolume += amount }
                        )
                        Text(
                            text = "$amount ml",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 12.sp
                        )
                    }
                }

                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.custom_bottle),
                        contentDescription = null,
                        modifier = Modifier.clickable { showDialog = true }
                    )
                    Text(
                        text = "Custom Amount",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 8.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                sharedPreferences.edit()
                    .putInt("start_hour", startHour)
                    .putInt("start_minute", startMinute)
                    .putInt("drink_times", drinkTimes)
                    .putInt("interval_hours", interval)
                    .putInt("total_volume", totalVolume)
                    .apply()

                ReminderScheduler.scheduleWaterReminder(context)

                Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Save")
        }
    }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Enter Custom Amount") },
                text = {
                    OutlinedTextField(
                        value = customAmount,
                        onValueChange = {
                            customAmount = it.filter { char -> char.isDigit() }
                        },
                        label = { Text("Amount in ml") }
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            val amount = customAmount.toIntOrNull()
                            if (amount != null && amount > 0) {
                                totalVolume += amount
                                showDialog = false
                                customAmount = ""
                            }
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
        if (showTimePicker) {
            TimePickerDialog(
                context,
                { _, hour, minute ->
                    startHour = hour
                    startMinute = minute
                    showTimePicker = false
                },
                startHour,
                startMinute,
                true
            ).show()

        }
    }


















