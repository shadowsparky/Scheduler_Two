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

    fun getItemWithInflate(inflater: MenuInflater, menu: Menu, id: Int) : MenuItem {
        inflater.inflate(R.menu.add_schedule_menu, menu)
        return menu.findItem(id)
    }

    fun getItem(menu: Menu, id: Int) : MenuItem = menu.findItem(id)

    fun setBackButton(item: MenuItem, activity: AppCompatActivity) {
        if (item.itemId == android.R.id.home) {
            activity.onBackPressed()
        }
    }
}