package com.guttrack.app.data.settings

data class UserSettings(
    val reminderBreakfast: String = "10:00",
    val reminderLunch: String = "13:00",
    val reminderDinnerStart: String = "18:00",
    val reminderDinnerEnd: String = "19:00",
    val followUpEnabled: Boolean = true,
    val snackAskEnabled: Boolean = true,
    val includePhotosInExport: Boolean = true,
    val totalDays: Int = 14,
    val trackingStartEpoch: Long = 0L,
    val nutritionExpertEmail: String = "",
    val language: String = "en",
)
