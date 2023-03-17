package vtsen.hashnode.dev.androidnews.app

import android.app.Application
import androidx.work.*
import vtsen.hashnode.dev.androidnews.app.workers.SyncWorker
import java.util.concurrent.TimeUnit

class AndroidNewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(24, TimeUnit.HOURS)
            //.setInitialDelay(24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            "SyncWorker",
            ExistingPeriodicWorkPolicy.UPDATE,
            syncWorkRequest)
    }
}