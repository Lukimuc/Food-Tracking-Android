package com.guttrack.app.data.model

import com.guttrack.app.R

import android.content.Context

enum class MealType(val labelRes: Int) {
    BREAKFAST(com.guttrack.app.R.string.meal_breakfast),
    LUNCH(com.guttrack.app.R.string.meal_lunch),
    DINNER(com.guttrack.app.R.string.meal_dinner),
    SNACK(com.guttrack.app.R.string.meal_snack);

    fun getLabel(context: Context): String = context.getString(labelRes)

    companion object {
        fun fromName(name: String): MealType = valueOf(name)
    }
}
