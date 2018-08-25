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



class SchedulersShowView : AppCompatActivity(), SchedulersShow.View {
    override fun enableChecking(item: MenuItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var data: Schedulers? = null
    private val schedule_menu_utils = Schedule_Menu_Utils(this)
    private val utils = MenuUtils()
    private lateinit var time: Dialog
    private lateinit var date: Dialog
    private var position: Int = -1
    private lateinit var presenter: SchedulersShow.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedulers_add)
        setSupportActionBar(add_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        data = intent.getSerializableExtra("Saved_Data") as Schedulers
        position = intent.getIntExtra("Position", -1)
        time = TimeDialog(this, schedule_menu_utils.getChooseCallback(choosed_time))
        date = DateDialog(this, schedule_menu_utils.getChooseCallback(choosed_date))
        presenter = SchedulersShowPresenter(this, SchedulersShowModel(applicationContext, data!!, position))
        initControls()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        utils.setBackButton(item!!, this)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val addButton = utils.getItemWithInflate(menuInflater, menu!!, R.id.schedule_add)
        val deleteButton = utils.getItem(menu!!, R.id.schedule_delete)
        addButton.setOnMenuItemClickListener {
                    presenter.onScheduleCompleted(choosed_date.text.toString(), choosed_time.text.toString(),
                        choosed_title.text.toString(), choosed_text.text.toString(), schedule_menu_utils.resultCallback())
                    return@setOnMenuItemClickListener true
        }
        deleteButton.isVisible = true
        deleteButton.setOnMenuItemClickListener {
                    presenter.onScheduleDelete(data!!, schedule_menu_utils.resultCallback())
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
        choosed_date.text = data!!.date
        choosed_time.text = data!!.time
        choosed_title.setText(data!!.title)
        choosed_text.setText(data!!.text)
        choosed_date.setOnClickListener { date.show() }
        choosed_time.setOnClickListener { time.show() }
    }
}