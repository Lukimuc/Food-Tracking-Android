package com.guttrack.app.data.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "guttrack_settings")

class SettingsStore(private val context: Context) {
    private object Keys {
        val BREAKFAST = stringPreferencesKey("reminder_breakfast")
        val LUNCH = stringPreferencesKey("reminder_lunch")
        val DINNER_START = stringPreferencesKey("reminder_dinner_start")
        val DINNER_END = stringPreferencesKey("reminder_dinner_end")
        val FOLLOW_UP = booleanPreferencesKey("follow_up_enabled")
        val SNACK_ASK = booleanPreferencesKey("snack_ask_enabled")
        val INCLUDE_PHOTOS = booleanPreferencesKey("include_photos")
        val TOTAL_DAYS = intPreferencesKey("total_days")
        val TRACKING_START = longPreferencesKey("tracking_start_epoch")
        val NUTRITION_EMAIL = stringPreferencesKey("nutrition_expert_email")
        val LANGUAGE = stringPreferencesKey("language")
    }

    val settingsFlow: Flow<UserSettings> = context.dataStore.data.map { p ->
        UserSettings(
            reminderBreakfast = p[Keys.BREAKFAST] ?: "10:00",
            reminderLunch = p[Keys.LUNCH] ?: "13:00",
            reminderDinnerStart = p[Keys.DINNER_START] ?: "18:00",
            reminderDinnerEnd = p[Keys.DINNER_END] ?: "19:00",
            followUpEnabled = p[Keys.FOLLOW_UP] ?: true,
            snackAskEnabled = p[Keys.SNACK_ASK] ?: true,
            includePhotosInExport = p[Keys.INCLUDE_PHOTOS] ?: true,
            totalDays = p[Keys.TOTAL_DAYS] ?: 14,
            trackingStartEpoch = p[Keys.TRACKING_START] ?: 0L,
            nutritionExpertEmail = p[Keys.NUTRITION_EMAIL] ?: "",
            language = p[Keys.LANGUAGE] ?: "en",
        )
    }

    suspend fun ensureTrackingStart() {
        context.dataStore.edit { p ->
            if (p[Keys.TRACKING_START] == null) {
                p[Keys.TRACKING_START] = LocalDate.now().toEpochDay()
            }
        }
    }

    suspend fun current(): UserSettings = settingsFlow.first()

    suspend fun setReminderBreakfast(value: String) = context.dataStore.edit { it[Keys.BREAKFAST] = value }
    suspend fun setReminderLunch(value: String) = context.dataStore.edit { it[Keys.LUNCH] = value }
    suspend fun setReminderDinnerStart(value: String) = context.dataStore.edit { it[Keys.DINNER_START] = value }
    suspend fun setReminderDinnerEnd(value: String) = context.dataStore.edit { it[Keys.DINNER_END] = value }
    suspend fun setFollowUpEnabled(value: Boolean) = context.dataStore.edit { it[Keys.FOLLOW_UP] = value }
    suspend fun setSnackAskEnabled(value: Boolean) = context.dataStore.edit { it[Keys.SNACK_ASK] = value }
    suspend fun setIncludePhotos(value: Boolean) = context.dataStore.edit { it[Keys.INCLUDE_PHOTOS] = value }
    suspend fun setTotalDays(value: Int) = context.dataStore.edit { it[Keys.TOTAL_DAYS] = value }
    suspend fun setNutritionExpertEmail(value: String) = context.dataStore.edit { it[Keys.NUTRITION_EMAIL] = value }
    suspend fun setLanguage(value: String) = context.dataStore.edit { it[Keys.LANGUAGE] = value }

    companion object {
        @Volatile private var INSTANCE: SettingsStore? = null
        fun getInstance(context: Context): SettingsStore =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SettingsStore(context.applicationContext).also { INSTANCE = it }
            }
    }
}
