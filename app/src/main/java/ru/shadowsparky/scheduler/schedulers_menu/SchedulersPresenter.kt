package ru.shadowsparky.scheduler.schedulers_menu

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.shadowsparky.scheduler.schedulers_menu.SchedulersView.Companion.APPOINTMENT_ADD_CODE
import ru.shadowsparky.scheduler.schedulers_menu.SchedulersView.Companion.APPOINTMENT_SHOW_CODE
import ru.shadowsparky.scheduler.schedulers_edit.schedulers_show.SchedulersShowModel.Companion.DELETE_MODE
import ru.shadowsparky.scheduler.schedulers_edit.schedulers_show.SchedulersShowModel.Companion.UPDATE_MODE
import ru.shadowsparky.scheduler.utils.LogUtils

class SchedulersPresenter(
        private val view: SchedulersMenu.SchedulersView,
        private val model: SchedulersMenu.SchedulersModel
) : SchedulersMenu.SchedulersPresenter {

    override fun onSchedulesListEdited(mode: String?, requestCode: Int, data: ru.shadowsparky.scheduler.room_utils.Schedulers, position: Int) {
        when (requestCode) {
            APPOINTMENT_ADD_CODE -> {
                view.addItem(data)
            }
            APPOINTMENT_SHOW_CODE -> {
                if (mode == DELETE_MODE) {
                    view.removeItem(position)
                } else if (mode == UPDATE_MODE) {
                    view.updateItem(data, position)
                }
            }
        }
    }

    override fun onSchedulesLoading() {
        view.setLoading(true)
        Observable.just("")
                .observeOn(Schedulers.io())
                .map { model.getDataFromDB() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                        onNext = {
                            if (it.isNotEmpty()) {
                                view.setListVisible(true)
                            } else
                                view.setListVisible(false)
                            view.setAdapter(it)
                            view.setLoading(false)
                        },
                        onError = { LogUtils.print("ERROR: Adapter can't setting. '$it'")}
                )
    }

    override fun onAddAppointmentClicked() {
        view.navigateToAppointmentAdd()
    }
}