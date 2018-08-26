package ru.shadowsparky.scheduler.utils

import android.widget.Button
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_schedulers_add.*

class Validator {

    fun verify(date: Button, time: Button, title: EditText, text: EditText, callback: (Boolean) -> Unit) {
        Observables.combineLatest(
                RxTextView.textChanges(date),
                RxTextView.textChanges(time),
                RxTextView.textChanges(title),
                RxTextView.textChanges(text))
                { date, time, title, text -> (date.isNotBlank()) and (time.isNotBlank()) and (title.isNotBlank()) and (text.isNotBlank()) }
                .subscribeBy(
                        onNext = { callback(it) }
                )
    }
}