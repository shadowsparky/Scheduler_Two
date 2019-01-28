package ru.shadowsparky.scheduler
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import ru.shadowsparky.scheduler.utils.LogUtils
import ru.shadowsparky.scheduler.utils.TimeAndDateParser
import java.util.*
import java.util.Calendar.*

open class NotificationScheduler(val context: Context) {
    private val intent = Intent(context, NotificationReceiver::class.java)
    private val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun scheduleNotification(title: String, message: String, calendar: Calendar, code: Int) {
        intent.putExtra("Title", title)
        intent.putExtra("Message", message)
        intent.putExtra("Code", code)
        val pending_intent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // doze
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending_intent)
        } else {
            // non doze
            manager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending_intent)
        }
        LogUtils.print("NOTIFICATION WITH ID $code SCHEDULED")
    }

    fun scheduleNotification(date: String, time: String, title: String, text: String, id: Int) {
        val thread = Thread {
            val parsed_date = TimeAndDateParser.parseDate(date)
            val parsed_time = TimeAndDateParser.parseTime(time)
            val calendar = NotificationScheduler
                    .getCalendar(
                            parsed_date[TimeAndDateParser.YEAR],
                            parsed_date[TimeAndDateParser.MONTH],
                            parsed_date[TimeAndDateParser.DAY],
                            parsed_time[TimeAndDateParser.HOUR],
                            parsed_time[TimeAndDateParser.MINUTE]
                    )
            NotificationScheduler(context)
                    .scheduleNotification(title, text, calendar, id)
        }
        thread.start()
    }

    fun removeSchedule(code: Int) {
        val pending_intent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        manager.cancel(pending_intent)
        LogUtils.print("SCHEDULED NOTIFICATION WITH ID $code DELETED")
    }
    companion object {
        fun getCalendar(year: Int, month: Int, day: Int, hour: Int, minute: Int) : Calendar {
            val calendar = Calendar.getInstance()
            calendar.set(YEAR, year)
            calendar.set(MONTH, month - 1)
            calendar.set(DAY_OF_MONTH, day)
            calendar.set(HOUR_OF_DAY, hour)
            calendar.set(MINUTE, minute)
            calendar.set(SECOND, 0)
            calendar.set(MILLISECOND, 0)
            LogUtils.print("CALENDAR DEBUG: ${calendar.toString()} ")
            return calendar
        }
    }
}