package com.guttrack.app.data.settings;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 !2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\""}, d2 = {"Lcom/guttrack/app/data/settings/SettingsStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "settingsFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/guttrack/app/data/settings/UserSettings;", "getSettingsFlow", "()Lkotlinx/coroutines/flow/Flow;", "ensureTrackingStart", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "current", "setReminderBreakfast", "Landroidx/datastore/preferences/core/Preferences;", "value", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setReminderLunch", "setReminderDinnerStart", "setReminderDinnerEnd", "setFollowUpEnabled", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSnackAskEnabled", "setIncludePhotos", "setTotalDays", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setNutritionExpertEmail", "Keys", "Companion", "app_debug"})
public final class SettingsStore {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.guttrack.app.data.settings.UserSettings> settingsFlow = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.guttrack.app.data.settings.SettingsStore INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.guttrack.app.data.settings.SettingsStore.Companion Companion = null;
    
    public SettingsStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.guttrack.app.data.settings.UserSettings> getSettingsFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureTrackingStart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object current(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.guttrack.app.data.settings.UserSettings> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setReminderBreakfast(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setReminderLunch(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setReminderDinnerStart(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setReminderDinnerEnd(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setFollowUpEnabled(boolean value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setSnackAskEnabled(boolean value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setIncludePhotos(boolean value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setTotalDays(int value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setNutritionExpertEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/guttrack/app/data/settings/SettingsStore$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/guttrack/app/data/settings/SettingsStore;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.guttrack.app.data.settings.SettingsStore getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c2\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\bR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\b\u00a8\u0006\u001e"}, d2 = {"Lcom/guttrack/app/data/settings/SettingsStore$Keys;", "", "<init>", "()V", "BREAKFAST", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getBREAKFAST", "()Landroidx/datastore/preferences/core/Preferences$Key;", "LUNCH", "getLUNCH", "DINNER_START", "getDINNER_START", "DINNER_END", "getDINNER_END", "FOLLOW_UP", "", "getFOLLOW_UP", "SNACK_ASK", "getSNACK_ASK", "INCLUDE_PHOTOS", "getINCLUDE_PHOTOS", "TOTAL_DAYS", "", "getTOTAL_DAYS", "TRACKING_START", "", "getTRACKING_START", "NUTRITION_EMAIL", "getNUTRITION_EMAIL", "app_debug"})
    static final class Keys {
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> BREAKFAST = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> LUNCH = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> DINNER_START = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> DINNER_END = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> FOLLOW_UP = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> SNACK_ASK = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> INCLUDE_PHOTOS = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> TOTAL_DAYS = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Long> TRACKING_START = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> NUTRITION_EMAIL = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.guttrack.app.data.settings.SettingsStore.Keys INSTANCE = null;
        
        private Keys() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getBREAKFAST() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getLUNCH() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getDINNER_START() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getDINNER_END() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getFOLLOW_UP() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getSNACK_ASK() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getINCLUDE_PHOTOS() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getTOTAL_DAYS() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Long> getTRACKING_START() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getNUTRITION_EMAIL() {
            return null;
        }
    }
}