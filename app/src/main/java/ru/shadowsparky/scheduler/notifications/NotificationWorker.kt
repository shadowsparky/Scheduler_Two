package ru.shadowsparky.scheduler

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.shadowsparky.scheduler.utils.LogUtils

open class NotificationWorker(val context: Context) {
    val NOTIFICATION_ID: Int = 0
    val NOTIFICATION_CHANNEL_NAME: String = "DEFAULT_NAME"
    val NOTIFICATION_CHANNEL_ID: String = "DEFAULT_ID"

    private val compat_manager = NotificationManagerCompat.from(context)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
        )
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        channel.enableVibration(true)
        channel.vibrationPattern = LongArray(5){ 100; 200; 300; 400; 500}
        channel.enableLights(true)
        channel.lightColor = Color.GREEN
        channel.description = "Test Notification"
        val manager  = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        LogUtils.print("CHANNEL WITH ID $NOTIFICATION_CHANNEL_ID CREATED")
    }


    private fun sendLowerOreoNotification(title: String?, message: String?) {
        val builder = NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setVibrate(LongArray(5){ 100; 200; 300; 400; 500})
                .setVisibility(Notification.VISIBILITY_PUBLIC)
        compat_manager.notify(NOTIFICATION_ID, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendOreoNotification(title: String?, message: String?) {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(
                context,
                NOTIFICATION_CHANNEL_ID
        )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setVibrate(LongArray(5){ 100; 200; 300; 400; 500})
                .setVisibility(Notification.VISIBILITY_PUBLIC)
        compat_manager.notify(NOTIFICATION_ID, builder.build())
    }

    fun sendNotification(title: String?, message: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sendOreoNotification(title, message)
        } else {
            sendLowerOreoNotification(title, message)
        }
        LogUtils.print("NOTIFICATION WITH ID $NOTIFICATION_CHANNEL_ID SHOWED")
    }
}