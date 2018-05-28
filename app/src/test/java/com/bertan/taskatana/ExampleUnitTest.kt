package com.bertan.taskatana

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    val t1 = Task(UUID.randomUUID(), "1", false, 1)
    val t2 = Task(UUID.randomUUID(), "2", false, 2)
    val t3 = Task(UUID.randomUUID(), "3", false, 3)
    val asas = mutableListOf(t1, t2, t3)

    val toAdd = Task(UUID.randomUUID(), "4", false, 4)

    val index = asas.indexOf(toAdd)
    when (index) {
      -1 -> asas.add(toAdd)
      else -> asas.set(index, toAdd)
    }

    asas.forEach {
      println("$it")
    }

    assertEquals(4, 2 + 2)
  }
}
