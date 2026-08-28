package com.guttrack.app.data.repo

import android.content.Context
import com.guttrack.app.data.db.GutTrackDatabase
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.model.SymptomEntry
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

class GutTrackRepository private constructor(context: Context) {
    private val db = GutTrackDatabase.getInstance(context)
    private val mealDao = db.mealDao()
    private val symptomDao = db.symptomDao()

    fun observeMealsForDate(date: LocalDate): Flow<List<MealEntry>> =
        mealDao.observeForDate(date.toEpochDay())

    fun observeSymptomsForDate(date: LocalDate): Flow<List<SymptomEntry>> =
        symptomDao.observeForDate(date.toEpochDay())

    fun observeMealsSince(date: LocalDate): Flow<List<MealEntry>> =
        mealDao.observeSince(date.toEpochDay())

    fun observeSymptomsSince(date: LocalDate): Flow<List<SymptomEntry>> =
        symptomDao.observeSince(date.toEpochDay())

    /** Breakfast/lunch/dinner are single-per-day: update the existing row for (date, type) or insert one. */
    suspend fun saveMainMeal(date: LocalDate, type: MealType, time: String, note: String, photoUris: String, intoleranceTags: String = "") {
        val existing = mealDao.findByDateAndType(date.toEpochDay(), type.name)
        if (existing != null) {
            mealDao.update(existing.copy(note = note, photoUris = photoUris, intoleranceTags = intoleranceTags))
        } else {
            mealDao.insert(MealEntry(dateEpoch = date.toEpochDay(), type = type.name, time = time, note = note, photoUris = photoUris, intoleranceTags = intoleranceTags))
        }
    }

    suspend fun addSnack(date: LocalDate, time: String, note: String, photoUris: String, intoleranceTags: String = "") {
        mealDao.insert(MealEntry(dateEpoch = date.toEpochDay(), type = MealType.SNACK.name, time = time, note = note, photoUris = photoUris, intoleranceTags = intoleranceTags))
    }

    suspend fun addDrink(date: LocalDate, time: String, note: String, photoUris: String, intoleranceTags: String = "") {
        mealDao.insert(MealEntry(dateEpoch = date.toEpochDay(), type = MealType.DRINK.name, time = time, note = note, photoUris = photoUris, intoleranceTags = intoleranceTags))
    }

    suspend fun updateMeal(entry: MealEntry, note: String, photoUris: String, intoleranceTags: String) {
        mealDao.update(entry.copy(note = note, photoUris = photoUris, intoleranceTags = intoleranceTags))
    }

    suspend fun deleteMeal(entry: MealEntry) = mealDao.delete(entry)

    suspend fun saveSymptom(existing: SymptomEntry?, date: LocalDate, time: String, severity: Int, note: String) {
        if (existing != null) {
            symptomDao.update(existing.copy(severity = severity, note = note))
        } else {
            symptomDao.insert(SymptomEntry(dateEpoch = date.toEpochDay(), time = time, severity = severity, note = note))
        }
    }

    suspend fun deleteSymptom(entry: SymptomEntry) = symptomDao.delete(entry)

    suspend fun isMainMealLogged(date: LocalDate, type: MealType): Boolean =
        mealDao.findByDateAndType(date.toEpochDay(), type.name) != null

    suspend fun getMainMeal(date: LocalDate, type: MealType): MealEntry? =
        mealDao.findByDateAndType(date.toEpochDay(), type.name)

    companion object {
        @Volatile private var INSTANCE: GutTrackRepository? = null
        fun getInstance(context: Context): GutTrackRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GutTrackRepository(context.applicationContext).also { INSTANCE = it }
            }
    }
}
