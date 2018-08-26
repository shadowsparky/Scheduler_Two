package ru.shadowsparky.scheduler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.room_utils.Schedulers
import ru.shadowsparky.scheduler.schedulers_menu.SchedulersMenu

open class SchedulersList(
        val data: ArrayList<Schedulers>,
        val callback: SchedulersMenu.Touch
) : RecyclerView.Adapter<SchedulersList.MainViewHolder>() {

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (data[position].title!!.isNotBlank()) {
            holder.title.visibility = VISIBLE
            holder.title.text = data[position].title
        } else
            holder.title.visibility = GONE
        holder.text.text = data[position].text
        holder.card.setOnClickListener {
            callback.onItemTouched(data[position], holder.card, position)
        }
        if (data[position].date!!.isNotBlank()) {
            holder.scheduleLayout.visibility = VISIBLE
            holder.date.text = data[position].date
            holder.time.text = data[position].time
        } else {
            holder.scheduleLayout.visibility = GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return MainViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun add(item: Schedulers) {
        data.add(item)
//        this.notifyItemInserted(item)
        this.notifyDataSetChanged()
    }

    fun remove(index: Int) {
        data.removeAt(index)
        this.notifyItemRemoved(index)
        this.notifyDataSetChanged()
    }

    fun update(item: Schedulers, position: Int) {
        data[position] = item
        this.notifyItemChanged(position)
        this.notifyDataSetChanged()
    }

    open class MainViewHolder : RecyclerView.ViewHolder {
        var title: TextView
        var text: TextView
        var card: CardView
        var scheduleLayout: LinearLayout
        var time: TextView
        var date: TextView

        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.element_title)
            text = itemView.findViewById(R.id.element_text)
            card = itemView.findViewById(R.id.card)
            scheduleLayout = itemView.findViewById(R.id.element_footer)
            time = itemView.findViewById(R.id.element_time)
            date = itemView.findViewById(R.id.element_date)
        }
    }
}