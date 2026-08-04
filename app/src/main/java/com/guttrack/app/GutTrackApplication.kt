package com.guttrack.app

import android.app.Application
import com.guttrack.app.data.settings.SettingsStore
import com.guttrack.app.notif.NotificationHelper
import com.guttrack.app.notif.ReminderScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GutTrackApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createChannels(this)
        CoroutineScope(Dispatchers.IO).launch {
            val store = SettingsStore.getInstance(this@GutTrackApplication)
            store.ensureTrackingStart()
            ReminderScheduler.rescheduleAll(this@GutTrackApplication, store.current())
        }
    }
}
