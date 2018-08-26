package ru.shadowsparky.scheduler.utils

import android.content.Intent
import android.widget.Button
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import ru.shadowsparky.scheduler.SchedulersEdit.schedulers_add.SchedulersAdd

open class Schedule_Menu_Utils(private val view: SchedulersAdd.SchedulersAddView) {

    fun getChooseCallback(button: Button) : PublishSubject<String> {
        val callback = PublishSubject.create<String>()
        callback.subscribeBy (
                onNext = { button.text = it },
                onError = { LogUtils.print("ERROR: $it") }
        )
        return callback
    }

    fun resultCallback() : SchedulersAdd.HandleResult {
        return object : SchedulersAdd.HandleResult {
            override fun handleResult(intent: Intent) {
                view.hide(intent)
            }
        }
    }
}