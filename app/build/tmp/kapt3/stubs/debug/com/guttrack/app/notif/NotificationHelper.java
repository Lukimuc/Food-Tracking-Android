package com.guttrack.app.notif;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ.\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/guttrack/app/notif/NotificationHelper;", "", "<init>", "()V", "CHANNEL_REMINDERS", "", "CHANNEL_CHECKINS", "createChannels", "", "context", "Landroid/content/Context;", "show", "channel", "notificationId", "", "title", "text", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_REMINDERS = "meal_reminders";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_CHECKINS = "checkins";
    @org.jetbrains.annotations.NotNull()
    public static final com.guttrack.app.notif.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void createChannels(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void show(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String channel, int notificationId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}