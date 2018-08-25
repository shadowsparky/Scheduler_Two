package ru.shadowsparky.scheduler.room_utils

import androidx.room.*

@Dao
open interface Schedulers_Dao {
    @Query("SELECT * FROM Schedulers")
    fun getAll() : Array<Schedulers>

    @Query("SELECT * FROM Schedulers WHERE id = :id")
    fun get(id: Long) : Schedulers

    @Insert
    fun add(data: Schedulers) : Long

    @Update
    fun update(data: Schedulers)

    @Delete
    fun delete(data: Schedulers)
}