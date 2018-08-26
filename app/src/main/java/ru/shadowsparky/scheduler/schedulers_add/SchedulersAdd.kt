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
        fun onScheduleCompleted(date: String, time: String, title: String, text: String, handleResult: HandleResult)
    }
    interface SchedulersAddModel {
        fun schedule(date: String, time: String, title: String, text: String, handleResult: HandleResult)
    }
    interface HandleResult {
        fun handleResult(intent: Intent)
    }
}