package com.guttrack.app.data.db;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\t\u001a\u00020\u0007H'J \u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017\u00c0\u0006\u0003"}, d2 = {"Lcom/guttrack/app/data/db/MealDao;", "", "observeForDate", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/guttrack/app/data/model/MealEntry;", "dateEpoch", "", "observeSince", "fromEpoch", "findByDateAndType", "type", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "entry", "(Lcom/guttrack/app/data/model/MealEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "deleteById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface MealDao {
    
    @androidx.room.Query(value = "SELECT * FROM meal_entries WHERE dateEpoch = :dateEpoch ORDER BY id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.guttrack.app.data.model.MealEntry>> observeForDate(long dateEpoch);
    
    @androidx.room.Query(value = "SELECT * FROM meal_entries WHERE dateEpoch >= :fromEpoch ORDER BY dateEpoch, id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.guttrack.app.data.model.MealEntry>> observeSince(long fromEpoch);
    
    @androidx.room.Query(value = "SELECT * FROM meal_entries WHERE dateEpoch = :dateEpoch AND type = :type LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findByDateAndType(long dateEpoch, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.guttrack.app.data.model.MealEntry> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.guttrack.app.data.model.MealEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM meal_entries WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}