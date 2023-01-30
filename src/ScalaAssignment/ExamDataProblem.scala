package ScalaAssignment

object ExamDataProblem extends App{
  for (line <- io.Source.fromFile("/Users/surajshinde/Downloads/Assignment_1/ExamData.txt").getLines()) {
    if (!line.isEmpty) {
      val ListData = line.split(",").toList
      val calculatedHour = (ListData.head).trim.toInt * (ListData.tail(0)).trim.toInt
      if (calculatedHour < ListData.tail(1).trim.toInt) {
        println(ListData + "-" + "No")
      }
      else
        println(ListData + "-" + "Yes")
    }
  }
}
