package ScalaAssignment

object HarvestSecondAndThirdQues extends App{


  // What is your best-earning fruit (overall and by month)? Which is your least profitable fruit (again, overall and by month)

  val harvestSource = scala.io.Source.fromFile("/Users/surajshinde/Downloads/Assignment_1/HarvestProblem/harvest.csv").getLines().drop(1).toList
  val priceSource = scala.io.Source.fromFile("/Users/surajshinde/Downloads/Assignment_1/HarvestProblem/prices.csv").getLines().drop(1).toList


  val priceRow = priceSource.map(x => x.split(","))
  val harvestRow = harvestSource.map(x => x.split(","))

  val priceMap = priceRow.groupBy(p => (p(0), p(1).substring(5, 7))).view.mapValues(x => x(0)(2)).toMap
  //priceMap.foreach(println)
  //substring(5,10)

  val harvestMap = harvestRow.groupBy(p => (p(2), p(1).substring(3, 5), p(0))).view.mapValues(x => (x(0)(0), x(0)(3))).toMap
  //harvestMap.foreach(println)

  val JoinData = harvestMap.map(x => (x._2._1, x._2._2.toDouble, x._1._1, x._1._2, priceMap.getOrElse((x._1._1, x._1._2), 0.0).toString.toDouble * x._2._2.toDouble))
  val sumOfAmountOverall = JoinData.groupBy(x => (x._3)).map(y => List(y._1 -> y._2.map(z => z._5.toString.toDouble).fold(0.0)((a, b) => a + b))).flatten

  // JoinData.foreach(println)

  //println(sumOfAmountOverall)
  println("Best Earning Fruit By Year: " + sumOfAmountOverall.toSeq.sortWith(_._2 > _._2).head)
  println("Least Profitable Fruit By Year: " + sumOfAmountOverall.toSeq.sortWith(_._2 < _._2).head)

  val sumOfAmountByMonth = JoinData.groupBy(x => (x._3, x._4.substring(0, 2))).map(y => List(y._1 -> y._2.map(z => z._5.toDouble).fold(0.0)((a, b) => a + b))).flatten
  //  sumOfAmountByMonth.foreach(println)

  val BestEarningFruitByMonth = sumOfAmountByMonth.groupBy(x => x._1._2).map(dataByMonth => dataByMonth._2.toSeq.sortWith((_._2 > _._2)).head)
  val LeastEarningFruitByMonth = sumOfAmountByMonth.groupBy(x => x._1._2).map(dataByMonth => dataByMonth._2.toSeq.sortWith((_._2 < _._2)).head)
  println(BestEarningFruitByMonth)
  println(LeastEarningFruitByMonth)


  // Which gatherer contributed most to your income (during the whole year and by month)?
  val BestGathererDuringYear = JoinData.groupBy(x => x._1).map(y => List(y._1 -> y._2.map(z => z._5.toDouble).fold(0.0)((a, b) => a + b))).flatten.toSeq.sortWith(_._2 > _._2).head
  println("Best gatherer who contributed most of income during whole year :: " + BestGathererDuringYear)
  //
  val BestGathererByMonth = JoinData.groupBy(x => (x._1, x._4.substring(0, 2))).map(y => List(y._1 -> y._2.map(z => z._5.toDouble).fold(0.0)((a, b) => a + b))).flatten
  println(BestGathererByMonth.groupBy(x => x._1._2).map(y => y._2.toSeq.sortWith(_._2 > _._2).head))

}
