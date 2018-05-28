package com.bertan.taskatana

import java.util.*

data class Task(val id: UUID,
                val title: String,
                val completed: Boolean,
                val order: Int) {

  override fun toString(): String {
    return "Title: $title - Completed: $completed - Order: $order"
  }

  override fun equals(other: Any?): Boolean {
    if(other is Task) {
      return id == other.id
    }
    return super.equals(other)
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + title.hashCode()
    result = 31 * result + completed.hashCode()
    result = 31 * result + order
    return result
  }
}