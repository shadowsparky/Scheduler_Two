package ru.shadowsparky.scheduler.schedulers_edit.schedulers_add

import android.content.Intent

interface SchedulersAdd {
    interface SchedulersAddView {
        fun showToast(message_id: Int)
        fun hide(intent: Intent)
        fun enableChecking()
        fun setLoading(result: Boolean)
    }
    interface SchedulersAddPresenter {
        fun onEnableChecking()
        fun onFinish(date: String, time: String, title: String, text: String, callback: HandleResult, loading: (Boolean) -> Unit)
    }
    interface SchedulersAddModel {
        fun taskRequest(title: String, text: String, callback: HandleResult, loading: (Boolean) -> Unit)
        fun scheduleRequest(date: String, time: String, title: String, text: String, callback: HandleResult, loading: (Boolean) -> Unit)
    }
    interface HandleResult {
        fun handleResult(intent: Intent)
    }
}