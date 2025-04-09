package com.example.fitnessapp

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitnessapp.ui.navigation.MyAppNavigation
import com.example.fitnessapp.ui.navigation.Screens.GenderScreen
import android.Manifest
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.example.fitnessapp.ui.screens.set_goals.GoalsScreen
import com.example.fitnessapp.ui.screens.splash_screen.SplashScreen
import com.example.fitnessapp.ui.screens.waterScreen.ReminderScheduler
import com.example.fitnessapp.ui.screens.waterScreen.WaterTrackerScreen
import com.example.fitnessapp.presentation.navigation.MyAppNavigation
import com.example.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppTheme {
                MyAppNavigation()
            }
        }
                Scaffold(
//                    topBar = { TopBar(title = "Hello") }
                ) {
                    val sharedPreferences = getSharedPreferences("WaterReminderPrefs", MODE_PRIVATE)
                    val interval = sharedPreferences.getInt("interval_hours", 2)
                    val isReminderEnabled = sharedPreferences.getBoolean("reminder_enabled", false)

                    if (isReminderEnabled) {
                        ReminderScheduler.scheduleWaterReminder(this) // تشغيل التذكير تلقائيًا
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

                    MyAppNavigation(Modifier.padding(it))
                    WaterTrackerScreen(context = this)
                }
            }
        }

    }
}