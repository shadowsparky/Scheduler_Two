package ru.shadowsparky.scheduler.room_utils

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
open class Schedulers : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var date: String? = null
    var time: String? = null
    var title: String? = null
    var text: String? = null
}