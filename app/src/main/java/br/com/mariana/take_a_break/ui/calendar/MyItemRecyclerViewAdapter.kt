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
        holder.taskTitle.hint = "Digite o nome da tarefa"
        holder.taskSubtitle.hint = "Adicione detalhes"

        for (i in 0..itemCount) {
            holder.taskTitle.text = values[i].title
            holder.taskSubtitle.text = values[i].content
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById(R.id.item_title)
        val taskSubtitle: TextView = view.findViewById(R.id.content)
    }
}