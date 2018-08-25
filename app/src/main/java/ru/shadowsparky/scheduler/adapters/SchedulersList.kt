package ru.shadowsparky.scheduler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.room_utils.*
import ru.shadowsparky.scheduler.schedulers_menu.SchedulersMenu
import java.io.Serializable

open class SchedulersList(
        val data: ArrayList<Schedulers>,
        val callback: SchedulersMenu.Touch
) : RecyclerView.Adapter<SchedulersList.MainViewHolder>() {

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.text.text = data[position].text
        holder.card.setOnClickListener {
            callback.onItemTouched(data[position], holder.card, position)
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
        this.notifyItemInserted(data.size)
    }

    fun remove(index: Int) {
        data.removeAt(index)
        this.notifyItemRemoved(index)
        this.notifyDataSetChanged()
    }

    fun update(item: Schedulers, position: Int) {
        data[position] = item
        this.notifyItemChanged(position)
    }

    open class MainViewHolder : RecyclerView.ViewHolder {
        var title: TextView
        var text: TextView
        var card: CardView

        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.element_title)
            text = itemView.findViewById(R.id.element_text)
            card = itemView.findViewById(R.id.card)
        }
    }
}