package com.guttrack.app.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.guttrack.app.data.model.MealEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meal_entries WHERE dateEpoch = :dateEpoch ORDER BY id")
    fun observeForDate(dateEpoch: Long): Flow<List<MealEntry>>

    @Query("SELECT * FROM meal_entries WHERE dateEpoch >= :fromEpoch ORDER BY dateEpoch, id")
    fun observeSince(fromEpoch: Long): Flow<List<MealEntry>>

    @Query("SELECT * FROM meal_entries WHERE dateEpoch = :dateEpoch AND type = :type LIMIT 1")
    suspend fun findByDateAndType(dateEpoch: Long, type: String): MealEntry?

    @Insert
    suspend fun insert(entry: MealEntry): Long

    @Update
    suspend fun update(entry: MealEntry): Int

    @Delete
    suspend fun delete(entry: MealEntry): Int

    @Query("DELETE FROM meal_entries WHERE id = :id")
    suspend fun deleteById(id: Long): Int
}
