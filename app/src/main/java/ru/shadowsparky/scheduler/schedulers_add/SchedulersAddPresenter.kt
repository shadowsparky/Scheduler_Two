package ru.shadowsparky.scheduler.schedulers_add

class SchedulersAddPresenter(
        private val view: SchedulersAdd.SchedulersAddView,
        private val model: SchedulersAdd.SchedulersAddModel
) : SchedulersAdd.SchedulersAddPresenter {

    init {
        onEnableChecking()
    }

    override fun onEnableChecking() {
        view.enableChecking()
    }

    override fun onFinish(date: String, time: String, title: String, text: String, handleResult: SchedulersAdd.HandleResult) {
        if ((date.isNotBlank()) and (time.isNotEmpty())) {
            model.createSchedule(date, time, title, text, handleResult)
        } else {
            model.createTask(title, text, handleResult)
        }
    }
}