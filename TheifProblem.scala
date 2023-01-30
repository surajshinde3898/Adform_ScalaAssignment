package ScalaAssignment

import scala.annotation.tailrec

object TheifProblem extends App{
  @tailrec
  def minChanges(doors: String, index: Int = 0, previousOpen: Boolean = false, acc: Int = 0): Int = {

    if (index == doors.length) return acc
    val currentOpen = doors(index) == '1'
    val currentChanges = if (previousOpen != currentOpen) 1 else 0
    val nextIndex = index + 1
    if (currentOpen) minChanges(doors, nextIndex, true, acc + currentChanges)
    else minChanges(doors, nextIndex, false, acc + currentChanges)
  }

  for (lines <- io.Source.fromFile("/Users/surajshinde/Downloads/Assignment_1/thief_data.txt").getLines()) {
    var change = 0
    if (lines(0) == '0') change = 1
    println(s"$lines required " + minChanges(lines, acc = change) + " times flip")
  }
}
