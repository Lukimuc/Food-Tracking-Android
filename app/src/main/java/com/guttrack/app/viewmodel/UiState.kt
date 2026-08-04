package com.guttrack.app.viewmodel

import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.model.SymptomEntry

enum class Tab { HOME, TIMELINE, EXPORT, SETTINGS }
enum class Modal { LOG, SYMPTOM }
enum class ExportState { IDLE, SENDING, SENT }

data class GtUiState(
    val tab: Tab = Tab.HOME,
    val modal: Modal? = null,

    // Log-meal modal editing buffer
    val logType: MealType = MealType.BREAKFAST,
    val editingMeal: MealEntry? = null,
    val logDate: java.time.LocalDate = java.time.LocalDate.now(),
    val logTime: String = "12:00", // 24h format
    val noteText: String = "",
    val intoleranceTags: List<String> = emptyList(),
    val pendingPhotoUris: List<String> = emptyList(),
    val isAiProcessing: Boolean = false,

    // Symptom modal editing buffer
    val editingSymptom: SymptomEntry? = null,
    val severity: Int = 3,
    val symptomNote: String = "",

    val showNotifBanner: Boolean = false,
    val notifText: String = "",

    val exportState: ExportState = ExportState.IDLE,
)
