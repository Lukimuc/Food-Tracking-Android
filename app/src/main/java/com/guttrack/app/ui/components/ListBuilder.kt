package com.guttrack.app.ui.components

import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.model.SymptomEntry

/** Orders a day's entries strictly chronologically by time. */
fun buildDisplayList(meals: List<MealEntry>, symptoms: List<SymptomEntry>): List<Any> {
    val items = (meals + symptoms).sortedBy { item ->
        when (item) {
            is MealEntry -> item.time
            is SymptomEntry -> item.time
            else -> ""
        }
    }
    return items
}

fun mealLabelRes(entry: MealEntry): Int = MealType.fromName(entry.type).labelRes
