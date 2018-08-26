package ru.shadowsparky.scheduler.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.time_dialog.*
import kotlinx.android.synthetic.main.time_dialog.view.*
import org.reactivestreams.Subscription
import ru.shadowsparky.scheduler.R

open class TimeDialog(
        context: Context,
        private val callback: PublishSubject<String>
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_dialog)
        time_picker.setIs24HourView(true)
        choose_time.setOnClickListener { onTimeChoosed() }
    }

    private fun onTimeChoosed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            callback.onNext("${time_picker.hour}:${time_picker.minute}")
        } else {
            callback.onNext("${time_picker.currentHour}:${time_picker.currentMinute}")
        }
        this.hide()
    }
}