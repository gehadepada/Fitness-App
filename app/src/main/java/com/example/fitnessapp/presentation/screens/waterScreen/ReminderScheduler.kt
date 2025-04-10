package com.example.fitnessapp.presentation.screens.waterScreen

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object ReminderScheduler {
    private const val WORK_TAG = "WaterReminderWork"

    fun scheduleWaterReminder(context: Context) {
        val sharedPreferences = context.getSharedPreferences("WaterReminderPrefs", Context.MODE_PRIVATE)
        val intervalHours = sharedPreferences.getInt("interval_hours", 3)

        val workRequest = PeriodicWorkRequestBuilder<WaterReminderWorker>(
            intervalHours.toLong(), TimeUnit.HOURS
        ).addTag(WORK_TAG).build()

        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniquePeriodicWork(
            WORK_TAG,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}