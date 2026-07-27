package com.bridor.app

import android.app.Application
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.bridor.app.workers.DailyResetWorker
import com.bridor.app.workers.KronosSyncWorker
import java.util.concurrent.TimeUnit

class BridorApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        scheduleWorkers()
    }

    private fun scheduleWorkers() {
        val workManager = WorkManager.getInstance(this)

        // Reset quotidien à minuit (approximatif via 24h)
        val dailyReset = PeriodicWorkRequestBuilder<DailyResetWorker>(24, TimeUnit.HOURS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "daily_reset",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyReset
        )

        // Sync Kronos toutes les 30 minutes
        val kronosSync = PeriodicWorkRequestBuilder<KronosSyncWorker>(30, TimeUnit.MINUTES)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "kronos_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            kronosSync
        )
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}
