package com.guttrack.app.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b+\b\u0086\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0018\u0010\u0019J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u00104\u001a\u00020\u000bH\u00c6\u0003J\t\u00105\u001a\u00020\u000bH\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\t\u00108\u001a\u00020\u0011H\u00c6\u0003J\t\u00109\u001a\u00020\u000bH\u00c6\u0003J\t\u0010:\u001a\u00020\u0014H\u00c6\u0003J\t\u0010;\u001a\u00020\u000bH\u00c6\u0003J\t\u0010<\u001a\u00020\u0017H\u00c6\u0003J\u0093\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u00c6\u0001J\u0013\u0010>\u001a\u00020\u00142\b\u0010?\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010@\u001a\u00020\u0011H\u00d6\u0001J\t\u0010A\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0012\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u0015\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/\u00a8\u0006B"}, d2 = {"Lcom/guttrack/app/viewmodel/GtUiState;", "", "tab", "Lcom/guttrack/app/viewmodel/Tab;", "modal", "Lcom/guttrack/app/viewmodel/Modal;", "logType", "Lcom/guttrack/app/data/model/MealType;", "editingMeal", "Lcom/guttrack/app/data/model/MealEntry;", "noteText", "", "drinkText", "pendingPhotoUri", "editingSymptom", "Lcom/guttrack/app/data/model/SymptomEntry;", "severity", "", "symptomNote", "showNotifBanner", "", "notifText", "exportState", "Lcom/guttrack/app/viewmodel/ExportState;", "<init>", "(Lcom/guttrack/app/viewmodel/Tab;Lcom/guttrack/app/viewmodel/Modal;Lcom/guttrack/app/data/model/MealType;Lcom/guttrack/app/data/model/MealEntry;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/guttrack/app/data/model/SymptomEntry;ILjava/lang/String;ZLjava/lang/String;Lcom/guttrack/app/viewmodel/ExportState;)V", "getTab", "()Lcom/guttrack/app/viewmodel/Tab;", "getModal", "()Lcom/guttrack/app/viewmodel/Modal;", "getLogType", "()Lcom/guttrack/app/data/model/MealType;", "getEditingMeal", "()Lcom/guttrack/app/data/model/MealEntry;", "getNoteText", "()Ljava/lang/String;", "getDrinkText", "getPendingPhotoUri", "getEditingSymptom", "()Lcom/guttrack/app/data/model/SymptomEntry;", "getSeverity", "()I", "getSymptomNote", "getShowNotifBanner", "()Z", "getNotifText", "getExportState", "()Lcom/guttrack/app/viewmodel/ExportState;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class GtUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.viewmodel.Tab tab = null;
    @org.jetbrains.annotations.Nullable()
    private final com.guttrack.app.viewmodel.Modal modal = null;
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.data.model.MealType logType = null;
    @org.jetbrains.annotations.Nullable()
    private final com.guttrack.app.data.model.MealEntry editingMeal = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String noteText = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String drinkText = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pendingPhotoUri = null;
    @org.jetbrains.annotations.Nullable()
    private final com.guttrack.app.data.model.SymptomEntry editingSymptom = null;
    private final int severity = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String symptomNote = null;
    private final boolean showNotifBanner = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notifText = null;
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.viewmodel.ExportState exportState = null;
    
    public GtUiState(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.viewmodel.Tab tab, @org.jetbrains.annotations.Nullable()
    com.guttrack.app.viewmodel.Modal modal, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType logType, @org.jetbrains.annotations.Nullable()
    com.guttrack.app.data.model.MealEntry editingMeal, @org.jetbrains.annotations.NotNull()
    java.lang.String noteText, @org.jetbrains.annotations.NotNull()
    java.lang.String drinkText, @org.jetbrains.annotations.Nullable()
    java.lang.String pendingPhotoUri, @org.jetbrains.annotations.Nullable()
    com.guttrack.app.data.model.SymptomEntry editingSymptom, int severity, @org.jetbrains.annotations.NotNull()
    java.lang.String symptomNote, boolean showNotifBanner, @org.jetbrains.annotations.NotNull()
    java.lang.String notifText, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.viewmodel.ExportState exportState) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.viewmodel.Tab getTab() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.guttrack.app.viewmodel.Modal getModal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.data.model.MealType getLogType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.guttrack.app.data.model.MealEntry getEditingMeal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNoteText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDrinkText() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPendingPhotoUri() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.guttrack.app.data.model.SymptomEntry getEditingSymptom() {
        return null;
    }
    
    public final int getSeverity() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSymptomNote() {
        return null;
    }
    
    public final boolean getShowNotifBanner() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotifText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.viewmodel.ExportState getExportState() {
        return null;
    }
    
    public GtUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.viewmodel.Tab component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.viewmodel.ExportState component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.guttrack.app.viewmodel.Modal component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.data.model.MealType component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.guttrack.app.data.model.MealEntry component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.guttrack.app.data.model.SymptomEntry component8() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.guttrack.app.viewmodel.GtUiState copy(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.viewmodel.Tab tab, @org.jetbrains.annotations.Nullable()
    com.guttrack.app.viewmodel.Modal modal, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType logType, @org.jetbrains.annotations.Nullable()
    com.guttrack.app.data.model.MealEntry editingMeal, @org.jetbrains.annotations.NotNull()
    java.lang.String noteText, @org.jetbrains.annotations.NotNull()
    java.lang.String drinkText, @org.jetbrains.annotations.Nullable()
    java.lang.String pendingPhotoUri, @org.jetbrains.annotations.Nullable()
    com.guttrack.app.data.model.SymptomEntry editingSymptom, int severity, @org.jetbrains.annotations.NotNull()
    java.lang.String symptomNote, boolean showNotifBanner, @org.jetbrains.annotations.NotNull()
    java.lang.String notifText, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.viewmodel.ExportState exportState) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}