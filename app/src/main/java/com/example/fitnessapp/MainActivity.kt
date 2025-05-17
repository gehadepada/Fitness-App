package com.example.fitnessapp
import com.example.fitnessapp.presentation.viewModels.themeView.ThemeViewModel
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitnessapp.presentation.navigation.MyAppNavigation
import com.example.fitnessapp.presentation.screens.waterScreen.ReminderScheduler
import android.Manifest
import androidx.compose.runtime.Composable
import com.example.fitnessapp.theme.FitnessAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppRoot(this)
        }
    }
}

@Composable
fun FitnessAppRoot(activity: ComponentActivity) {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

    FitnessAppTheme(darkTheme = isDarkTheme) {
        MyAppNavigation()

        val sharedPreferences = activity.getSharedPreferences("WaterReminderPrefs", android.content.Context.MODE_PRIVATE)
        val isReminderEnabled = sharedPreferences.getBoolean("reminder_enabled", false)

        if (isReminderEnabled) {
            ReminderScheduler.scheduleWaterReminder(activity)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }
}