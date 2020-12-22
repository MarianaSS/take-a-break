package br.com.mariana.take_a_break.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mariana.take_a_break.R
import br.com.mariana.take_a_break.ui.calendar.content.TasksContent
import kotlinx.android.synthetic.main.fragment_item_list.view.*
import kotlinx.android.synthetic.main.fragment_timer.*

class ItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val viewList = view.list
        if (viewList is RecyclerView) {
            with(viewList) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(TasksContent.ITEMS)
            }
        }
        // GAMBI GAMBI GAMBI
        view.first_item_title?.text = view.context.resources.getString(R.string.current_task_title)
        view.first_item_detail?.text = view.context.resources.getString(R.string.current_task_detail)

        view.second_item_title?.text = view.context.resources.getString(R.string.next_task_title)
        view.second_item_detail?.text = view.context.resources.getString(R.string.next_task_detail)
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}