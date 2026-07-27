package com.bridor.app.workers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val workManager = WorkManager.getInstance(context)

            val dailyReset = PeriodicWorkRequestBuilder<DailyResetWorker>(24, TimeUnit.HOURS).build()
            workManager.enqueueUniquePeriodicWork(
                "daily_reset",
                ExistingPeriodicWorkPolicy.KEEP,
                dailyReset
            )

            val kronosSync = PeriodicWorkRequestBuilder<KronosSyncWorker>(30, TimeUnit.MINUTES).build()
            workManager.enqueueUniquePeriodicWork(
                "kronos_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                kronosSync
            )
        }
    }
}
