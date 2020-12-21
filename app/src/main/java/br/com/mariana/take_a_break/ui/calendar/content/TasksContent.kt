package br.com.mariana.take_a_break.ui.calendar.content

import java.util.*

object TasksContent {

    val ITEMS: MutableList<TaskItem> = ArrayList()

    private val ITEM_MAP: MutableMap<String, TaskItem> = HashMap()

    init {
        for (i in 1..10) {
            addItem(createTaskItem("", ""))
        }
    }

    private fun addItem(item: TaskItem) {
        ITEMS.add(item)
        ITEM_MAP[item.title] = item
    }

    private fun createTaskItem(title: String, details: String): TaskItem {
        return TaskItem(title, details)
    }

    data class TaskItem(val title: String, val content: String) {
        override fun toString(): String = content
    }
}