package com.example.fitnessapp.ui.screens.waterScreen


import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.fitnessapp.R
import androidx.core.content.ContextCompat

class WaterReminderWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        sendNotification(applicationContext)
        return Result.success()
    }
    private fun sendNotification(context: Context) {
        val notificationManager = ContextCompat.getSystemService(context, NotificationManager::class.java)

        notificationManager?.let {
            val builder = NotificationCompat.Builder(context, "water_reminder_channel")
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle("تذكير شرب الماء 💧")
                .setContentText("حان وقت شرب الماء! لا تنسى صحتك 💙")
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            it.notify(1, builder.build())
        }
    }

}