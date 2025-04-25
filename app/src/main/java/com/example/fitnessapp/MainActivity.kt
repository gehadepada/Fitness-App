package com.example.fitnessapp

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitnessapp.presentation.navigation.MyAppNavigation
import com.example.fitnessapp.presentation.screens.waterScreen.ReminderScheduler
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import android.Manifest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppTheme {
                MyAppNavigation(this)

                val sharedPreferences = getSharedPreferences("WaterReminderPrefs", MODE_PRIVATE)
                val interval = sharedPreferences.getInt("interval_hours", 2)
                val isReminderEnabled = sharedPreferences.getBoolean("reminder_enabled", false)

                if (isReminderEnabled) {
                    ReminderScheduler.scheduleWaterReminder(this)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                            1001
                        )
                    }
                }
            }
        }
    }
}