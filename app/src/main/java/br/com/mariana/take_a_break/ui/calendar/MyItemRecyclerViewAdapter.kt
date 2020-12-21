package br.com.mariana.take_a_break.ui.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mariana.take_a_break.R
import br.com.mariana.take_a_break.ui.calendar.content.TasksContent

class MyItemRecyclerViewAdapter(
    private val values: MutableList<TasksContent.TaskItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTitle.hint = holder.titleText
        holder.taskSubtitle.hint = holder.contentText

        for (i in 0 until itemCount) {
            holder.taskTitle.text = values[i].title
            holder.taskSubtitle.text = values[i].content
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById(R.id.item_title)
        val taskSubtitle: TextView = view.findViewById(R.id.content)
        val titleText: String = view.context.resources.getString(R.string.task_title_hint)
        val contentText: String = view.context.resources.getString(R.string.task_content_hint)
    }
}