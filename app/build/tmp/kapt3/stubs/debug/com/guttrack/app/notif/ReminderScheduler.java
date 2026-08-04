package com.guttrack.app.notif;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018J8\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\nJ\u0016\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010 \u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\"2\u0006\u0010#\u001a\u00020\nJ\u0018\u0010$\u001a\u00020%2\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/guttrack/app/notif/ReminderScheduler;", "", "<init>", "()V", "REQ_BREAKFAST", "", "REQ_LUNCH", "REQ_DINNER", "REQ_SNACK", "EXTRA_REQUEST_CODE", "", "EXTRA_TITLE", "EXTRA_TEXT", "EXTRA_HOUR", "EXTRA_MINUTE", "EXTRA_MEAL_TYPE", "canScheduleExact", "", "context", "Landroid/content/Context;", "requestExactAlarmPermission", "", "rescheduleAll", "settings", "Lcom/guttrack/app/data/settings/UserSettings;", "scheduleSingle", "requestCode", "hour", "minute", "message", "mealTypeName", "cancel", "cancelAll", "parseHourMinute", "Lkotlin/Pair;", "hhmm", "nextTriggerMillis", "", "app_debug"})
public final class ReminderScheduler {
    public static final int REQ_BREAKFAST = 1001;
    public static final int REQ_LUNCH = 1002;
    public static final int REQ_DINNER = 1003;
    public static final int REQ_SNACK = 1004;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_REQUEST_CODE = "requestCode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TITLE = "title";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TEXT = "text";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_HOUR = "hour";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MINUTE = "minute";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MEAL_TYPE = "mealType";
    @org.jetbrains.annotations.NotNull()
    public static final com.guttrack.app.notif.ReminderScheduler INSTANCE = null;
    
    private ReminderScheduler() {
        super();
    }
    
    public final boolean canScheduleExact(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final void requestExactAlarmPermission(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void rescheduleAll(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.settings.UserSettings settings) {
    }
    
    public final void scheduleSingle(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int requestCode, int hour, int minute, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.String mealTypeName) {
    }
    
    public final void cancel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int requestCode) {
    }
    
    public final void cancelAll(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> parseHourMinute(@org.jetbrains.annotations.NotNull()
    java.lang.String hhmm) {
        return null;
    }
    
    private final long nextTriggerMillis(int hour, int minute) {
        return 0L;
    }
}