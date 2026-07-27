package com.bridor.app.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bridor.app.data.database.BridorDatabase
import com.bridor.app.data.database.DailyStepsEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DailyResetWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val db = BridorDatabase.getInstance(applicationContext)
            val today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

            // Ensure today row exists with 0 steps
            db.stepsDao().upsert(
                DailyStepsEntity(
                    date = today,
                    steps = 0,
                    distanceMeters = 0f,
                    calories = 0f,
                    activeMinutes = 0,
                    goal = 10000
                )
            )
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
