package ScalaAssignment

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer

object HarvestFirstQuestion extends App{

  //Who is your best gatherer in terms of the amounts of fruits gathered every month? Are there employees
  // that are better at gathering some specific fruit?

  val rows = ArrayBuffer[Array[String]]()
  val Source = scala.io.Source.fromFile("/Users/surajshinde/Downloads/Assignment_1/HarvestProblem/harvest.csv").getLines().drop(1)
  val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")

  for (lines <- Source) {
    rows += lines.split(",").map(_.trim)
  }

  // sum of Amount for whole year
  //val sumOfAmount = rows.groupBy(row => row(0)).map(y => Map(y._1 -> y._2.map(value => value(3).toDouble).fold(0.0)((a, b) => a + b)))


  val sumOfAmountByMonth = rows.groupBy(row => row(0)).map(y => Map(y._1 -> y._2.groupBy(value => value(1)(4)))
    .map(data => Map(data._1 -> data._2.values.map(innerData => Map(LocalDate.parse(innerData(1)(1).toString, formatter).getMonth.toString -> innerData.map(valueData => valueData(3).toDouble).fold(0.0)((a, b) => a + b))))))
  println("Sum of Amount By Month :: " + sumOfAmountByMonth)

  val maxGatherSpecificFruit = rows.groupBy(row => row(0)).map(y => Map(y._1 -> y._2.groupBy(value => value(2)))
    .map(data => Map(data._1 -> data._2.values.map(innerData => List(innerData(0)(2) -> innerData.map(valueData => valueData(3).toDouble).fold(0.0)((a, b) => a + b))).flatten)))


  val maxGathererForSpecificFruit = maxGatherSpecificFruit.map(x => x.map(y => Map(y.keys -> y.values.flatten.toSeq.sortWith(_._2 > _._2).head)))

  println("Better at gatherer in some specific fruit::  " + maxGathererForSpecificFruit)

}
