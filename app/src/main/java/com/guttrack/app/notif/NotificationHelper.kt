package com.guttrack.app.notif

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.guttrack.app.MainActivity

object NotificationHelper {
    const val CHANNEL_REMINDERS = "meal_reminders"
    const val CHANNEL_CHECKINS = "checkins"

    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = context.getSystemService(NotificationManager::class.java)
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_REMINDERS, "Meal reminders", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Reminders to log breakfast, lunch and dinner"
            },
        )
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_CHECKINS, "Check-ins", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Snack and symptom check-in nudges"
            },
        )
    }

    fun show(context: Context, channel: String, notificationId: Int, title: String, text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            if (!granted) return
        }
        val openIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = android.app.PendingIntent.getActivity(
            context, notificationId, openIntent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE,
        )
        val notification = NotificationCompat.Builder(context, channel)
            .setSmallIcon(com.guttrack.app.R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        androidx.core.app.NotificationManagerCompat.from(context).notify(notificationId, notification)
    }
}
