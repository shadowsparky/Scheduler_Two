package ru.shadowsparky.scheduler.utils

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAdd
import ru.shadowsparky.scheduler.schedulers_show.SchedulersShow

class MenuUtils {

    fun setAddMenu(inflater: MenuInflater, menu: Menu) {
        inflater.inflate(R.menu.add_schedule_menu, menu)
        menu.findItem(R.id.schedule_add).setOnMenuItemClickListener {
            return@setOnMenuItemClickListener true
        }
        menu.findItem(R.id.schedule_delete).isVisible = false
    }

    fun getItemWithInflate(inflater: MenuInflater, menu: Menu, id: Int) : MenuItem {
        inflater.inflate(R.menu.add_schedule_menu, menu)
        return menu.findItem(id)
    }

    fun getItem(menu: Menu, id: Int) : MenuItem = menu.findItem(id)

    fun setAddScheduleMenu(date: String, time: String, title: String, text: String, presenter: SchedulersAdd.SchedulersAddPresenter, callback: Schedule_Menu_Utils) {
        presenter.onScheduleCompleted(date, time, title, text, callback.resultCallback())
    }

    fun setShowMenu(menu: Menu, item: Schedulers, presenter: SchedulersShow.Presenter) {
        menu.findItem(R.id.schedule_delete).setOnMenuItemClickListener {
//            presenter.onScheduleDelete(item)
            return@setOnMenuItemClickListener true
        }
        menu.findItem(R.id.schedule_delete).isVisible = true
    }

    fun setBackButton(item: MenuItem, activity: AppCompatActivity) {
        if (item.itemId == android.R.id.home) {
            activity.onBackPressed()
        }
    }
}