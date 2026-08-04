package com.guttrack.app.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u00100\u001a\u000201J\u000e\u00102\u001a\u0002012\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\u0002012\u0006\u00106\u001a\u00020\u0012J\u0006\u00107\u001a\u000201J\u000e\u00108\u001a\u0002012\u0006\u00109\u001a\u00020:J\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u0002012\u0006\u0010>\u001a\u00020?J\u000e\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020\u001eJ\u000e\u0010B\u001a\u0002012\u0006\u0010>\u001a\u00020?J\u000e\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020<J\u000e\u0010E\u001a\u0002012\u0006\u0010D\u001a\u00020<J\u000e\u0010F\u001a\u0002012\u0006\u0010G\u001a\u00020<J\u0006\u0010H\u001a\u000201J\u000e\u0010I\u001a\u0002012\u0006\u0010A\u001a\u00020\u001eJ\u0006\u0010J\u001a\u000201J\u000e\u0010K\u001a\u0002012\u0006\u0010A\u001a\u00020#J\u000e\u0010L\u001a\u0002012\u0006\u0010M\u001a\u00020NJ\u000e\u0010O\u001a\u0002012\u0006\u0010D\u001a\u00020<J\u0006\u0010P\u001a\u000201J\u000e\u0010Q\u001a\u0002012\u0006\u0010A\u001a\u00020#J\u0006\u0010R\u001a\u000201J\u0016\u0010S\u001a\u0002012\u0006\u00109\u001a\u00020:2\u0006\u0010T\u001a\u00020<J\u0016\u0010U\u001a\u0002012\u0006\u00109\u001a\u00020:2\u0006\u0010T\u001a\u00020<J\u0016\u0010V\u001a\u0002012\u0006\u00109\u001a\u00020:2\u0006\u0010T\u001a\u00020<J\u0016\u0010W\u001a\u0002012\u0006\u00109\u001a\u00020:2\u0006\u0010T\u001a\u00020<J\u000e\u0010X\u001a\u0002012\u0006\u00109\u001a\u00020:J\u000e\u0010Y\u001a\u0002012\u0006\u00109\u001a\u00020:J\u000e\u0010Z\u001a\u0002012\u0006\u0010T\u001a\u00020<J3\u0010[\u001a\u0002012\u0006\u00109\u001a\u00020:2\u001c\u0010\\\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002010^\u0012\u0006\u0012\u0004\u0018\u00010_0]H\u0002\u00a2\u0006\u0002\u0010`J\u0006\u0010a\u001a\u000201J\u000e\u0010b\u001a\u0002012\u0006\u00109\u001a\u00020:J\u0006\u0010c\u001a\u000201J\u000e\u0010d\u001a\b\u0012\u0004\u0012\u00020e0\u001dH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u00120\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u00120\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R#\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\u0010R#\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001d0\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b$\u0010 \u001a\u0004\b%\u0010\u0010R#\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b'\u0010 \u001a\u0004\b(\u0010\u0010R#\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001d0\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b*\u0010 \u001a\u0004\b+\u0010\u0010R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001d0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0010\u00a8\u0006f"}, d2 = {"Lcom/guttrack/app/viewmodel/GutTrackViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "repo", "Lcom/guttrack/app/data/repo/GutTrackRepository;", "settingsStore", "Lcom/guttrack/app/data/settings/SettingsStore;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/guttrack/app/viewmodel/GtUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "_todayDate", "Ljava/time/LocalDate;", "kotlin.jvm.PlatformType", "todayDate", "getTodayDate", "_selectedTimelineDate", "selectedTimelineDate", "getSelectedTimelineDate", "settings", "Lcom/guttrack/app/data/settings/UserSettings;", "getSettings", "todayMeals", "", "Lcom/guttrack/app/data/model/MealEntry;", "getTodayMeals$annotations", "()V", "getTodayMeals", "todaySymptoms", "Lcom/guttrack/app/data/model/SymptomEntry;", "getTodaySymptoms$annotations", "getTodaySymptoms", "timelineMeals", "getTimelineMeals$annotations", "getTimelineMeals", "timelineSymptoms", "getTimelineSymptoms$annotations", "getTimelineSymptoms", "exportMeals", "getExportMeals", "exportSymptoms", "getExportSymptoms", "refreshDate", "", "setTab", "tab", "Lcom/guttrack/app/viewmodel/Tab;", "selectTimelineDate", "date", "dismissNotifBanner", "sendTestNotification", "context", "Landroid/content/Context;", "nowLabel", "", "openLogMeal", "type", "Lcom/guttrack/app/data/model/MealType;", "editMeal", "entry", "setLogType", "onNoteChange", "text", "onDrinkChange", "onPhotoCaptured", "uri", "saveLog", "deleteMeal", "openSymptomNew", "editSymptom", "onSeverityChange", "n", "", "onSymptomNoteChange", "saveSymptom", "deleteSymptomEntry", "closeModal", "updateReminderBreakfast", "value", "updateReminderLunch", "updateReminderDinnerStart", "updateReminderDinnerEnd", "toggleFollowUp", "toggleSnackAsk", "setNutritionEmail", "updateAndReschedule", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "toggleIncludePhotos", "doExport", "resetExport", "buildExportGroups", "Lcom/guttrack/app/export/ExportDayGroup;", "app_debug"})
public final class GutTrackViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.data.repo.GutTrackRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.data.settings.SettingsStore settingsStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.guttrack.app.viewmodel.GtUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.guttrack.app.viewmodel.GtUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.time.LocalDate> _todayDate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.time.LocalDate> todayDate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.time.LocalDate> _selectedTimelineDate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.time.LocalDate> selectedTimelineDate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.guttrack.app.data.settings.UserSettings> settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.MealEntry>> todayMeals = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> todaySymptoms = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.MealEntry>> timelineMeals = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> timelineSymptoms = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.MealEntry>> exportMeals = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> exportSymptoms = null;
    
    public GutTrackViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.guttrack.app.viewmodel.GtUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.time.LocalDate> getTodayDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.time.LocalDate> getSelectedTimelineDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.guttrack.app.data.settings.UserSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.MealEntry>> getTodayMeals() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getTodayMeals$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> getTodaySymptoms() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getTodaySymptoms$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.MealEntry>> getTimelineMeals() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getTimelineMeals$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> getTimelineSymptoms() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getTimelineSymptoms$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.MealEntry>> getExportMeals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> getExportSymptoms() {
        return null;
    }
    
    public final void refreshDate() {
    }
    
    public final void setTab(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.viewmodel.Tab tab) {
    }
    
    public final void selectTimelineDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
    }
    
    public final void dismissNotifBanner() {
    }
    
    public final void sendTestNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String nowLabel() {
        return null;
    }
    
    public final void openLogMeal(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType type) {
    }
    
    public final void editMeal(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry) {
    }
    
    public final void setLogType(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType type) {
    }
    
    public final void onNoteChange(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void onDrinkChange(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void onPhotoCaptured(@org.jetbrains.annotations.NotNull()
    java.lang.String uri) {
    }
    
    public final void saveLog() {
    }
    
    public final void deleteMeal(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry) {
    }
    
    public final void openSymptomNew() {
    }
    
    public final void editSymptom(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.SymptomEntry entry) {
    }
    
    public final void onSeverityChange(int n) {
    }
    
    public final void onSymptomNoteChange(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void saveSymptom() {
    }
    
    public final void deleteSymptomEntry(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.SymptomEntry entry) {
    }
    
    public final void closeModal() {
    }
    
    public final void updateReminderBreakfast(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateReminderLunch(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateReminderDinnerStart(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateReminderDinnerEnd(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void toggleFollowUp(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void toggleSnackAsk(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void setNutritionEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    private final void updateAndReschedule(android.content.Context context, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
    }
    
    public final void toggleIncludePhotos() {
    }
    
    public final void doExport(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void resetExport() {
    }
    
    private final java.util.List<com.guttrack.app.export.ExportDayGroup> buildExportGroups() {
        return null;
    }
}