package com.guttrack.app.notif

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.guttrack.app.data.settings.SettingsStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val settings = SettingsStore.getInstance(context.applicationContext).current()
                ReminderScheduler.rescheduleAll(context.applicationContext, settings)
            } finally {
                pendingResult.finish()
            }
        }
    }
}
