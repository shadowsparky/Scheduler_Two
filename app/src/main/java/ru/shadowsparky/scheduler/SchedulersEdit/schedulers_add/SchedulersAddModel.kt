package ru.shadowsparky.scheduler.SchedulersEdit.schedulers_add

import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import ru.shadowsparky.scheduler.utils.LogUtils
import ru.shadowsparky.scheduler.NotificationScheduler
import ru.shadowsparky.scheduler.room_utils.DatabaseInitialization
import ru.shadowsparky.scheduler.room_utils.Schedulers

class SchedulersAddModel(
        val context: Context
) : SchedulersAdd.SchedulersAddModel {

    override fun scheduleRequest(date: String, time: String, title: String, text: String, callback: SchedulersAdd.HandleResult) {
        val db = DatabaseInitialization.getDB(context).getSchedulesDB()
        val data = Schedulers()
        Observable.just(data)
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .doOnNext {
                    it.date = date
                    it.time = time
                    it.title = title
                    it.text = text
                    it.id = db.add(it).toInt()
                    NotificationScheduler(context).scheduleNotification(date, time, title, text, it.id!!)
                }
                .map { Intent().putExtra("RESULT", it) }
                .subscribeBy (
                        onNext = { callback.handleResult(it) },
                        onError = { LogUtils.print("SCHEDULING ERROR: $it") },
                        onComplete = { LogUtils.print("Scheduling successfully!")}
                )
    }

    override fun taskRequest(title: String, text: String, callback: SchedulersAdd.HandleResult) {
        val db = DatabaseInitialization.getDB(context).getSchedulesDB()
        val data = Schedulers()
        Observable.just(data)
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .doOnNext {
                    it.date = ""
                    it.time = ""
                    it.title = title
                    it.text = text
                    it.id = db.add(it).toInt()
                }
                .map { Intent().putExtra("RESULT", it) }
                .subscribeBy (
                        onNext = { callback.handleResult(it) },
                        onError = { LogUtils.print("CREATE TASK ERROR: $it") },
                        onComplete = { LogUtils.print("Task created successfully!")}
                )
    }

}