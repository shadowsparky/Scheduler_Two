package ru.shadowsparky.scheduler.schedulers_menu

import androidx.cardview.widget.CardView
import ru.shadowsparky.scheduler.room_utils.Schedulers
import java.nio.file.attribute.PosixFileAttributeView


interface SchedulersMenu {
    interface SchedulersView {
        fun navigateToAppointmentShow(item: Schedulers, card: CardView, position: Int)
        fun navigateToAppointmentAdd()
        fun setAdapter(data: Array<Schedulers>)
        fun updateItem(item: Schedulers, index: Int)
        fun removeItem(index: Int)
        fun addItem(item: Schedulers)
    }
    interface SchedulersPresenter {
        fun onAddAppointmentClicked()
        fun onSchedulesLoading()
        fun onSchedulesListEdited(mode: String?, requestCode: Int, data: Schedulers, position: Int)
    }
    interface SchedulersModel {
        fun getDataFromDB() : Array<Schedulers>
    }

    interface Touch {
        fun onItemTouched(data: Schedulers, card: CardView, index: Int)
    }
}