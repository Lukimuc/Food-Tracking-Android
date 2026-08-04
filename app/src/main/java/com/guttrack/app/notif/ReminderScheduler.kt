package com.guttrack.app.notif

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.settings.UserSettings
import java.time.LocalDateTime
import java.time.ZoneId

object ReminderScheduler {
    const val REQ_BREAKFAST = 1001
    const val REQ_LUNCH = 1002
    const val REQ_DINNER = 1003
    const val REQ_SNACK = 1004

    const val EXTRA_REQUEST_CODE = "requestCode"
    const val EXTRA_TITLE = "title"
    const val EXTRA_TEXT = "text"
    const val EXTRA_HOUR = "hour"
    const val EXTRA_MINUTE = "minute"
    const val EXTRA_MEAL_TYPE = "mealType"

    fun canScheduleExact(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return true
        val am = context.getSystemService(AlarmManager::class.java)
        return am.canScheduleExactAlarms()
    }

    fun requestExactAlarmPermission(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, Uri.parse("package:${context.packageName}"))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun rescheduleAll(context: Context, settings: UserSettings) {
        val (bh, bm) = parseHourMinute(settings.reminderBreakfast)
        val (lh, lm) = parseHourMinute(settings.reminderLunch)
        val (dh, dm) = parseHourMinute(settings.reminderDinnerStart)

        scheduleSingle(context, REQ_BREAKFAST, bh, bm, "It's breakfast time — log what you ate", MealType.BREAKFAST.name)
        scheduleSingle(context, REQ_LUNCH, lh, lm, "It's lunch time — log what you ate", MealType.LUNCH.name)
        scheduleSingle(context, REQ_DINNER, dh, dm, "It's dinner time — log what you ate", MealType.DINNER.name)

        if (settings.snackAskEnabled) {
            scheduleSingle(context, REQ_SNACK, 15, 30, "Time for your afternoon snack check-in", null)
        } else {
            cancel(context, REQ_SNACK)
        }
    }

    fun scheduleSingle(context: Context, requestCode: Int, hour: Int, minute: Int, message: String, mealTypeName: String?) {
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(EXTRA_REQUEST_CODE, requestCode)
            putExtra(EXTRA_TITLE, "GutTrack")
            putExtra(EXTRA_TEXT, message)
            putExtra(EXTRA_HOUR, hour)
            putExtra(EXTRA_MINUTE, minute)
            if (mealTypeName != null) putExtra(EXTRA_MEAL_TYPE, mealTypeName)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val trigger = nextTriggerMillis(hour, minute)
        val am = context.getSystemService(AlarmManager::class.java)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !am.canScheduleExactAlarms()) {
                am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pendingIntent)
            } else {
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pendingIntent)
            }
        } catch (e: SecurityException) {
            am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pendingIntent)
        }
    }

    fun cancel(context: Context, requestCode: Int) {
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        context.getSystemService(AlarmManager::class.java).cancel(pendingIntent)
        pendingIntent.cancel()
    }

    fun cancelAll(context: Context) {
        cancel(context, REQ_BREAKFAST)
        cancel(context, REQ_LUNCH)
        cancel(context, REQ_DINNER)
        cancel(context, REQ_SNACK)
    }

    fun parseHourMinute(hhmm: String): Pair<Int, Int> {
        val parts = hhmm.split(":")
        val h = parts.getOrNull(0)?.toIntOrNull() ?: 0
        val m = parts.getOrNull(1)?.toIntOrNull() ?: 0
        return h to m
    }

    private fun nextTriggerMillis(hour: Int, minute: Int): Long {
        val now = LocalDateTime.now()
        var trigger = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0)
        if (!trigger.isAfter(now)) trigger = trigger.plusDays(1)
        return trigger.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}
