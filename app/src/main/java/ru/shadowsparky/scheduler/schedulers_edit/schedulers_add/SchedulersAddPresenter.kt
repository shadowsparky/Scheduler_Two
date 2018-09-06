package ru.shadowsparky.scheduler.schedulers_edit.schedulers_add

import ru.shadowsparky.scheduler.schedulers_edit.SchedulersEditPresenter

class SchedulersAddPresenter(
        private val view: SchedulersAddView,
        private val model: SchedulersAddModel
) : SchedulersEditPresenter(model, view) {
    init {
        view.enableChecking()
    }
}