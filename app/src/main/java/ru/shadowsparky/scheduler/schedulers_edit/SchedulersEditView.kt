package ru.shadowsparky.scheduler.schedulers_edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_schedulers_add.*
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.dialogs.ScheduleDialog
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_edit.schedulers_add.SchedulersAdd
import ru.shadowsparky.scheduler.schedulers_edit.schedulers_show.SchedulersShowPresenter
import ru.shadowsparky.scheduler.utils.MenuUtils
import ru.shadowsparky.scheduler.utils.ScheduleMenuUtils
import ru.shadowsparky.scheduler.utils.Validator

abstract class SchedulersEditView() : SchedulersAdd.SchedulersAddView, AppCompatActivity() {
    protected var check = false
    protected val schedule_menu_utils = ScheduleMenuUtils(this)
    protected val utils = MenuUtils()
    protected var date = ""
    protected var time = ""
    protected var item: Schedulers? = null
    protected var presenter: SchedulersAdd.SchedulersAddPresenter? = null
    override fun showToast(message_id: Int) = Toast.makeText(this, message_id, Toast.LENGTH_SHORT).show()

    override fun setLoading(result: Boolean) {
        if (result) {
            add_loading.visibility = VISIBLE
        } else {
            add_loading.visibility = GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedulers_add)
        setSupportActionBar(add_toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
    }

    override fun hide(intent: Intent) {
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun enableChecking() {
        val callback: (Boolean) -> Unit = { check = it }
        Validator().verifyText(choosed_text, callback)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val scheduleButton = utils.getItemWithInflate(menuInflater, menu!!, R.id.schedule_try)
        val addButton = utils.getItem(menu!!, R.id.schedule_add)
        val setTimeCallback: (String, String) -> Unit = {date, time ->
            this.date = date
            this.time = time
        }
        scheduleButton.setOnMenuItemClickListener {
            ScheduleDialog(this, setTimeCallback, date, time).show()
            return@setOnMenuItemClickListener true
        }
        val loading: (Boolean) -> Unit = { setLoading(it) }
        addButton.setOnMenuItemClickListener {
            if (check) {
                presenter?.onFinish(date, time, choosed_title.text.toString(),
                        choosed_text.text.toString(), schedule_menu_utils.resultCallback(), loading)
            } else {
                choosed_text.error = "Это поле обязательно для заполнения"
            }
            return@setOnMenuItemClickListener true
        }
        if (presenter is SchedulersShowPresenter) {
            val removeScheduleButton = utils.getItem(menu!!, R.id.schedule_delete)
            removeScheduleButton.isVisible = true
            removeScheduleButton.setOnMenuItemClickListener {
                (presenter as SchedulersShowPresenter).onScheduleDelete(item!!, schedule_menu_utils.resultCallback(), loading)
                return@setOnMenuItemClickListener true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}