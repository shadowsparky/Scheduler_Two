/*
 * Samsonov Eugene (c) 2018.
 */

package ru.shadowsparky.scheduler.schedulers_edit.schedulers_add

import android.os.Bundle
import ru.shadowsparky.scheduler.schedulers_edit.SchedulersEditView

class SchedulersAddView : SchedulersEditView() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SchedulersAddPresenter(this, SchedulersAddModel(this))
    }
}
