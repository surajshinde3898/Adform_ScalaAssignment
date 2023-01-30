package ScalaAssignment

import scala.annotation.tailrec

object coreDigit extends App{

  for (lines <- io.Source.fromFile("/Users/surajshinde/Downloads/Assignment_1/CoreDigit.txt").getLines()) {
    val inputData = lines.split(" ")
    val inputMulti = inputData(1).toInt
    val inputNum = BigInt(inputData(0))
    val sum = digSum(inputNum, 0)
    val result = digSum(sum * inputMulti, 0)
    println("Digit Sum :::: " + result)
  }

  @tailrec
  def digSum(num: BigInt, accumulator: BigInt): BigInt = {
    if (num == 0 && accumulator < 9) accumulator
    else if (num == 0) {
      digSum(accumulator, 0)
    }
    else digSum(num / 10, accumulator = accumulator + (num % 10))
  }

}
