package ru.shadowsparky.scheduler.schedulers_show

import ru.shadowsparky.scheduler.adapters.SchedulersList
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAdd

class SchedulersShowPresenter(
        private val view: SchedulersShow.View,
        private val model: SchedulersShow.Model
) : SchedulersShow.Presenter {

    init {
        onEnableChecking()
    }

    override fun onEnableChecking() {
        view.enableChecking()
    }

    override fun onFinish(date: String, time: String, title: String, text: String, callback: SchedulersAdd.HandleResult) {
        if (date.isNotBlank() and time.isNotBlank()) {
            model.createSchedule(date, time, title, text, callback)
        } else {
            model.createTask(title, text, callback)
        }
    }

    override fun onScheduleDelete(item: Schedulers, handler: SchedulersAdd.HandleResult) {
        model.deleteRequest(item, handler)
    }
}