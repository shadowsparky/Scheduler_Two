package ru.shadowsparky.scheduler.utils

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_schedulers_add.*
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAdd
import ru.shadowsparky.scheduler.schedulers_show.SchedulersShow

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