package ru.shadowsparky.scheduler.room_utils

import android.app.Application
import android.content.Context
import androidx.room.Room

class DatabaseInitialization: Application() {

    companion object {
        private var db: Database? = null

        fun getDB(context: Context) : Database {
            if (db == null) {
                db = Room.databaseBuilder(context, Database::class.java, "db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return db!!
        }
    }
}