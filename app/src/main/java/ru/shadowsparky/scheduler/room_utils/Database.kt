package ru.shadowsparky.scheduler.room_utils

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Schedulers::class), version = 2)
open abstract class Database : RoomDatabase() {
    abstract fun getSchedulesDB() : Schedulers_Dao
}