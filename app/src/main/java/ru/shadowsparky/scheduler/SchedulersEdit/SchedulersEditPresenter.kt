package ru.shadowsparky.scheduler.SchedulersEdit

import io.reactivex.Scheduler
import ru.shadowsparky.scheduler.SchedulersEdit.schedulers_add.SchedulersAdd

abstract class SchedulersEditPresenter(
        private var model: SchedulersAdd.SchedulersAddModel,
        private var view: SchedulersAdd.SchedulersAddView
) : SchedulersAdd.SchedulersAddPresenter {

    override fun onEnableChecking() {
        view.enableChecking()
    }

    override fun onFinish(date: String, time: String, title: String, text: String, callback: SchedulersAdd.HandleResult, loading: (Boolean) -> Unit) {
        loading(true)
        if (date.isNotBlank() and time.isNotBlank()) {
            model.scheduleRequest(date, time, title, text, callback, loading)
        } else {
            model.taskRequest(title, text, callback, loading)
        }
    }
}