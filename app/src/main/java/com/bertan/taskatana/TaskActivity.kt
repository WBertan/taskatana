package com.bertan.taskatana

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import java.lang.ref.WeakReference
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

  private val bottomView by lazy {
    findViewById<ConstraintLayout>(R.id.bottomView)
  }

  private val editTextTask by lazy {
    val editText = findViewById<EditText>(R.id.editTextTask)
    editText.setOnFocusChangeListener { _, hasFocus ->
      if (hasFocus) {
        showKeyboard()
      }
    }
    editText
  }

  private val buttonSave by lazy {
    findViewById<Button>(R.id.buttonSave)
  }

  private val taskAdapter by lazy {
    TaskAdapter(this, listOf(), openTask, viewModel)
  }

  private lateinit var fabAnim: FabAnim

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task)

    fabAnim = FabAnim(WeakReference(actionButton), WeakReference(bottomView))
    fabAnim.hideBottomView()

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = taskAdapter

    viewModel.tasksData.observe(this, Observer(updateTaskList))
    actionButton.setOnClickListener {
      newTask()
    }
  }

  private fun newTask() {
    openTask(null)
  }

  private val openTask: (Task?) -> Unit = { task ->
    fabAnim.showBottomView()

    editTextTask.setText(task?.title)
    editTextTask.requestFocus()
    editTextTask.setSelection(editTextTask.text.length)

    buttonSave.setOnClickListener {
      val taskToSave = task ?: Task(UUID.randomUUID(), "", false, 0)

      viewModel.setTask(taskToSave.copy(title = editTextTask.text.toString()))

      fabAnim.hideBottomView()
      editTextTask.text.clear()
      hideKeyboard()
    }
  }

  private val updateTaskList: (List<Task>?) -> Unit = {
    taskAdapter.items = it ?: listOf()
    taskAdapter.notifyDataSetChanged()
  }

  private fun showKeyboard() {
    val view = currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.showSoftInput(view, 0)
    }
  }

  private fun hideKeyboard() {
    val view = currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }
}
