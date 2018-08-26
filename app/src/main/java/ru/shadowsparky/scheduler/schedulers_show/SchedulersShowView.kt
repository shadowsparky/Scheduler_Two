package ru.shadowsparky.scheduler.schedulers_show

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
import ru.shadowsparky.scheduler.adapters.SchedulersList
import ru.shadowsparky.scheduler.dialogs.DateDialog
import ru.shadowsparky.scheduler.dialogs.TimeDialog
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAdd
import ru.shadowsparky.scheduler.schedulers_add.SchedulersAddPresenter
import ru.shadowsparky.scheduler.utils.MenuUtils
import ru.shadowsparky.scheduler.utils.Schedule_Menu_Utils
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.time_dialog.*
import ru.shadowsparky.scheduler.dialogs.ScheduleDialog
import ru.shadowsparky.scheduler.utils.Validator


class SchedulersShowView : AppCompatActivity(), SchedulersShow.View {
    private var data: Schedulers? = null
    private val schedule_menu_utils = Schedule_Menu_Utils(this)
    private val utils = MenuUtils()
    private var time = ""
    private var date = ""
    private var position: Int = -1
    private lateinit var presenter: SchedulersShow.Presenter
    private var check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedulers_add)
        setSupportActionBar(add_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        data = intent.getSerializableExtra("Saved_Data") as Schedulers
        position = intent.getIntExtra("Position", -1)
        presenter = SchedulersShowPresenter(this, SchedulersShowModel(applicationContext, data!!, position))
        initControls()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        utils.setBackButton(item!!, this)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val addButton = utils.getItemWithInflate(menuInflater, menu!!, R.id.schedule_add)
        val deleteButton = utils.getItem(menu, R.id.schedule_delete)
        val set_timeButton = utils.getItem(menu, R.id.schedule_try)
        addButton.setOnMenuItemClickListener {
            if (check) {
                presenter.onFinish(date, time,
                    choosed_title.text.toString(), choosed_text.text.toString(), schedule_menu_utils.resultCallback())
            } else {
                choosed_text.error = "Это поле обязательно для заполнения"
            }
            return@setOnMenuItemClickListener true
        }
        deleteButton.isVisible = true
        deleteButton.setOnMenuItemClickListener {
            presenter.onScheduleDelete(data!!, schedule_menu_utils.resultCallback())
            return@setOnMenuItemClickListener true
        }
        val callback: (String, String) -> Unit = { date, time ->
            this.date = date
            this.time = time
        }
        date = data!!.date!!
        time = data!!.time!!
        set_timeButton.setOnMenuItemClickListener {
            ScheduleDialog(this, callback, date, time).show()
            return@setOnMenuItemClickListener true
        }
        return true
    }

    override fun showToast(message_id: Int) = Toast.makeText(this, message_id, Toast.LENGTH_SHORT).show()

    override fun hide(intent: Intent) {
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun initControls() {
        choosed_title.setText(data!!.title)
        choosed_text.setText(data!!.text)
    }

    override fun enableChecking() {
        val callback: (Boolean) -> Unit = {
            check = it
        }
        Validator().verifyText(choosed_text, callback)
    }
}