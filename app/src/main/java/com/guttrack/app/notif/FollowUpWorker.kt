package com.guttrack.app.notif

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.guttrack.app.R
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.repo.GutTrackRepository
import java.time.LocalDate

class FollowUpWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val typeName = inputData.getString(KEY_MEAL_TYPE) ?: return Result.success()
        val type = runCatching { MealType.valueOf(typeName) }.getOrNull() ?: return Result.success()
        val repo = GutTrackRepository.getInstance(applicationContext)
        val logged = repo.isMainMealLogged(LocalDate.now(), type)
        if (!logged) {
            val mealLabel = applicationContext.getString(type.labelRes)
            NotificationHelper.show(
                applicationContext,
                NotificationHelper.CHANNEL_CHECKINS,
                2000 + type.ordinal,
                "GutTrack",
                applicationContext.getString(R.string.notif_followup_msg, mealLabel.lowercase()),
            )
        }
        return Result.success()
    }

    companion object {
        const val KEY_MEAL_TYPE = "mealType"
    }
}
