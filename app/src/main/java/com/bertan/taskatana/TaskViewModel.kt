package com.bertan.taskatana

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TaskViewModel: ViewModel() {
  val tasksData = MutableLiveData<List<Task>>()
  private val tasks = mutableListOf<Task>()

  fun setTask(task: Task) {
    val index = tasks.indexOf(task)
    when (index) {
      -1 -> tasks.add(task)
      else -> tasks[index] = task
    }
    tasksData.value = tasks.sortedBy { it.order }
  }
}