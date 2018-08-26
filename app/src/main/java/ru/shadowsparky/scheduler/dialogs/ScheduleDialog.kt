package ru.shadowsparky.scheduler.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.schedule_dialog.*
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.utils.Validator


class ScheduleDialog(context: Context, val callback: (String, String) -> Unit,
                     var date: String = "", var time: String = ""
) : Dialog(context) {
    private var check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_dialog)
        choosed_date.text = date
        choosed_time.text = time
    }

    override fun onStart() {
        super.onStart()
        onDateClick()
        onTimeClick()
        onOkClick()
        onCancelClick()
        enableChecking()
    }

    fun onDateClick() {
        val dateCallback = PublishSubject.create<String>()
        dateCallback.subscribeBy(
            onNext = {
                date = it
                choosed_date.text = date
            }
        )
        choosed_date.setOnClickListener {
            DateDialog(context, dateCallback).show()
        }
    }

    fun onTimeClick() {
        val timeCallback = PublishSubject.create<String>()
        timeCallback.subscribeBy(
            onNext = {
                time = it
                choosed_time.text = time
            }
        )

        choosed_time.setOnClickListener {
            TimeDialog(context, timeCallback).show()
        }
    }

    fun onOkClick() {
        schedule_ok.setOnClickListener {
            callback(date, time)
            this.hide()
        }
    }

    fun onCancelClick() {
        schedule_cancel.setOnClickListener {
            this.hide()
        }
    }

    fun enableChecking() {
        val checkCallback: (Boolean) -> Unit = {
            schedule_ok.isEnabled = it
        }
        Validator().verifySchedule(choosed_date, choosed_time, checkCallback)
    }
}