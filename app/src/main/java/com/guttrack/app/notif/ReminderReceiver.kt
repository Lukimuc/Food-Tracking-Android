package com.guttrack.app.notif

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.guttrack.app.data.settings.SettingsStore
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val requestCode = intent.getIntExtra(ReminderScheduler.EXTRA_REQUEST_CODE, -1)
        val title = intent.getStringExtra(ReminderScheduler.EXTRA_TITLE) ?: "GutTrack"
        val text = intent.getStringExtra(ReminderScheduler.EXTRA_TEXT) ?: ""
        val hour = intent.getIntExtra(ReminderScheduler.EXTRA_HOUR, 9)
        val minute = intent.getIntExtra(ReminderScheduler.EXTRA_MINUTE, 0)
        val mealType = intent.getStringExtra(ReminderScheduler.EXTRA_MEAL_TYPE)

        NotificationHelper.show(context, NotificationHelper.CHANNEL_REMINDERS, requestCode, title, text)

        // Re-arm for the same time tomorrow so the reminder repeats daily.
        ReminderScheduler.scheduleSingle(context.applicationContext, requestCode, hour, minute, text, mealType)

        if (mealType != null) {
            val pendingResult = goAsync()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val settings = SettingsStore.getInstance(context.applicationContext).current()
                    if (settings.followUpEnabled) {
                        val work = OneTimeWorkRequestBuilder<FollowUpWorker>()
                            .setInitialDelay(45, TimeUnit.MINUTES)
                            .setInputData(workDataOf(FollowUpWorker.KEY_MEAL_TYPE to mealType))
                            .build()
                        WorkManager.getInstance(context.applicationContext).enqueue(work)
                    }
                } finally {
                    pendingResult.finish()
                }
            }
        }
    }
}
