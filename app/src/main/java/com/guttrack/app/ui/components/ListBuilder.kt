package com.guttrack.app.ui.components

import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.model.SymptomEntry

/** Orders a day's entries the way the GutTrack design lists them: breakfast, lunch, symptoms, snacks, dinner. */
fun buildDisplayList(meals: List<MealEntry>, symptoms: List<SymptomEntry>): List<Any> {
    val byType = meals.groupBy { it.type }
    val items = mutableListOf<Any>()
    byType[MealType.BREAKFAST.name]?.firstOrNull()?.let { items.add(it) }
    byType[MealType.LUNCH.name]?.firstOrNull()?.let { items.add(it) }
    items.addAll(symptoms)
    items.addAll(byType[MealType.SNACK.name].orEmpty())
    items.addAll(byType[MealType.DRINK.name].orEmpty())
    byType[MealType.DINNER.name]?.firstOrNull()?.let { items.add(it) }
    return items
}

fun mealLabelRes(entry: MealEntry): Int = MealType.fromName(entry.type).labelRes
