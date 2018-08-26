package ru.shadowsparky.scheduler.SchedulersEdit.schedulers_show

import ru.shadowsparky.scheduler.SchedulersEdit.SchedulersEditPresenter
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.SchedulersEdit.schedulers_add.SchedulersAdd

class SchedulersShowPresenter(
        private val view: SchedulersShow.View,
        private val model: SchedulersShow.Model
) : SchedulersEditPresenter(model, view), SchedulersShow.Presenter{
    init {
        view.enableChecking()
    }
    override fun onScheduleDelete(item: Schedulers, handler: SchedulersAdd.HandleResult, loading: (Boolean) -> Unit) {
        model.deleteRequest(item, handler, loading)
    }
}