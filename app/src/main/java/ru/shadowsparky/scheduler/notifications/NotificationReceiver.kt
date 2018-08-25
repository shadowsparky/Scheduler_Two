package ru.shadowsparky.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var title = intent.getStringExtra("Title")
        var message = intent.getStringExtra("Message")
        val code = intent.getIntExtra("Code", -1)
        if (code == -1) {
            title = "Привет!"
            message = "У тебя новое напоминание!"
        }
        val notificationWorker = NotificationWorker(context)
        notificationWorker.sendNotification(title, message)
    }
}