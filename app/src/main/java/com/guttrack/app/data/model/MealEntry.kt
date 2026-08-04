package com.guttrack.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_entries")
data class MealEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateEpoch: Long,
    val type: String, // MealType.name
    val time: String,
    val note: String,
    val photoUris: String = "", // Comma-separated URIs
    val intoleranceTags: String = "", // Comma-separated tags
)
