package com.guttrack.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "symptom_entries")
data class SymptomEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateEpoch: Long,
    val time: String,
    val severity: Int,
    val note: String,
)
