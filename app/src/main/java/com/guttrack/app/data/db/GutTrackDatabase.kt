package com.guttrack.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.SymptomEntry

@Database(entities = [MealEntry::class, SymptomEntry::class], version = 1, exportSchema = false)
abstract class GutTrackDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun symptomDao(): SymptomDao

    companion object {
        @Volatile
        private var INSTANCE: GutTrackDatabase? = null

        fun getInstance(context: Context): GutTrackDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GutTrackDatabase::class.java,
                    "guttrack.db",
                ).build().also { INSTANCE = it }
            }
        }
    }
}
