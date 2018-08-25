package ru.shadowsparky.scheduler.schedulers_menu

import android.content.Context
import ru.shadowsparky.scheduler.room_utils.DatabaseInitialization
import ru.shadowsparky.scheduler.room_utils.Schedulers

class SchedulersModel(val context: Context): SchedulersMenu.SchedulersModel {
    override fun getDataFromDB(): Array<Schedulers> {
        return DatabaseInitialization.getDB(context).getSchedulesDB().getAll()
    }
}