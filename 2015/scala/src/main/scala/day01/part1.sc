import scala.io.Source

val input = Source.stdin.getLines().mkString
println(input.count(_ == '(') - input.count(_ == ')'))