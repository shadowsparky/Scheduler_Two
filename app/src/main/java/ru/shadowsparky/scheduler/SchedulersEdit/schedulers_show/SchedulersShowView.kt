package ru.shadowsparky.scheduler.SchedulersEdit.schedulers_show

import kotlinx.android.synthetic.main.activity_schedulers_add.*
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.SchedulersEdit.SchedulersEditView


class SchedulersShowView : SchedulersEditView(), SchedulersShow.View{
    private var position: Int = -1

    override fun onStart() {
        super.onStart()
        item = intent.getSerializableExtra("Saved_Data") as Schedulers
        position = intent.getIntExtra("Position", -1)
        presenter = SchedulersShowPresenter(this, SchedulersShowModel(applicationContext, item!!, position))
        choosed_title.setText(item!!.title)
        choosed_text.setText(item!!.text)
        date = item!!.date!!
        time = item!!.time!!
    }
}