package ru.shadowsparky.scheduler.schedulers_show

import ru.shadowsparky.scheduler.schedulers_add.SchedulersAdd
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import ru.shadowsparky.scheduler.utils.LogUtils
import ru.shadowsparky.scheduler.NotificationScheduler
import ru.shadowsparky.scheduler.room_utils.DatabaseInitialization
import ru.shadowsparky.scheduler.room_utils.Schedulers

class SchedulersShowModel(
        val context: Context,
        val data: Schedulers,
        val position: Int
) : SchedulersShow.Model {

    companion object {
        val MODE = "MODE"
        val RESULT = "RESULT"
        val DELETE_MODE = "DELETE"
        val UPDATE_MODE = "UPDATE"
        val POSITION = "POSITION"
    }

    override fun deleteRequest(item: Schedulers, handler: SchedulersAdd.HandleResult) {
        Observable.just("mock")
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .map { DatabaseInitialization.getDB(context).getSchedulesDB() }
                .doOnNext { it.delete(item) }
                .doOnNext { NotificationScheduler(context).removeSchedule(item.id!!.toInt()) }
                .map { Intent().putExtra(RESULT, data)
                        .putExtra(MODE, DELETE_MODE)
                        .putExtra(POSITION, position)}
                .subscribeBy(
                        onNext = { handler.handleResult(it) },
                        onError = { LogUtils.print("REMOVE ITEM FAILED. ERROR: '$it'") },
                        onComplete = { LogUtils.print("DELETE completed successfully") }
                )
    }

    override fun schedule(date: String, time: String, title: String, text: String, handler: SchedulersAdd.HandleResult) {
        val db = DatabaseInitialization.getDB(context).getSchedulesDB()
        Observable.just(data)
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .doOnNext {
                    it.date = date
                    it.time = time
                    it.title = title
                    it.text = text
                    db.update(it)
                    NotificationScheduler(context).scheduleNotification(date, time, title, text, it.id!!.toInt())
                }
                .map {
                    Intent()
                        .putExtra(RESULT, it)
                        .putExtra(MODE, UPDATE_MODE)
                        .putExtra(POSITION, position)
                }
                .subscribeBy(
                        onNext = { handler.handleResult(it) },
                        onError = { "UPDATE ITEM FAILED. ERROR: '$it'" },
                        onComplete = { "Update completed successfully" }
                )
    }
}