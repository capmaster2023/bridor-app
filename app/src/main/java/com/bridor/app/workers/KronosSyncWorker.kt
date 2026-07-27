package com.bridor.app.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bridor.app.data.database.BridorDatabase
import com.bridor.app.data.repository.KronosRepository

class KronosSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val db = BridorDatabase.getInstance(applicationContext)
            val repo = KronosRepository(db)
            repo.sync()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
