package ru.shadowsparky.scheduler.schedulers_add

import android.content.Intent
import ru.shadowsparky.scheduler.NotificationScheduler
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.TimeAndDateParser.Companion.DAY
import ru.shadowsparky.scheduler.TimeAndDateParser.Companion.HOUR
import ru.shadowsparky.scheduler.TimeAndDateParser.Companion.MINUTE
import ru.shadowsparky.scheduler.TimeAndDateParser.Companion.MONTH
import ru.shadowsparky.scheduler.TimeAndDateParser.Companion.YEAR

class SchedulersAddPresenter(
        private val view: SchedulersAdd.SchedulersAddView,
        private val model: SchedulersAdd.SchedulersAddModel
) : SchedulersAdd.SchedulersAddPresenter {
    override fun onScheduleCompleted(date: String, time: String, title: String, text: String, handleResult: SchedulersAdd.HandleResult) {
        model.schedule(date, time, title, text, handleResult)
    }

}