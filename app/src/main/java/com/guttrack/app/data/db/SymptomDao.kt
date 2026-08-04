package com.guttrack.app.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.guttrack.app.data.model.SymptomEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface SymptomDao {
    @Query("SELECT * FROM symptom_entries WHERE dateEpoch = :dateEpoch ORDER BY id")
    fun observeForDate(dateEpoch: Long): Flow<List<SymptomEntry>>

    @Query("SELECT * FROM symptom_entries WHERE dateEpoch >= :fromEpoch ORDER BY dateEpoch, id")
    fun observeSince(fromEpoch: Long): Flow<List<SymptomEntry>>

    @Insert
    suspend fun insert(entry: SymptomEntry): Long

    @Update
    suspend fun update(entry: SymptomEntry): Int

    @Delete
    suspend fun delete(entry: SymptomEntry): Int

    @Query("DELETE FROM symptom_entries WHERE id = :id")
    suspend fun deleteById(id: Long): Int
}
