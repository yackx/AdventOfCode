import scala.io.Source
import scala.annotation.tailrec

val input = Source.stdin.getLines().mkString

@tailrec
def findBasement(s: String, index: Int = 0, floor: Int = 0): Int = {
  if (s.isEmpty) throw new Exception("Basement not reached")
  if (floor == -1) index
  else {
    val delta = s.head match {
      case '(' => 1
      case ')' => -1
      case _   => throw new Exception("Invalid character")
    }
    findBasement(s.tail, index + 1, floor + delta)
  }
}

println(findBasement(input, 0, 0))
