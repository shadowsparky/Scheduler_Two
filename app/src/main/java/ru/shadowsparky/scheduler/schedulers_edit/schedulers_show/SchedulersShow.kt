package ru.shadowsparky.scheduler.schedulers_edit.schedulers_show

import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_edit.schedulers_add.SchedulersAdd

interface SchedulersShow {
    interface View : SchedulersAdd.SchedulersAddView {
    }
    interface Presenter : SchedulersAdd.SchedulersAddPresenter {
        fun onScheduleDelete(item: Schedulers, handler: SchedulersAdd.HandleResult, loading: (Boolean) -> Unit)
    }
    interface Model : SchedulersAdd.SchedulersAddModel {
        fun deleteRequest(item: Schedulers, handler: SchedulersAdd.HandleResult, loading: (Boolean) -> Unit)
    }
}