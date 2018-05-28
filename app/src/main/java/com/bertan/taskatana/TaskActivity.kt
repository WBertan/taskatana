package com.bertan.taskatana

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

class TaskActivity : AppCompatActivity() {
  private val viewModel by lazy {
    ViewModelProviders.of(this)[TaskViewModel::class.java]
  }

  private val recyclerView by lazy {
    findViewById<RecyclerView>(R.id.recyclerView)
  }

  private val actionButton by lazy {
    findViewById<FloatingActionButton>(R.id.floatingActionButton)
  }

  private val taskAdapter by lazy {
    TaskAdapter(this, listOf(), viewModel)
  }

  private var order = 100

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task)

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = taskAdapter

    viewModel.tasksData.observe(this, Observer(updateTaskList))
    actionButton.setOnClickListener {
      val task = Task(UUID.randomUUID(), "Asas $order", false, --order)
      viewModel.setTask(task)
    }
  }

  private val updateTaskList: (List<Task>?) -> Unit = {
    taskAdapter.items = it ?: listOf()
    taskAdapter.notifyDataSetChanged()
  }
}
