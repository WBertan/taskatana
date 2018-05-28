package com.bertan.taskatana

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.task_list_item.view.*

class TaskAdapter(private val context: Context,
                  var items: List<Task>,
                  private val onLongClick: (Task) -> Unit,
                  private val viewModel: TaskViewModel)
  : RecyclerView.Adapter<ViewHolder>() {

  override fun getItemCount(): Int {
    return items.size
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
      ViewHolder(
          LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false)
      )

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val task = items[position]
    holder?.checkBox?.text = task.title
    holder?.checkBox?.setOnCheckedChangeListener(null)
    holder?.checkBox?.isChecked = task.completed
    holder?.checkBox?.setOnCheckedChangeListener { _, isChecked ->
      viewModel.setTask(task.copy(completed = isChecked))
    }
    holder?.checkBox?.setOnLongClickListener {
      onLongClick(task)
      true
    }
  }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val checkBox: CheckBox = view.checkBox
}