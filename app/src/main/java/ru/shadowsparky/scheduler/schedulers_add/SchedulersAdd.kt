package ru.shadowsparky.scheduler.schedulers_add

import android.content.Intent
import android.view.MenuItem
import io.reactivex.subjects.PublishSubject
import java.util.*

interface SchedulersAdd {
    interface SchedulersAddView {
        fun showToast(message_id: Int)
        fun hide(intent: Intent)
        fun enableChecking()
    }
    interface SchedulersAddPresenter {
        fun onEnableChecking()
        fun onFinish(date: String, time: String, title: String, text: String, callback: HandleResult)
    }
    interface SchedulersAddModel {
        fun createTask(title: String, text: String, callback: HandleResult)
        fun createSchedule(date: String, time: String, title: String, text: String, callback: HandleResult)
    }
    interface HandleResult {
        fun handleResult(intent: Intent)
    }
}