package ru.shadowsparky.scheduler.SchedulersEdit.schedulers_add

import ru.shadowsparky.scheduler.SchedulersEdit.SchedulersEditPresenter

class SchedulersAddPresenter(
        private val view: SchedulersAddView,
        private val model: SchedulersAddModel
) : SchedulersEditPresenter(model, view) {
    init {
        view.enableChecking()
    }
}