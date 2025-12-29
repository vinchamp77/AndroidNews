/*
 * Copyright 2025 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.app.workers

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.vinchamp77.buildutils.BuildExt
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepoStatus
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.main.MainActivity

class SyncWorker(
    private val appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {
    private val notificationChannelId = "AndroidNewsNotificationChannelId"

    override suspend fun doWork(): Result {
        val repository = ArticlesRepositoryImpl.getInstance(applicationContext)
        val status = repository.refresh()

        if (status is ArticlesRepoStatus.Success) {
            if (ActivityCompat.checkSelfPermission(
                    appContext,
                    Manifest.permission.POST_NOTIFICATIONS,
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                if (status.withNewArticles) {
                    with(NotificationManagerCompat.from(applicationContext)) {
                        notify(0, createNotification())
                    }
                }
            }

            return Result.success()
        }

        return Result.retry()
    }

    private fun createNotification(): Notification {
        createNotificationChannel()

        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)

        val pendingIntentFlag =
            if (BuildExt.VERSION.isFlagImmutableSupported()) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        val mainActivityPendingIntent =
            PendingIntent.getActivity(
                applicationContext,
                0,
                mainActivityIntent,
                pendingIntentFlag,
            )

        return NotificationCompat
            .Builder(applicationContext, notificationChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("New Articles Arrived!")
            .setContentIntent(mainActivityPendingIntent)
            .setAutoCancel(true)
            .build()
    }

    private fun createNotificationChannel() {
        if (BuildExt.VERSION.isNotificationChannelSupported()) {
            val notificationChannel =
                NotificationChannel(
                    notificationChannelId,
                    "Sync Articles",
                    NotificationManager.IMPORTANCE_DEFAULT,
                )

            val notificationManager: NotificationManager? =
                ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)

            notificationManager?.createNotificationChannel(notificationChannel)
        }
    }
}
