package com.guttrack.app.data.repo;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 22\u00020\u0001:\u00012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u0011J@\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001bH\u0086@\u00a2\u0006\u0002\u0010\u001fJ8\u0010 \u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001bH\u0086@\u00a2\u0006\u0002\u0010!J0\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001bH\u0086@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010&J8\u0010'\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001c\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010-J\u001e\u0010.\u001a\u00020/2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u00100J \u00101\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u00100R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/guttrack/app/data/repo/GutTrackRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "db", "Lcom/guttrack/app/data/db/GutTrackDatabase;", "mealDao", "Lcom/guttrack/app/data/db/MealDao;", "symptomDao", "Lcom/guttrack/app/data/db/SymptomDao;", "observeMealsForDate", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/guttrack/app/data/model/MealEntry;", "date", "Ljava/time/LocalDate;", "observeSymptomsForDate", "Lcom/guttrack/app/data/model/SymptomEntry;", "observeMealsSince", "observeSymptomsSince", "saveMainMeal", "", "type", "Lcom/guttrack/app/data/model/MealType;", "time", "", "note", "drinkNote", "photoUri", "(Ljava/time/LocalDate;Lcom/guttrack/app/data/model/MealType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addSnack", "(Ljava/time/LocalDate;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateMeal", "entry", "(Lcom/guttrack/app/data/model/MealEntry;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMeal", "(Lcom/guttrack/app/data/model/MealEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSymptom", "existing", "severity", "", "(Lcom/guttrack/app/data/model/SymptomEntry;Ljava/time/LocalDate;Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSymptom", "(Lcom/guttrack/app/data/model/SymptomEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isMainMealLogged", "", "(Ljava/time/LocalDate;Lcom/guttrack/app/data/model/MealType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMainMeal", "Companion", "app_debug"})
public final class GutTrackRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.data.db.GutTrackDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.data.db.MealDao mealDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.guttrack.app.data.db.SymptomDao symptomDao = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.guttrack.app.data.repo.GutTrackRepository INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.guttrack.app.data.repo.GutTrackRepository.Companion Companion = null;
    
    private GutTrackRepository(android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.guttrack.app.data.model.MealEntry>> observeMealsForDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> observeSymptomsForDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.guttrack.app.data.model.MealEntry>> observeMealsSince(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.guttrack.app.data.model.SymptomEntry>> observeSymptomsSince(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
        return null;
    }
    
    /**
     * Breakfast/lunch/dinner are single-per-day: update the existing row for (date, type) or insert one.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveMainMeal(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType type, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String note, @org.jetbrains.annotations.NotNull()
    java.lang.String drinkNote, @org.jetbrains.annotations.Nullable()
    java.lang.String photoUri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSnack(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String note, @org.jetbrains.annotations.NotNull()
    java.lang.String drinkNote, @org.jetbrains.annotations.Nullable()
    java.lang.String photoUri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMeal(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry, @org.jetbrains.annotations.NotNull()
    java.lang.String note, @org.jetbrains.annotations.NotNull()
    java.lang.String drinkNote, @org.jetbrains.annotations.Nullable()
    java.lang.String photoUri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteMeal(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveSymptom(@org.jetbrains.annotations.Nullable()
    com.guttrack.app.data.model.SymptomEntry existing, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    java.lang.String time, int severity, @org.jetbrains.annotations.NotNull()
    java.lang.String note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteSymptom(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.SymptomEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isMainMealLogged(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMainMeal(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.guttrack.app.data.model.MealEntry> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/guttrack/app/data/repo/GutTrackRepository$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/guttrack/app/data/repo/GutTrackRepository;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.guttrack.app.data.repo.GutTrackRepository getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}