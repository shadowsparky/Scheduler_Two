package ru.shadowsparky.scheduler.schedulers_show

import ru.shadowsparky.scheduler.adapters.SchedulersList
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAdd

class SchedulersShowPresenter(
        private val view: SchedulersShow.View,
        private val model: SchedulersShow.Model
) : SchedulersShow.Presenter {

    override fun onScheduleDelete(item: Schedulers, handler: SchedulersAdd.HandleResult) {
        model.deleteRequest(item, handler)
    }

    override fun onScheduleCompleted(date: String, time: String, title: String, text: String, handleResult: SchedulersAdd.HandleResult) {
        model.schedule(date, time, title, text, handleResult)
    }
}