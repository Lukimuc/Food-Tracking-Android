package com.guttrack.app.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.model.SymptomEntry
import com.guttrack.app.data.repo.GutTrackRepository
import com.guttrack.app.data.settings.SettingsStore
import com.guttrack.app.data.settings.UserSettings
import com.guttrack.app.export.ExportDayGroup
import com.guttrack.app.export.ExportItem
import com.guttrack.app.export.PdfExporter
import com.guttrack.app.ai.OnDeviceAiManager
import com.guttrack.app.notif.NotificationHelper
import com.guttrack.app.notif.ReminderScheduler
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GutTrackViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = GutTrackRepository.getInstance(app)
    private val settingsStore = SettingsStore.getInstance(app)
    private val aiManager = OnDeviceAiManager(app)

    private val _uiState = MutableStateFlow(GtUiState())
    val uiState: StateFlow<GtUiState> = _uiState.asStateFlow()

    private val _todayDate = MutableStateFlow(LocalDate.now())
    val todayDate: StateFlow<LocalDate> = _todayDate.asStateFlow()

    private val _selectedTimelineDate = MutableStateFlow(LocalDate.now())
    val selectedTimelineDate: StateFlow<LocalDate> = _selectedTimelineDate.asStateFlow()

    val settings: StateFlow<UserSettings> = settingsStore.settingsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserSettings())

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val todayMeals: StateFlow<List<MealEntry>> = _todayDate
        .flatMapLatest { repo.observeMealsForDate(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val todaySymptoms: StateFlow<List<SymptomEntry>> = _todayDate
        .flatMapLatest { repo.observeSymptomsForDate(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val timelineMeals: StateFlow<List<MealEntry>> = _selectedTimelineDate
        .flatMapLatest { repo.observeMealsForDate(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val timelineSymptoms: StateFlow<List<SymptomEntry>> = _selectedTimelineDate
        .flatMapLatest { repo.observeSymptomsForDate(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val exportMeals: StateFlow<List<MealEntry>> = repo.observeMealsSince(LocalDate.now().minusDays(40))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val exportSymptoms: StateFlow<List<SymptomEntry>> = repo.observeSymptomsSince(LocalDate.now().minusDays(40))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            settingsStore.ensureTrackingStart()
        }
    }

    fun refreshDate() {
        _todayDate.value = LocalDate.now()
    }

    fun setTab(tab: Tab) {
        _uiState.update { it.copy(tab = tab, modal = null) }
        if (tab == Tab.TIMELINE) _selectedTimelineDate.value = LocalDate.now()
    }

    fun selectTimelineDate(date: LocalDate) {
        _selectedTimelineDate.value = date
    }

    fun dismissNotifBanner() {
        _uiState.update { it.copy(showNotifBanner = false) }
    }

    fun sendTestNotification(context: Context) {
        _uiState.update { it.copy(showNotifBanner = true, notifText = "This is a test notification from GutTrack") }
        NotificationHelper.show(context, NotificationHelper.CHANNEL_CHECKINS, 9999, "FoodTracker", "This is a test notification")
    }

    // ---------- Log meal modal ----------

    fun openLogMeal(type: MealType) {
        viewModelScope.launch {
            val now = java.time.LocalTime.now()
            val timeStr = "${now.hour.toString().padStart(2, '0')}:${now.minute.toString().padStart(2, '0')}"
            if (type == MealType.SNACK || type == MealType.DRINK) {
                _uiState.update { it.copy(modal = Modal.LOG, logType = type, editingMeal = null, logDate = _todayDate.value, logTime = timeStr, noteText = "", intoleranceTags = emptyList(), pendingPhotoUris = emptyList()) }
            } else {
                val existing = repo.getMainMeal(_todayDate.value, type)
                val tags = existing?.intoleranceTags?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                val uris = existing?.photoUris?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                _uiState.update {
                    it.copy(modal = Modal.LOG, logType = type, editingMeal = existing, logDate = _todayDate.value, logTime = existing?.time ?: timeStr, noteText = existing?.note ?: "", intoleranceTags = tags, pendingPhotoUris = uris)
                }
            }
        }
    }

    fun editMeal(entry: MealEntry) {
        val tags = entry.intoleranceTags.split(",").filter { it.isNotBlank() }
        val uris = entry.photoUris.split(",").filter { it.isNotBlank() }
        _uiState.update {
            it.copy(modal = Modal.LOG, logType = MealType.fromName(entry.type), editingMeal = entry, logDate = LocalDate.ofEpochDay(entry.dateEpoch), logTime = entry.time, noteText = entry.note, intoleranceTags = tags, pendingPhotoUris = uris)
        }
    }

    fun setLogType(type: MealType) {
        viewModelScope.launch {
            val now = java.time.LocalTime.now()
            val timeStr = "${now.hour.toString().padStart(2, '0')}:${now.minute.toString().padStart(2, '0')}"
            if (type == MealType.SNACK || type == MealType.DRINK) {
                _uiState.update { it.copy(logType = type, editingMeal = null, noteText = "", intoleranceTags = emptyList(), pendingPhotoUris = emptyList()) }
            } else {
                val existing = repo.getMainMeal(_todayDate.value, type)
                val tags = existing?.intoleranceTags?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                val uris = existing?.photoUris?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                _uiState.update { it.copy(logType = type, editingMeal = existing, logDate = _todayDate.value, logTime = existing?.time ?: timeStr, noteText = existing?.note ?: "", intoleranceTags = tags, pendingPhotoUris = uris) }
            }
        }
    }

    fun onNoteChange(text: String) {
        _uiState.update { it.copy(noteText = text) }
    }

    fun addIntoleranceTag(tag: String) {
        _uiState.update { it.copy(intoleranceTags = (it.intoleranceTags + tag).distinct()) }
    }

    fun removeIntoleranceTag(tag: String) {
        _uiState.update { it.copy(intoleranceTags = it.intoleranceTags - tag) }
    }

    fun removePhoto(uri: String) {
        _uiState.update { it.copy(pendingPhotoUris = it.pendingPhotoUris - uri) }
    }

    fun onPhotoCaptured(uri: String) {
        _uiState.update { it.copy(pendingPhotoUris = it.pendingPhotoUris + uri, isAiProcessing = true) }
        tryExtractExif(uri)
        val lang = settings.value.language
        viewModelScope.launch {
            val description = aiManager.describeMeal(_uiState.value.pendingPhotoUris, lang)
            if (description != null) {
                _uiState.update { it.copy(noteText = description) }
                // Trigger intolerance check based on FRESH description
                val tags = aiManager.checkIntolerances(description, lang)
                _uiState.update { it.copy(intoleranceTags = (it.intoleranceTags + tags).distinct(), isAiProcessing = false) }
            } else {
                _uiState.update { it.copy(isAiProcessing = false) }
            }
        }
    }

    fun runManualIntoleranceCheck() {
        val text = _uiState.value.noteText
        if (text.isBlank()) return
        val lang = settings.value.language
        _uiState.update { it.copy(isAiProcessing = true) }
        viewModelScope.launch {
            val tags = aiManager.checkIntolerances(text, lang)
            _uiState.update { it.copy(intoleranceTags = (it.intoleranceTags + tags).distinct(), isAiProcessing = false) }
        }
    }

    private fun tryExtractExif(uriString: String) {
        try {
            val context = getApplication<Application>().applicationContext
            val uri = android.net.Uri.parse(uriString)
            context.contentResolver.openInputStream(uri)?.use { input ->
                val exif = androidx.exifinterface.media.ExifInterface(input)
                val dateStr = exif.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME_ORIGINAL) ?: exif.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME)
                if (dateStr != null) {
                    // Format: "YYYY:MM:DD HH:MM:SS"
                    val parts = dateStr.split(" ")
                    val dateParts = parts[0].split(":")
                    val timeParts = parts[1].split(":")
                    val date = LocalDate.of(dateParts[0].toInt(), dateParts[1].toInt(), dateParts[2].toInt())
                    val time = "${timeParts[0]}:${timeParts[1]}"
                    _uiState.update { it.copy(logDate = date, logTime = time) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onDateChange(date: LocalDate) {
        _uiState.update { it.copy(logDate = date) }
    }

    fun onTimeChange(time: String) {
        _uiState.update { it.copy(logTime = time) }
        // Also trigger intolerance check when text changes manually if needed
        val lang = settings.value.language
        viewModelScope.launch {
            val tags = aiManager.checkIntolerances(_uiState.value.noteText, lang)
            _uiState.update { it.copy(intoleranceTags = (it.intoleranceTags + tags).distinct()) }
        }
    }

    fun saveLog() {
        val state = _uiState.value
        viewModelScope.launch {
            val date = state.logDate
            val time = state.logTime
            val context = getApplication<Application>().applicationContext
            
            val finalUris = state.pendingPhotoUris.map { com.guttrack.app.util.PhotoFiles.persist(context, it) }
            val urisStr = finalUris.joinToString(",")
            val tagsStr = state.intoleranceTags.joinToString(",")

            if (state.logType == MealType.SNACK || state.logType == MealType.DRINK) {
                val editing = state.editingMeal
                if (editing != null) {
                    repo.updateMeal(editing.copy(intoleranceTags = tagsStr, dateEpoch = date.toEpochDay(), time = time), state.noteText, urisStr, tagsStr)
                } else {
                    if (state.logType == MealType.SNACK) {
                        repo.addSnack(date, time, state.noteText, urisStr, tagsStr)
                    } else {
                        repo.addDrink(date, time, state.noteText, urisStr, tagsStr)
                    }
                }
            } else {
                repo.saveMainMeal(date, state.logType, time, state.noteText, urisStr, tagsStr)
            }
            closeModal()
        }
    }

    fun deleteMeal(entry: MealEntry) {
        viewModelScope.launch { repo.deleteMeal(entry) }
    }

    // ---------- Symptom modal ----------

    fun openSymptomNew() {
        _uiState.update { it.copy(modal = Modal.SYMPTOM, editingSymptom = null, severity = 3, symptomNote = "") }
    }

    fun editSymptom(entry: SymptomEntry) {
        _uiState.update { it.copy(modal = Modal.SYMPTOM, editingSymptom = entry, severity = entry.severity, symptomNote = entry.note) }
    }

    fun onSeverityChange(n: Int) {
        _uiState.update { it.copy(severity = n) }
    }

    fun onSymptomNoteChange(text: String) {
        _uiState.update { it.copy(symptomNote = text) }
    }

    fun saveSymptom() {
        val state = _uiState.value
        viewModelScope.launch {
            val now = java.time.LocalTime.now()
            val timeStr = "${now.hour.toString().padStart(2, '0')}:${now.minute.toString().padStart(2, '0')}"
            repo.saveSymptom(state.editingSymptom, _todayDate.value, timeStr, state.severity, state.symptomNote)
            closeModal()
        }
    }

    fun deleteSymptomEntry(entry: SymptomEntry) {
        viewModelScope.launch { repo.deleteSymptom(entry) }
    }

    fun closeModal() {
        _uiState.update { it.copy(modal = null) }
    }

    // ---------- Settings ----------

    fun updateReminderBreakfast(context: Context, value: String) = updateAndReschedule(context) { settingsStore.setReminderBreakfast(value) }
    fun updateReminderLunch(context: Context, value: String) = updateAndReschedule(context) { settingsStore.setReminderLunch(value) }
    fun updateReminderDinnerStart(context: Context, value: String) = updateAndReschedule(context) { settingsStore.setReminderDinnerStart(value) }
    fun updateReminderDinnerEnd(context: Context, value: String) = updateAndReschedule(context) { settingsStore.setReminderDinnerEnd(value) }
    fun toggleFollowUp(context: Context) = updateAndReschedule(context) { settingsStore.setFollowUpEnabled(!settings.value.followUpEnabled) }
    fun toggleSnackAsk(context: Context) = updateAndReschedule(context) { settingsStore.setSnackAskEnabled(!settings.value.snackAskEnabled) }

    private fun updateAndReschedule(context: Context, block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
            ReminderScheduler.rescheduleAll(context.applicationContext, settingsStore.current())
        }
    }

    fun toggleIncludePhotos() {
        viewModelScope.launch { settingsStore.setIncludePhotos(!settings.value.includePhotosInExport) }
    }

    fun setLanguage(value: String) {
        viewModelScope.launch { settingsStore.setLanguage(value) }
    }

    // ---------- Export ----------

    fun doExport(context: Context) {
        _uiState.update { it.copy(exportState = ExportState.SENDING) }
        viewModelScope.launch {
            val groups = buildExportGroups()
            val includePhotos = settings.value.includePhotosInExport
            val file = withContext(Dispatchers.IO) { PdfExporter.generate(context, groups, includePhotos) }
            val uri = PdfExporter.uriFor(context, file)

            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, "Food & Gut Log")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(Intent.createChooser(sendIntent, "Export report").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

            _uiState.update { it.copy(exportState = ExportState.SENT) }
        }
    }

    fun resetExport() {
        _uiState.update { it.copy(exportState = ExportState.IDLE) }
    }

    private fun buildExportGroups(): List<ExportDayGroup> {
        val meals = exportMeals.value
        val symptoms = exportSymptoms.value
        val context = getApplication<Application>().applicationContext
        val dayLabel = DateTimeFormatter.ofPattern("MMM d")
        val dates = (meals.map { it.dateEpoch } + symptoms.map { it.dateEpoch }).toSortedSet().sortedDescending()
        return dates.map { epoch ->
            val date = LocalDate.ofEpochDay(epoch)
            val label = when (date) {
                LocalDate.now() -> context.getString(com.guttrack.app.R.string.day_today)
                LocalDate.now().minusDays(1) -> context.getString(com.guttrack.app.R.string.day_yesterday)
                else -> date.format(dayLabel)
            }
            
            val dayMeals = meals.filter { it.dateEpoch == epoch }.map {
                val tags = it.intoleranceTags.split(",").filter { t -> t.isNotBlank() }
                val uris = it.photoUris.split(",").filter { u -> u.isNotBlank() }
                ExportItem(MealType.fromName(it.type).getLabel(context), it.time, it.note, "", tags, uris.firstOrNull(), uris.getOrNull(1), null)
            }
            val daySymptoms = symptoms.filter { it.dateEpoch == epoch }.map {
                ExportItem(context.getString(com.guttrack.app.R.string.log_symptom), it.time, it.note, "", emptyList(), null, null, it.severity)
            }
            
            // Strictly chronological sort by time string (24h format "HH:mm")
            val sortedItems = (dayMeals + daySymptoms).sortedBy { it.time }
            
            ExportDayGroup(label, sortedItems)
        }
    }
}
