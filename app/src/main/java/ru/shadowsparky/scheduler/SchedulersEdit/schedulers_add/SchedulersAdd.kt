package ru.shadowsparky.scheduler.SchedulersEdit.schedulers_add

import android.content.Intent

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
        fun taskRequest(title: String, text: String, callback: HandleResult)
        fun scheduleRequest(date: String, time: String, title: String, text: String, callback: HandleResult)
    }
    interface HandleResult {
        fun handleResult(intent: Intent)
    }
}