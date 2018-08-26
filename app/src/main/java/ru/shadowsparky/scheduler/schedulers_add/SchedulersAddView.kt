/*
 * Samsonov Eugene (c) 2018.
 */

package ru.shadowsparky.scheduler.schedulers_add

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_schedulers_add.*
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.dialogs.ScheduleDialog
import ru.shadowsparky.scheduler.utils.LogUtils
import ru.shadowsparky.scheduler.utils.MenuUtils
import ru.shadowsparky.scheduler.utils.Schedule_Menu_Utils
import ru.shadowsparky.scheduler.utils.Validator

class SchedulersAddView : AppCompatActivity(), SchedulersAdd.SchedulersAddView {
    private lateinit var presenter: SchedulersAddPresenter
    private var schedule_menu_utils = Schedule_Menu_Utils(this)
    private val utils = MenuUtils()
    private var check = false
    private var date = ""
    private var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedulers_add)
        setSupportActionBar(add_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        presenter = SchedulersAddPresenter(this, SchedulersAddModel(applicationContext))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val addButton = utils.getItemWithInflate(menuInflater, menu!!, R.id.schedule_add)
        addButton.setOnMenuItemClickListener {
            if (check) {
                presenter.onFinish(date, time, choosed_title.text.toString(),
                        choosed_text.text.toString(), schedule_menu_utils.resultCallback())
            } else {
                choosed_text.error = "Это поле обязательно для заполнения"
            }
            return@setOnMenuItemClickListener true
        }
        val callback: (String, String) -> Unit = {date, time ->
            this.date = date
            this.time = time
        }
        val scheduleButton = utils.getItem(menu, R.id.schedule_try)
        scheduleButton.setOnMenuItemClickListener {
            ScheduleDialog(this, callback, date, time).show()
            return@setOnMenuItemClickListener true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        utils.setBackButton(item!!, this)
        LogUtils.print("Clicked")
        return true
    }

    override fun hide(intent: Intent) {
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun enableChecking() {
        val callback: (Boolean) -> Unit = { check = it }
        Validator().verifyText(choosed_text, callback)
    }

    override fun showToast(message_id: Int) = Toast.makeText(this, message_id, Toast.LENGTH_SHORT).show()
}
