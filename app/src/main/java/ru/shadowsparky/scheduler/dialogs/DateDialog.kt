package ru.shadowsparky.scheduler.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.date_dialog.*
import ru.shadowsparky.scheduler.R

open class DateDialog(
        context: Context,
        private val callback: PublishSubject<String>
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_dialog)
        choose_date.setOnClickListener { onDateChoosed() }
    }

    private fun onDateChoosed() {
        callback.onNext("${date_picker.year}.${date_picker.month.toInt() + 1}.${date_picker.dayOfMonth}")
        this.hide()
    }
}