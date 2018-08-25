/*
 * Samsonov Eugene (c) 2018.
 */

package ru.shadowsparky.scheduler.schedulers_menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_schedulers_view.*
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.adapters.SchedulersList
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAddView
import ru.shadowsparky.scheduler.schedulers_show.SchedulersShowModel.Companion.MODE
import ru.shadowsparky.scheduler.schedulers_show.SchedulersShowModel.Companion.POSITION
import ru.shadowsparky.scheduler.schedulers_show.SchedulersShowView
import ru.shadowsparky.scheduler.utils.LogUtils


class SchedulersView : AppCompatActivity(), SchedulersMenu.SchedulersView {
    companion object {
        val APPOINTMENT_SHOW_CODE = 1
        val APPOINTMENT_ADD_CODE = 0
    }
    private lateinit var presenter: SchedulersMenu.SchedulersPresenter
    private var itemTouched: SchedulersMenu.Touch? = null
    private var adapter: SchedulersList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedulers_view)
        schedule_add.setOnClickListener {
            presenter.onAddAppointmentClicked()
        }
        presenter = SchedulersPresenter(this, SchedulersModel(applicationContext))
        itemTouched = object: SchedulersMenu.Touch {
            override fun onItemTouched(data: Schedulers, card: CardView, position: Int) {
                navigateToAppointmentShow(data, card, position)
            }
        }
        presenter.onSchedulesLoading()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
                val position = data!!.getIntExtra(POSITION, -1)
                val item = data!!.getSerializableExtra("RESULT") as ru.shadowsparky.scheduler.room_utils.Schedulers
                val mode = data?.getStringExtra(MODE)
                presenter.onSchedulesListEdited(mode, requestCode, item, position)
        }
    }

    override fun removeItem(index: Int) = adapter!!.remove(index)

    override fun addItem(item: Schedulers) = adapter!!.add(item)

    override fun updateItem(item: Schedulers, index: Int) = adapter!!.update(item, index)

    override fun setAdapter(data: Array<ru.shadowsparky.scheduler.room_utils.Schedulers>) {
        list.setHasFixedSize(true)
        list.layoutManager = GridLayoutManager(this, 2)
        adapter = SchedulersList(data.toCollection(ArrayList()), itemTouched!!)
        list.adapter = adapter
        LogUtils.print("Adapter is installed in ${Thread.currentThread().name} thread ${data.toString()}")
    }
    override fun navigateToAppointmentShow(item: Schedulers, card: CardView, position: Int) {
        val intent = Intent(this, SchedulersShowView::class.java)
        intent.putExtra("Saved_Data", item)
        intent.putExtra("Position", position)
        val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, card, "title")
        startActivityForResult(intent, APPOINTMENT_SHOW_CODE, options.toBundle())
    }

    override fun navigateToAppointmentAdd() {
        val intent = Intent(this, SchedulersAddView::class.java)
        startActivityForResult(intent, APPOINTMENT_ADD_CODE)
    }
}
